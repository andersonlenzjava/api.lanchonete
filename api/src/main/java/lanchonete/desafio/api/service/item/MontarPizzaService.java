package lanchonete.desafio.api.service.item;

import java.net.URI;
import java.util.Optional;

import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaRepository;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoRepository;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioRepository;
import lanchonete.desafio.api.domain.item.Pizza.MontarPizzaRegister;
import lanchonete.desafio.api.domain.item.Pizza.MontarPizzaResponse;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.Pizza.PizzaRepository;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import lanchonete.desafio.api.domain.pedido.Pedido.PedidoRepository;
import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class MontarPizzaService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	PizzaBordaRepository pizzaBordaRepository;
	
	@Autowired
	PizzaMolhoRepository pizzaMolhoRepository;
	
	@Autowired
	PizzaRecheioRepository pizzaRecheioRepository;
	
	// get 
	public Page<MontarPizzaResponse> listarPizzasPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Pizza> pizzas = pizzaRepository.findPizzasByPedidoNumero(pedidoId, paginacao);
			return MontarPizzaResponse.converter(pizzas);
		}
		return null;
	}
	

	//get id
	public ResponseEntity<MontarPizzaResponse> listarPizzaPedidoPorId(Long pedidoId, Long pizzaId) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Pizza> pizzaOptional = pizzaRepository.findUmaPizzaPorIdEPedido(pedidoId, pizzaId);
			if (pizzaOptional.isPresent()) {
				return ResponseEntity.ok(MontarPizzaResponse.converterUmaPizza(pizzaOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarPizzaResponse> cadastrarPizza(MontarPizzaRegister montarPizzaForm,
															  UriComponentsBuilder uriBuilder) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.pedidoId());
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.pizzaBordaId());
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.pizzaMolhoId());
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.pizzaRecheioId());
		
		if (pedidoOptional.isPresent()
				&& pizzaBordaOptional.isPresent()
				&& pizzaMolhoOptional.isPresent()
				&& pizzaRecheioOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
	
				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
						
						PizzaBorda pizzaBorda = pizzaBordaOptional.get();
						PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
						PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
	
						Pizza pizza = new Pizza(pedido, pizzaBorda, pizzaMolho, pizzaRecheio);
						
						pizzaRepository.save(pizza);
	
						pedido.adicionaPizza(pizza);  // operações com os saldos 
	
						pedidoRepository.save(pedido);
	
						URI uri = uriBuilder.path("/pedido/pizzas/{id}").buildAndExpand(pizza.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarPizzaResponse(pizza));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarPizzaResponse> atualizarPizza(Long pizzaId, MontarPizzaRegister montarPizzaForm) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarPizzaForm.pedidoId());
		Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(montarPizzaForm.pizzaBordaId()); // 3,4,5
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(montarPizzaForm.pizzaMolhoId());
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(montarPizzaForm.pizzaRecheioId());
		
		if (pedidoOptional.isPresent() 
				&& pizzaOptional.isPresent()
				&& pizzaBordaOptional.isPresent()
				&& pizzaMolhoOptional.isPresent()
				&& pizzaRecheioOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				Pizza pizza = pizzaOptional.get();
				
				if ((pizza.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
						
						pedido.removerPizza(pizza); // operações com os saldos 
					
						PizzaBorda pizzaBorda = pizzaBordaOptional.get();
						PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
						PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
						
						pizza.setPizzaBorda(pizzaBorda);
						pizza.setPizzaMolho(pizzaMolho);
						pizza.setPizzaRecheio(pizzaRecheio);
						pizza.CalculosPizza();
						
						pizzaRepository.save(pizza);
						
						pedido.adicionaPizza(pizza); // operações com os saldos 
	
						pedidoRepository.save(pedido);
	
						return ResponseEntity.ok(new MontarPizzaResponse(pizza));
					}
			}
		return ResponseEntity.notFound().build();
	}
	
	// deletar
	public ResponseEntity<?> removerPizzaPedido(Long pedidoId, Long pizzaId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional <Pizza> pizzaOptional = pizzaRepository.findUmaPizzaPorIdEPedido(pedidoId, pizzaId);

			if ((pizzaOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerPizza(pizzaOptional.get()); // operações com os saldos 
				pizzaRepository.delete(pizzaOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
