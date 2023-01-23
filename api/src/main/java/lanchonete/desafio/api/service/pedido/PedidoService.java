package lanchonete.desafio.api.service.pedido;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Lanche.LancheRepository;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.Pizza.PizzaRepository;
import lanchonete.desafio.api.domain.item.Salgadinho.Salgadinho;
import lanchonete.desafio.api.domain.item.Salgadinho.SalgadinhoRepository;
import lanchonete.desafio.api.domain.pedido.Pedido.*;
import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import lanchonete.desafio.api.infra.exeption.ValorPagoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;



@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	LancheRepository lancheRepository;
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	SalgadinhoRepository salgadinhoRepository;

	// get pedidos
	public Page<PedidoResponse> listarPedidos(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findAll(paginacao);
		return PedidoResponse.converter(pedidos);
	}

	// get por id
	public ResponseEntity<PedidoResponse> listarPedidoPorId(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			return ResponseEntity.ok(PedidoResponse.converterUmPedido(pedidoOptional.get()));
		}
		return ResponseEntity.notFound().build();
	} 
	
	//get por id completo
	public ResponseEntity<PedidoCompletoResponse> listarPedidoCompletoPorId(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			List<Lanche> findListLanchesPedido = lancheRepository.findListLanchesPedido(pedidoId);
			List<Pizza> findListPizzasPedido = pizzaRepository.findListPizzasPedido(pedidoId);
			List<Salgadinho> findListSalgadinhosPedido = salgadinhoRepository.findListSalgadinhosPedido(pedidoId);
			
			return ResponseEntity.ok(PedidoCompletoResponse.converterUmPedido(pedidoOptional.get(), findListLanchesPedido,
					findListPizzasPedido, findListSalgadinhosPedido));
		}
		return ResponseEntity.notFound().build();
	}
	
	// Get Aberto
	public Page<PedidoResponse> listarPedidosAbertos(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findbyStatusPedido(StatusPedido.ABERTO, paginacao);
		if(pedidos != null) {
			return PedidoResponse.converter(pedidos);
		}
		return null;
	} 

	//Get Processando
	public Page<PedidoResponse> listarPedidosProcessando(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findbyStatusPedido(StatusPedido.PROCESSANDO, paginacao);
		if(pedidos != null) {
			return PedidoResponse.converter(pedidos);
		}
		return null;
	} 

	//Get PagoFinalizado
	public Page<PedidoResponse> listarPedidosPagoFinalizado(Pageable paginacao) {
		Page<Pedido> pedidos = pedidoRepository.findbyStatusPedido(StatusPedido.PAGOFINALIZADO, paginacao);
		if(pedidos != null) {
			return PedidoResponse.converter(pedidos);
		}
		return null;
	} 

	// cadastrar pedido
	public ResponseEntity<PedidoResponse> cadastrarPedido(PedidoCompletoRegister pedidoCompletoRegister, UriComponentsBuilder uriBuilder) {
		Pedido pedido = pedidoCompletoRegister.converter();
		pedidoRepository.save(pedido);
		URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
	}

	// atualizar pedido
	public ResponseEntity<PedidoResponse> atualizarPedido(Long pedidoId, PedidoCompletoRegister pedidoCompletoRegister, UriComponentsBuilder uriBuilder) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Pedido pedido = pedidoOptional.get();
			if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {

				pedido.setNomeCliente(pedidoCompletoRegister.nomeCliente());
				pedidoRepository.save(pedido);

				return ResponseEntity.ok(new PedidoResponse(pedido));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// retorna o troco no corpo da mensagem 
	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(Long pedidoId, BigDecimal valorPago) throws ValorPagoInsuficienteException {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			
			if(pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
				return ResponseEntity.ok(pedido.calcularTroco(valorPago));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// deletar pedido
	public ResponseEntity<?> removerPedido(Long pedidoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			// primeiro tem de verificar se tem lanche para poder deletar 
			

			List<Lanche> lancheOptional = lancheRepository.findListLanchesPedido(pedidoId);
			List<Pizza> pizzaOptional = pizzaRepository.findListPizzasPedido(pedidoId);
			List<Salgadinho> salgadinhoOptional = salgadinhoRepository.findListSalgadinhosPedido(pedidoId);
			
			if (lancheOptional != null) {
				lancheRepository.deleteLancheByPedidoId(pedidoId);				
			}
			
			if (pizzaOptional != null) {
				pizzaRepository.deletePizzaByPedidoId(pedidoId);			
			}
			
			if (salgadinhoOptional != null) {
				salgadinhoRepository.deleteSalgadinhoByPedidoId(pedidoId);				
			}
			
			pedidoRepository.deleteById(pedidoId);

			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	

}
