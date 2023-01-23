package lanchonete.desafio.api.service.item;

import java.net.URI;
import java.util.Optional;

import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoRepository;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioRepository;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoRepository;
import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Lanche.LancheRepository;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheRegister;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheResponse;
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
public class MontarLancheService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	LancheRepository lancheRepository;

	@Autowired
	LancheMolhoRepository lancheMolhoRepository;

	@Autowired
	LancheRecheioRepository lancheRecheioRepository;

	@Autowired
	LancheTipoPaoRepository lancheTipoPaoRepository;

	// Get todos
	public Page<MontarLancheResponse> listarLanchesPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Lanche> lanches = lancheRepository.findLanchesByPedidoNumero(pedidoId, paginacao);
			return MontarLancheResponse.converter(lanches);
		}
		return null;
	}

	// Get por id 
	public ResponseEntity<MontarLancheResponse> listarLanchePorId(Long pedidoId, Long lancheId) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Lanche> lancheOptional = lancheRepository.findUmLanchePorIdEPedido(pedidoId, lancheId);
			if (lancheOptional.isPresent()) {
				return ResponseEntity.ok(MontarLancheResponse.converterUmLanche(lancheOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// post
	public ResponseEntity<MontarLancheResponse> cadastrarLanche(MontarLancheRegister montarLancheRegister,
			UriComponentsBuilder uriBuilder) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheRegister.pedidoId());
		Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheRegister.lancheMolhoId());
		Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheRegister.lancheRecheioId());
		Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheRegister.lancheTipoPaoId());
		
		if (pedidoOptional.isPresent()
				&& lancheMolhoOptional.isPresent()
				&& lancheRecheioOptional.isPresent()
				&& lancheTipoPaoOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				
				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
	
						LancheMolho lancheMolho = lancheMolhoOptional.get();
						LancheRecheio lancheRecheio = lancheRecheioOptional.get();
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
	
						Lanche lanche = new Lanche(pedido, lancheTipoPao, lancheRecheio, lancheMolho);
						
						lancheRepository.save(lanche); // aqui o lanche passa a ter ID 
	
						pedido.adicionaLanche(lanche);
						
						pedidoRepository.save(pedido);
						
						URI uri = uriBuilder.path("/pedido/lanches/{id}").buildAndExpand(lanche.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarLancheResponse(lanche));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarLancheResponse> atualizarLanche(Long lancheId, MontarLancheRegister montarLancheRegister) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarLancheRegister.pedidoId());
		Optional<Lanche> lancheOptional = lancheRepository.findById(lancheId);
		Optional<LancheMolho> lancheMolhoOptional = lancheMolhoRepository.findById(montarLancheRegister.lancheMolhoId());
		Optional<LancheRecheio> lancheRecheioOptional = lancheRecheioRepository.findById(montarLancheRegister.lancheRecheioId());
		Optional<LancheTipoPao> lancheTipoPaoOptional = lancheTipoPaoRepository.findById(montarLancheRegister.lancheTipoPaoId());
		
		if (pedidoOptional.isPresent()
				&& lancheOptional.isPresent()
				&& lancheMolhoOptional.isPresent()
				&& lancheRecheioOptional.isPresent()
				&& lancheTipoPaoOptional.isPresent()) {
			
				Pedido pedido = pedidoOptional.get();
				Lanche lanche = lancheOptional.get();
			
				if ((lanche.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {

						pedido.removerLanche(lanche);
					
						LancheMolho lancheMolho = lancheMolhoOptional.get();
						LancheRecheio lancheRecheio = lancheRecheioOptional.get();
						LancheTipoPao lancheTipoPao = lancheTipoPaoOptional.get();
	
						lanche.setLancheMolho(lancheMolho);
						lanche.setLancheRecheio(lancheRecheio);
						lanche.setLancheTipoPao(lancheTipoPao);
						lanche.CalculosLanche();
						
						lancheRepository.save(lanche); 
						
						pedido.adicionaLanche(lanche);

						pedidoRepository.save(pedido);
	
						return ResponseEntity.ok(new MontarLancheResponse(lanche));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// delete
	public ResponseEntity<?> removerLanchePedido(Long pedidoId, Long lancheId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional<Lanche> lancheOptional = lancheRepository.findUmLanchePorIdEPedido(pedidoId, lancheId);

			if ((lancheOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerLanche(lancheOptional.get()); // operações com os saldos 
				lancheRepository.delete(lancheOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

}
