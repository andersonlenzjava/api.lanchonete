package lanchonete.desafio.api.service.ingredientes;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoRepository;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoResponse;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioRepository;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioResponse;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoRepository;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoResponse;
import lanchonete.desafio.api.infra.exeption.ItemJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class LancheService {

//===================================================================================================================//
//LancheMolho
	
	@Autowired
	private LancheMolhoRepository lancheMolhoRepository;
	
	//Get
	public Page<LancheMolhoResponse> listarLancheMolho(String tipoMolho, Pageable paginacao) {
		if(tipoMolho == null) {
			Page<LancheMolho> lancheMolho = lancheMolhoRepository.findAll(paginacao);
			return LancheMolhoResponse.converter(lancheMolho);
		} else {
			Page<LancheMolho> lancheMolho = lancheMolhoRepository.findByTipoMolhoIgnoreCase(tipoMolho, paginacao);
			return LancheMolhoResponse.converter(lancheMolho);
		}
	}
	
	//Get id
	public ResponseEntity<LancheMolhoResponse> detalharLancheMolhoPorId(Long id) {
		Optional<LancheMolho> lancheMolho = lancheMolhoRepository.findById(id);
		if (lancheMolho.isPresent()) {
			return ResponseEntity.ok(LancheMolhoResponse.converterUmLancheMolho(lancheMolho.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	//cadastrar
		public ResponseEntity<LancheMolhoResponse> cadastrarLancheMolho(LancheMolhoRegister lancheMolhoRegister,
				UriComponentsBuilder uriBuilder) throws Exception {
			LancheMolho lancheMolho = lancheMolhoRegister.converter();
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findByTipoMolhoIgnoreCase(lancheMolho.getTipoMolho());
			if (lancheMolhoOptional.isEmpty()) {
				lancheMolhoRepository.save(lancheMolho);
				URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(lancheMolho.getId()).toUri();
				return ResponseEntity.created(uri).body(new LancheMolhoResponse(lancheMolho));
			} else {
				throw new ItemJaExisteException("Molho já existe");
			}
		}
		
		//atualizar
		public ResponseEntity<LancheMolhoResponse> atualizarLancheMolho(Long id, LancheMolhoRegister lancheMolhoRegister) {
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(id);
			if (lancheMolhoOptional.isPresent()) {
				
				LancheMolho lancheMolho = lancheMolhoOptional.get();
				lancheMolho.setTipoMolho(lancheMolhoRegister.tipoMolho());
				lancheMolho.getIngrediente().setPeso(lancheMolhoRegister.peso());
				lancheMolho.getIngrediente().setDataValidade(lancheMolhoRegister.dataValidade());
				lancheMolho.getIngrediente().setPrecoVenda(lancheMolhoRegister.precoVenda());
				lancheMolhoRepository.save(lancheMolho);
				
				return ResponseEntity.ok(new LancheMolhoResponse(lancheMolho));
			}
			return ResponseEntity.notFound().build();
		}
	
	
	//deletar 
		public ResponseEntity<?> removerLancheMolho(Long id) {
			Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(id);
			if (lancheMolhoOptional.isPresent()) {
				lancheMolhoRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.notFound().build();
		}
		
//===================================================================================================================//
//LancheRecheio
	
		@Autowired
		private LancheRecheioRepository lancheRecheioRepository;
		
		//Get
		public Page<LancheRecheioResponse> listarLancheRecheio(String tipoRecheio, Pageable paginacao) {
			if(tipoRecheio == null) {
				Page<LancheRecheio> lancheRecheios = lancheRecheioRepository.findAll(paginacao);
				return LancheRecheioResponse.converter(lancheRecheios);
			} else {
				Page<LancheRecheio> lancheRecheio = lancheRecheioRepository.findByTipoRecheioIgnoreCase(tipoRecheio, paginacao);
				return LancheRecheioResponse.converter(lancheRecheio);
			}
		}
		
		//Get id
		public ResponseEntity<LancheRecheioResponse> detalharLancheRecheioPorId(Long id) {
			Optional<LancheRecheio> lancheRecheio = lancheRecheioRepository.findById(id);
			if (lancheRecheio.isPresent()) {
				return ResponseEntity.ok(LancheRecheioResponse.converterUmLancheMolho(lancheRecheio.get()));
			}
			return ResponseEntity.notFound().build();
		}
		
		//cadastrar
			public ResponseEntity<LancheRecheioResponse> cadastrarLancheRecheio(LancheRecheioRegister lancheRecheioRegister,
					UriComponentsBuilder uriBuilder) throws Exception {
				LancheRecheio lancheRecheio = lancheRecheioRegister.converter();
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findByTipoRecheioIgnoreCase(lancheRecheio.getTipoRecheio());
				if (lancheRecheioOptional.isEmpty()) {
					lancheRecheioRepository.save(lancheRecheio);
					URI uri = uriBuilder.path("/lanches/{id}").buildAndExpand(lancheRecheio.getId()).toUri();
					return ResponseEntity.created(uri).body(new LancheRecheioResponse(lancheRecheio));
				} else {
					throw new ItemJaExisteException("Recheio já existe");
				}
			}
			
			//atualizar
			public ResponseEntity<LancheRecheioResponse> atualizarLancheRecheio(Long id,
					@Valid LancheRecheioRegister lancheRecheioRegister) {
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(id);
				if (lancheRecheioOptional.isPresent()) {
					
					LancheRecheio lancheRecheio = lancheRecheioOptional.get();
					lancheRecheio.setTipoRecheio(lancheRecheioRegister.tipoRecheio());
					lancheRecheio.getIngrediente().setPeso(lancheRecheioRegister.peso());
					lancheRecheio.getIngrediente().setDataValidade(lancheRecheioRegister.dataValidade());
					lancheRecheio.getIngrediente().setPrecoVenda(lancheRecheioRegister.precoVenda());
					lancheRecheioRepository.save(lancheRecheio);
					
					return ResponseEntity.ok(new LancheRecheioResponse(lancheRecheio));
				}
				return ResponseEntity.notFound().build();
			}
		
		//deletar 
			public ResponseEntity<?> removerLancheRecheio(Long id) {
				Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(id);
				if (lancheRecheioOptional.isPresent()) {
					lancheRecheioRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}
				return ResponseEntity.notFound().build();
			}

		
//===================================================================================================================//
//LancheTipoPao
		
			@Autowired
			private LancheTipoPaoRepository lancheTipoPaoRepository;
			
			//Get
			public Page<LancheTipoPaoResponse> listarLancheTipoPao(String tipoPao, Pageable paginacao) {
				if(tipoPao == null) {
					Page<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findAll(paginacao);
					return LancheTipoPaoResponse.converter(lancheTipoPao);
				} else {
					Page<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findByTipoPaoIgnoreCase(tipoPao, paginacao);
					return LancheTipoPaoResponse.converter(lancheTipoPao);
				}
			}
			
			//Get id
			public ResponseEntity<LancheTipoPaoResponse> detalharLancheTipoPaoPorId(Long id) {
				Optional<LancheTipoPao> lancheTipoPao = lancheTipoPaoRepository.findById(id);
				if (lancheTipoPao.isPresent()) {
					return ResponseEntity.ok(LancheTipoPaoResponse.converterUmLancheMolho(lancheTipoPao.get()));
				}
				return ResponseEntity.notFound().build();
			}
			
			//cadastrar
				public ResponseEntity<LancheTipoPaoResponse> cadastrarLancheTipoPao(LancheTipoPaoRegister lancheTipoPaoRegister,
						UriComponentsBuilder uriBuilder) throws Exception {
					LancheTipoPao lancheTipoPao = lancheTipoPaoRegister.converter();
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findByTipoPaoIgnoreCase(lancheTipoPao.getTipoPao());
					if (lancheTipoPaoOptional.isEmpty()) {
						lancheTipoPaoRepository.save(lancheTipoPao);
						URI uri = uriBuilder.path("/agencias/{id}").buildAndExpand(lancheTipoPao.getId()).toUri();
						return ResponseEntity.created(uri).body(new LancheTipoPaoResponse(lancheTipoPao));
					} else {
						throw new ItemJaExisteException("TipoPao já existe");
					}
				}
				
			//atualizar 	
				public ResponseEntity<LancheTipoPaoResponse> atualizarLancheTipoPao(Long id,
						@Valid LancheTipoPaoRegister lancheTipoPaoRegister) {
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(id);
					if (lancheTipoPaoOptional.isPresent()) {
						
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
						lancheTipoPao.setTipoPao(lancheTipoPaoRegister.tipoPao());
						lancheTipoPao.getIngrediente().setPeso(lancheTipoPaoRegister.peso());
						lancheTipoPao.getIngrediente().setDataValidade(lancheTipoPaoRegister.dataValidade());
						lancheTipoPao.getIngrediente().setPrecoVenda(lancheTipoPaoRegister.precoVenda());
						lancheTipoPaoRepository.save(lancheTipoPao);
						
						return ResponseEntity.ok(new LancheTipoPaoResponse(lancheTipoPao));
					}
					return ResponseEntity.notFound().build();
				}
			
			//deletar 
				public ResponseEntity<?> removerLancheTipoPao(Long id) {
					Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(id);
					if (lancheTipoPaoOptional.isPresent()) {
						lancheTipoPaoRepository.deleteById(id);
						return ResponseEntity.ok().build();
					}
					return ResponseEntity.notFound().build();
				}

}
