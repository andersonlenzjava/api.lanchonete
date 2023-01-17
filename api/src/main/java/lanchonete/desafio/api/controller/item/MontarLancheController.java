package lanchonete.desafio.api.controller.item;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheRegister;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheResponse;
import lanchonete.desafio.api.service.item.MontarLancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedido/lanches")
public class MontarLancheController {
	
	@Autowired
	private MontarLancheService montarLancheService;
	
	@GetMapping
	public Page<MontarLancheResponse> listarLanchesPedido(@RequestParam(required = true) Long pedidoId,
														  @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return montarLancheService.listarLanchesPedido(pedidoId, paginacao);
	}
	
	@GetMapping("/{lancheId}") 
	public ResponseEntity<MontarLancheResponse> listarLanchePedidoPorId(@PathVariable Long lancheId,
			@RequestParam(required = true) Long pedidoId) {
		return montarLancheService.listarLanchePorId(pedidoId, lancheId);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MontarLancheResponse> cadastrarLanche(@RequestBody @Valid MontarLancheRegister montarLancheForm,
			UriComponentsBuilder uriBuilder) {
		return montarLancheService.cadastrarLanche(montarLancheForm, uriBuilder);
	}
	
	@PutMapping("/{lancheId}")
	@Transactional
	public ResponseEntity<MontarLancheResponse> atualizarLanche(@PathVariable Long lancheId, @RequestBody @Valid MontarLancheRegister montarLancheForm) {
		return montarLancheService.atualizarLanche(lancheId, montarLancheForm);
	}
	
	@DeleteMapping("/{lancheId}")
	@Transactional
	public ResponseEntity<?> removerLanchePedido(@RequestParam(required = true) Long pedidoId, @PathVariable Long lancheId) {
		return montarLancheService.removerLanchePedido(pedidoId, lancheId);
	}

}
