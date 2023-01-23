package lanchonete.desafio.api.service.ingredientes;

import java.net.URI;
import java.util.Optional;

import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaRepository;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaResponse;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoReponse;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoRepository;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioRepository;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioResponse;
import lanchonete.desafio.api.infra.exeption.ItemJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class PizzaService {

//===================================================================================================================//
//PizzaBorda		
	
	@Autowired
	private PizzaBordaRepository pizzaBordaRepository;
	
	//Get
	public Page<PizzaBordaResponse> listarPizzaBorda(String tipoBorda, Pageable paginacao) {
		if(tipoBorda == null) {
			Page<PizzaBorda> pizzaBorda = pizzaBordaRepository.findAll(paginacao);
			return PizzaBordaResponse.converter(pizzaBorda);
		} else {
			Page<PizzaBorda> pizzaBorda = pizzaBordaRepository.findByTipoBordaIgnoreCase(tipoBorda, paginacao);
			return PizzaBordaResponse.converter(pizzaBorda);
		}
	}

	//Get id
	public ResponseEntity<PizzaBordaResponse> detalharPizzaBordaPorId(Long id) {
		Optional<PizzaBorda> pizzaBorda = pizzaBordaRepository.findById(id);
		if (pizzaBorda.isPresent()) {
			return ResponseEntity.ok(PizzaBordaResponse.converterUmLancheMolho(pizzaBorda.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaBordaResponse> cadastrarPizzaBorda(PizzaBordaRegister pizzaBordaRegister,
																  UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaBorda pizzaBorda = pizzaBordaRegister.converter();
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findByTipoBordaIgnoreCase(pizzaBorda.getTipoBorda());
		if (pizzaBordaOptional.isEmpty()) {
			pizzaBordaRepository.save(pizzaBorda);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaBorda.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaBordaResponse(pizzaBorda));
		} else {
			throw new ItemJaExisteException("Borda já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<PizzaBordaResponse> atualizarLancheTipoPao(Long id,PizzaBordaRegister pizzaBordaRegister) {
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(id);
		if (pizzaBordaOptional.isPresent()) {
			
			PizzaBorda pizzaBorda = pizzaBordaOptional.get();
			pizzaBorda.setTipoBorda(pizzaBordaRegister.tipoBorda());
			pizzaBorda.getIngrediente().setPeso(pizzaBordaRegister.peso());
			pizzaBorda.getIngrediente().setDataValidade(pizzaBordaRegister.dataValidade());
			pizzaBorda.getIngrediente().setPrecoVenda(pizzaBordaRegister.precoVenda());
			pizzaBordaRepository.save(pizzaBorda);
			
			return ResponseEntity.ok(new PizzaBordaResponse(pizzaBorda));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerPizzaBorda(Long id) {
		Optional<PizzaBorda> pizzaBordaOptional = pizzaBordaRepository.findById(id);
		if (pizzaBordaOptional.isPresent()) {
			pizzaBordaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	
//===================================================================================================================//
//PizzaMolho
	
	@Autowired
	private PizzaMolhoRepository pizzaMolhoRepository;
	
	//Get
	public Page<PizzaMolhoReponse> listarPizzaMolho(String tipoMolho, Pageable paginacao) {
		if(tipoMolho == null) {
			Page<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findAll(paginacao);
			return PizzaMolhoReponse.converter(pizzaMolho);
		} else {
			Page<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findByTipoMolhoIgnoreCase(tipoMolho, paginacao);
			return PizzaMolhoReponse.converter(pizzaMolho);
		}
	}

	//Get id
	public ResponseEntity<PizzaMolhoReponse> detalharPizzaMolhoPorId(Long id) {
		Optional<PizzaMolho> pizzaMolho = pizzaMolhoRepository.findById(id);
		if (pizzaMolho.isPresent()) {
			return ResponseEntity.ok(PizzaMolhoReponse.converterUmLancheMolho(pizzaMolho.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaMolhoReponse> cadastrarPizzaMolho(PizzaMolhoRegister pizzaMolhoRegister,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaMolho pizzaMolho = pizzaMolhoRegister.converter();
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findByTipoMolhoIgnoreCase(pizzaMolho.getTipoMolho());
		if (pizzaMolhoOptional.isEmpty()) {
			pizzaMolhoRepository.save(pizzaMolho);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaMolho.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaMolhoReponse(pizzaMolho));
		} else {
			throw new ItemJaExisteException("Molho já existe");
		}
	}
	
	//atualizar
	public ResponseEntity<PizzaMolhoReponse> atualizarLancheTipoPao(Long id, PizzaMolhoRegister pizzaMolhoRegister) {
		Optional<PizzaMolho> pizzaMolhoOptional = pizzaMolhoRepository.findById(id);
		if (pizzaMolhoOptional.isPresent()) {
			
			PizzaMolho pizzaMolho = pizzaMolhoOptional.get();
			pizzaMolho.setTipoMolho(pizzaMolhoRegister.tipoMolho());
			pizzaMolho.getIngrediente().setPeso(pizzaMolhoRegister.peso());
			pizzaMolho.getIngrediente().setDataValidade(pizzaMolhoRegister.dataValidade());
			pizzaMolho.getIngrediente().setPrecoVenda(pizzaMolhoRegister.precoVenda());
			pizzaMolhoRepository.save(pizzaMolho);
			
			return ResponseEntity.ok(new PizzaMolhoReponse(pizzaMolho));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerPizzaMolho(Long id) {
		Optional<PizzaMolho> lancheMolhoOptional = pizzaMolhoRepository.findById(id);
		if (lancheMolhoOptional.isPresent()) {
			pizzaMolhoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	

//===================================================================================================================//
//PizzaRecheio
	
	@Autowired
	private PizzaRecheioRepository pizzaRecheioRepository;
	
	//Get
	public Page<PizzaRecheioResponse> listarPizzaRecheio(String tipoRecheio, Pageable paginacao) {
		if(tipoRecheio == null) {
			Page<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findAll(paginacao);
			return PizzaRecheioResponse.converter(pizzaRecheio);
		} else {
			Page<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
			return PizzaRecheioResponse.converter(pizzaRecheio);
		}
	}

	//Get id
	public ResponseEntity<PizzaRecheioResponse> detalharPizzaRecheioPorId(Long id) {
		Optional<PizzaRecheio> pizzaRecheio = pizzaRecheioRepository.findById(id);
		if (pizzaRecheio.isPresent()) {
			return ResponseEntity.ok(PizzaRecheioResponse.converterUmLancheMolho(pizzaRecheio.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<PizzaRecheioResponse> cadastrarPizzaRecheio(PizzaRecheioRegister pizzaRecheioRegister,
																	  UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		PizzaRecheio pizzaRecheio = pizzaRecheioRegister.converter();
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findByTipoRecheioIgnoreCase(pizzaRecheio.getTipoRecheio());
		if (pizzaRecheioOptional.isEmpty()) {
			pizzaRecheioRepository.save(pizzaRecheio);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(pizzaRecheio.getId()).toUri();
			return ResponseEntity.created(uri).body(new PizzaRecheioResponse(pizzaRecheio));
		} else {
			throw new ItemJaExisteException("Recheio já existe");
		}
	}

	//atualizar
	public ResponseEntity<PizzaRecheioResponse> atualizarPizzaRecheio(Long id,PizzaRecheioRegister pizzaRecheioRegister) {
		Optional<PizzaRecheio> pizzaRecheioOptional = pizzaRecheioRepository.findById(id);
		if (pizzaRecheioOptional.isPresent()) {
			
			PizzaRecheio pizzaRecheio = pizzaRecheioOptional.get();
			pizzaRecheio.setTipoRecheio(pizzaRecheioRegister.tipoRecheio());
			pizzaRecheio.getIngrediente().setPeso(pizzaRecheioRegister.peso());
			pizzaRecheio.getIngrediente().setDataValidade(pizzaRecheioRegister.dataValidade());
			pizzaRecheio.getIngrediente().setPrecoVenda(pizzaRecheioRegister.precoVenda());
			pizzaRecheioRepository.save(pizzaRecheio);
			
			return ResponseEntity.ok(new PizzaRecheioResponse(pizzaRecheio));
		}
		return ResponseEntity.notFound().build();
	}
	
	//deletar 
	public ResponseEntity<?> removerPizzaRecheio(Long id) {
		Optional<PizzaRecheio> lancheMolhoOptional = pizzaRecheioRepository.findById(id);
		if (lancheMolhoOptional.isPresent()) {
			pizzaRecheioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
