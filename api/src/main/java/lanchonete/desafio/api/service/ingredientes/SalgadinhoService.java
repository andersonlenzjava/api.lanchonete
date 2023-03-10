package lanchonete.desafio.api.service.ingredientes;

import java.net.URI;
import java.util.Optional;

import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaRepository;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaResponse;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioRepository;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioResponse;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoRepository;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoResponse;
import lanchonete.desafio.api.infra.exeption.ItemJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SalgadinhoService {

//===================================================================================================================//
//SalgadinhoMassa
	
	@Autowired
	private SalgadinhoMassaRepository salgadinhoMassaRepository;

	//Get
	public Page<SalgadinhoMassaResponse> listarSalgadinhoMassa(String tipoMassa, Pageable paginacao) {
		if(tipoMassa == null) {
			Page<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findAll(paginacao);
			return SalgadinhoMassaResponse.converter(salgadinhoTipoMassa);
		} else {
			Page<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findByTipoMassaIgnoreCase(tipoMassa, paginacao);
			return SalgadinhoMassaResponse.converter(salgadinhoTipoMassa);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoMassaResponse> detalharSalgadinhoMassa(Long id) {
		Optional<SalgadinhoMassa> salgadinhoTipoMassa = salgadinhoMassaRepository.findById(id);
		if (salgadinhoTipoMassa.isPresent()) {
			return ResponseEntity.ok(SalgadinhoMassaResponse.converterUmLancheMolho(salgadinhoTipoMassa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoMassaResponse> cadastrarSalgadinhoMassa(@Valid SalgadinhoMassaRegister salgadinhoMassaRegister,
			UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoMassa salgadinhoTipoMassa = salgadinhoMassaRegister.converter();
		Optional<SalgadinhoMassa> salgadinhoTipoMassaOptional = salgadinhoMassaRepository.findByTipoMassaIgnoreCase(salgadinhoTipoMassa.getTipoMassa());
		if (salgadinhoTipoMassaOptional.isEmpty()) {
			salgadinhoMassaRepository.save(salgadinhoTipoMassa);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoTipoMassa.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoMassaResponse(salgadinhoTipoMassa));
		} else {
			throw new ItemJaExisteException("Salgadinho Massa j?? existe");
		}
	}
	
	//atualizar
	public ResponseEntity<SalgadinhoMassaResponse> atualizarSalgadinhoMassa(Long id,
			@Valid SalgadinhoMassaRegister salgadinhoMassaRegister) {
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(id);
		if (salgadinhoMassaOptional.isPresent()) {
			
			SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
			salgadinhoMassa.setTipoMassa(salgadinhoMassaRegister.tipoMassa());
			salgadinhoMassa.getIngrediente().setPeso(salgadinhoMassaRegister.peso());
			salgadinhoMassa.getIngrediente().setDataValidade(salgadinhoMassaRegister.dataValidade());
			salgadinhoMassa.getIngrediente().setPrecoVenda(salgadinhoMassaRegister.precoVenda());
			salgadinhoMassaRepository.save(salgadinhoMassa);
			
			return ResponseEntity.ok(new SalgadinhoMassaResponse(salgadinhoMassa));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerSalgadinhoMassa(Long id) {
		Optional<SalgadinhoMassa> salgadinhoTipoMassaOptional = salgadinhoMassaRepository.findById(id);
		if (salgadinhoTipoMassaOptional.isPresent()) {
			salgadinhoMassaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}



//===================================================================================================================//
//SalgadinhoRecheiro
	
	@Autowired
	private SalgadinhoRecheioRepository salgadinhoRecheioRepository;
	
	//Get
	public Page<SalgadinhoRecheioResponse> listarSalgadinhoRecheio(String tipoRecheio, Pageable paginacao) {
		if(tipoRecheio == null) {
			Page<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findAll(paginacao);
			return SalgadinhoRecheioResponse.converter(salgadinhoRecheio);
		} else {
			Page<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
			return SalgadinhoRecheioResponse.converter(salgadinhoRecheio);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoRecheioResponse> detalharSalgadinhoRecheioPorId(Long id) {
		Optional<SalgadinhoRecheio> salgadinhoRecheio = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheio.isPresent()) {
			return ResponseEntity.ok(SalgadinhoRecheioResponse.converterUmLancheMolho(salgadinhoRecheio.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoRecheioResponse> cadastrarSalgadinhoRecheio(SalgadinhoRecheioRegister salgadinhoRecheioRegister,
																				UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioRegister.converter();
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findByTipoRecheioIgnoreCase(salgadinhoRecheio.getTipoRecheio());
		if (salgadinhoRecheioOptional.isEmpty()) {
			salgadinhoRecheioRepository.save(salgadinhoRecheio);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoRecheio.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoRecheioResponse(salgadinhoRecheio));
		} else {
			throw new ItemJaExisteException("Recheio j?? existe");
		}
	}
	
	//atualizar
	public ResponseEntity<SalgadinhoRecheioResponse> atualizarSalgadinhoRecheio(Long id,
			@Valid SalgadinhoRecheioRegister salgadinhoRecheioRegister) {
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheioOptional.isPresent()) {
			
			SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
			salgadinhoRecheio.setTipoRecheio(salgadinhoRecheioRegister.tipoRecheio());
			salgadinhoRecheio.getIngrediente().setPeso(salgadinhoRecheioRegister.peso());
			salgadinhoRecheio.getIngrediente().setDataValidade(salgadinhoRecheioRegister.dataValidade());
			salgadinhoRecheio.getIngrediente().setPrecoVenda(salgadinhoRecheioRegister.precoVenda());
			salgadinhoRecheioRepository.save(salgadinhoRecheio);
			
			return ResponseEntity.ok(new SalgadinhoRecheioResponse(salgadinhoRecheio));
		}
		return ResponseEntity.notFound().build();
	}

	//deletar 
	public ResponseEntity<?> removerSalgadinhoRecheio(Long id) {
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(id);
		if (salgadinhoRecheioOptional.isPresent()) {
			salgadinhoRecheioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	
	
//===================================================================================================================//
//SalgadinhoTipoPreparo
	
	@Autowired
	private SalgadinhoTipoPreparoRepository salgadinhoTipoPreparoRepository;
	
	//Get
	public Page<SalgadinhoTipoPreparoResponse> listarSalgadinhoTipoPreparo(String tipoPreparo, Pageable paginacao) {
		if(tipoPreparo == null) {
			Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findAll(paginacao);
			return SalgadinhoTipoPreparoResponse.converter(salgadinhoTipoPreparo);
		} else {
			Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findByTipoPreparoIgnoreCase(tipoPreparo, paginacao);
			return SalgadinhoTipoPreparoResponse.converter(salgadinhoTipoPreparo);
		}
	}

	//Get id
	public ResponseEntity<SalgadinhoTipoPreparoResponse> detalharSalgadinhoTipoPreparoPorId(Long id) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparo = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparo.isPresent()) {
			return ResponseEntity.ok(SalgadinhoTipoPreparoResponse.converterUmLancheMolho(salgadinhoTipoPreparo.get()));
		}
		return ResponseEntity.notFound().build();
	}

	//cadastrar
	public ResponseEntity<SalgadinhoTipoPreparoResponse> cadastrarSalgadinhoTipoPreparo(
			SalgadinhoTipoPreparoRegister salgadinhoTipoPreparoRegister, UriComponentsBuilder uriBuilder) throws ItemJaExisteException {
		SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoRegister.converter();
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findByTipoPreparoIgnoreCase(salgadinhoTipoPreparo.getTipoPreparo());
		if (salgadinhoTipoPreparoOptional.isEmpty()) {
			salgadinhoTipoPreparoRepository.save(salgadinhoTipoPreparo);
			URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(salgadinhoTipoPreparo.getId()).toUri();
			return ResponseEntity.created(uri).body(new SalgadinhoTipoPreparoResponse(salgadinhoTipoPreparo));
		} else {
			throw new ItemJaExisteException("TipoPreparo j?? existe");
		}
	}

	//atualizar
	public ResponseEntity<SalgadinhoTipoPreparoResponse> atualizarSalgadinhoTipoPreparo(Long id,
			@Valid SalgadinhoTipoPreparoRegister salgadinhoTipoPreparoRegister) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparoOptional.isPresent()) {
			
			SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
			salgadinhoTipoPreparo.setTipoPreparo(salgadinhoTipoPreparoRegister.tipoPreparo());
			salgadinhoTipoPreparo.getIngrediente().setPeso(salgadinhoTipoPreparoRegister.peso());
			salgadinhoTipoPreparo.getIngrediente().setDataValidade(salgadinhoTipoPreparoRegister.dataValidade());
			salgadinhoTipoPreparo.getIngrediente().setPrecoVenda(salgadinhoTipoPreparoRegister.precoVenda());
			salgadinhoTipoPreparoRepository.save(salgadinhoTipoPreparo);
			
			return ResponseEntity.ok(new SalgadinhoTipoPreparoResponse(salgadinhoTipoPreparo));
		}
		return ResponseEntity.notFound().build();
	}
	
	//deletar 
	public ResponseEntity<?> removerSalgadinhoTipoPreparo(Long id) {
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(id);
		if (salgadinhoTipoPreparoOptional.isPresent()) {
			salgadinhoTipoPreparoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
