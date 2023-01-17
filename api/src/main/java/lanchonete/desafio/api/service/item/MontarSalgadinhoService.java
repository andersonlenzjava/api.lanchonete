package lanchonete.desafio.api.service.item;

import java.net.URI;
import java.util.Optional;

import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaRepository;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioRepository;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoRepository;
import lanchonete.desafio.api.domain.item.Salgadinho.MontarSalgadinhoRegister;
import lanchonete.desafio.api.domain.item.Salgadinho.MontarSalgadinhoResponse;
import lanchonete.desafio.api.domain.item.Salgadinho.Salgadinho;
import lanchonete.desafio.api.domain.item.Salgadinho.SalgadinhoRepository;
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
public class MontarSalgadinhoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	SalgadinhoRepository salgadinhoRepository;
	
	@Autowired
	SalgadinhoMassaRepository salgadinhoMassaRepository;
	
	@Autowired
	SalgadinhoRecheioRepository salgadinhoRecheioRepository;
	
	@Autowired
	SalgadinhoTipoPreparoRepository salgadinhoTipoPreparoRepository;
	
	// get
	public Page<MontarSalgadinhoResponse> listarSalgadinhosPedido(Long pedidoId, Pageable paginacao) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			
			Page<Salgadinho> salgadinhos = salgadinhoRepository.findSalgadinhosByPedidoNumero(pedidoId, paginacao);
			return MontarSalgadinhoResponse.converter(salgadinhos);
		}
		return null;
	}

	//get id
	public ResponseEntity<MontarSalgadinhoResponse> listarSalgadinhoPedidoPorId(Long pedidoId, Long salgadinhoId) {

		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {
			Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findUmSalgadinhoPorIdEPedido(pedidoId, salgadinhoId);
			if (salgadinhoOptional.isPresent()) {
				return ResponseEntity.ok(MontarSalgadinhoResponse.converterUmSalgadinho(salgadinhoOptional.get()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	// Post
	public ResponseEntity<MontarSalgadinhoResponse> cadastrarSalgadinho(MontarSalgadinhoRegister montarSalgadinhoForm,
			UriComponentsBuilder uriBuilder) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.pedidoId());
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.salgadinhoMassaId());
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.salgadinhoRecheioId());
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.salgadinhoTipoPreparoId());

		if (pedidoOptional.isPresent()
				&& salgadinhoMassaOptional.isPresent() 
				&& salgadinhoRecheioOptional.isPresent() 
				&& salgadinhoTipoPreparoOptional.isPresent()) {
				
				Pedido pedido = pedidoOptional.get();

				if (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO) {
					
						SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
						SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
						SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
	
						Salgadinho salgadinho = new Salgadinho(pedido, salgadinhoMassa, salgadinhoRecheio, salgadinhoTipoPreparo);
						
						salgadinhoRepository.save(salgadinho); 
						
						pedido.adicionaSalgadinho(salgadinho); // operações com os saldos 
						
						pedidoRepository.save(pedido); 
	
						URI uri = uriBuilder.path("/pedido/salgadinhos/{id}").buildAndExpand(salgadinho.getId()).toUri();
						return ResponseEntity.created(uri).body(new MontarSalgadinhoResponse(salgadinho));
					}
				}
		return ResponseEntity.notFound().build();
	}

	// put
	public ResponseEntity<MontarSalgadinhoResponse> atualizarSalgadinho(Long salgadinhoId, MontarSalgadinhoRegister montarSalgadinhoForm) {
		
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(montarSalgadinhoForm.pedidoId());
		Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findById(salgadinhoId);
		Optional<SalgadinhoMassa> salgadinhoMassaOptional = salgadinhoMassaRepository.findById(montarSalgadinhoForm.salgadinhoMassaId());
		Optional<SalgadinhoRecheio> salgadinhoRecheioOptional = salgadinhoRecheioRepository.findById(montarSalgadinhoForm.salgadinhoRecheioId());
		Optional<SalgadinhoTipoPreparo> salgadinhoTipoPreparoOptional = salgadinhoTipoPreparoRepository.findById(montarSalgadinhoForm.salgadinhoTipoPreparoId());

		if (pedidoOptional.isPresent()
				&& salgadinhoOptional.isPresent()
				&& salgadinhoMassaOptional.isPresent() 
				&& salgadinhoRecheioOptional.isPresent() 
				&& salgadinhoTipoPreparoOptional.isPresent()) {
			
			Pedido pedido = pedidoOptional.get();
			Salgadinho salgadinho = salgadinhoOptional.get();
			
			if ((salgadinho.getPedido().getId() == pedido.getId()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
					pedido.removerSalgadinho(salgadinho); // operações com os saldos 
				
					SalgadinhoMassa salgadinhoMassa = salgadinhoMassaOptional.get();
					SalgadinhoRecheio salgadinhoRecheio = salgadinhoRecheioOptional.get();
					SalgadinhoTipoPreparo salgadinhoTipoPreparo = salgadinhoTipoPreparoOptional.get();
					
					salgadinho.setSalgadinhoMassa(salgadinhoMassa);
					salgadinho.setSalgadinhoRecheio(salgadinhoRecheio);
					salgadinho.setSalgadinhoTipoPreparo(salgadinhoTipoPreparo);
					salgadinho.CalculosSalgadinho();
					
					salgadinhoRepository.save(salgadinho);
					
					pedido.adicionaSalgadinho(salgadinho); // operações com os saldos 
					
					pedidoRepository.save(pedido); 
					
					return ResponseEntity.ok(new MontarSalgadinhoResponse(salgadinho));
					}
			}
		return ResponseEntity.notFound().build();
	}

	//delete
	public ResponseEntity<?> removerSalgadinhoPedido(Long pedidoId, Long salgadinhoId) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
		if (pedidoOptional.isPresent()) {

			Pedido pedido = pedidoOptional.get();
			Optional<Salgadinho> salgadinhoOptional = salgadinhoRepository.findUmSalgadinhoPorIdEPedido(pedidoId, salgadinhoId);

			if ((salgadinhoOptional.isPresent()) && (pedido.getStatusPedido() != StatusPedido.PAGOFINALIZADO)) {
				
				pedido.removerSalgadinho(salgadinhoOptional.get()); // operações com os saldos 
				salgadinhoRepository.delete(salgadinhoOptional.get());
				return ResponseEntity.ok().build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}
