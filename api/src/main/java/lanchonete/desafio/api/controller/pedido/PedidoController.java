package lanchonete.desafio.api.controller.pedido;

import java.math.BigDecimal;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.pedido.Pedido.PedidoCompletoRegister;
import lanchonete.desafio.api.domain.pedido.Pedido.PedidoCompletoResponse;
import lanchonete.desafio.api.domain.pedido.Pedido.PedidoResponse;
import lanchonete.desafio.api.infra.exeption.ValorPagoInsuficienteException;
import lanchonete.desafio.api.service.pedido.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public Page<PedidoResponse> listarPedidos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidos(paginacao);
	}
	
	@GetMapping("/porId/{pedidoId}")
	public ResponseEntity<PedidoResponse> listarPedidoPorId(@PathVariable Long pedidoId) {
		return pedidoService.listarPedidoPorId(pedidoId);
	}
	
	@GetMapping("/pedidoCompleto/porId/{pedidoId}")
	public ResponseEntity<PedidoCompletoResponse> listarPedidoCompletoPorId(@PathVariable Long pedidoId) {
		return pedidoService.listarPedidoCompletoPorId(pedidoId);
	}
	
	@GetMapping("/abertos")
	public Page<PedidoResponse> listarPedidosAbertos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidosAbertos(paginacao);
	}
	
	@GetMapping("/processando")
	public Page<PedidoResponse> listarPedidosProcessando(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidosProcessando(paginacao);
	}
	
	@GetMapping("/pagofinalizado")
	public Page<PedidoResponse> listarPedidosPagoFinalizado(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10)
	Pageable paginacao) {
		return pedidoService.listarPedidosPagoFinalizado(paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoResponse> cadastrarPedido(@RequestBody @Valid PedidoCompletoRegister pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.cadastrarPedido(pedidoForm, uriBuilder);
	}
	
	@PutMapping
	@Transactional 
	public ResponseEntity<PedidoResponse> atualizarPedido(@RequestParam(required = true) Long pedidoId, @RequestBody @Valid PedidoCompletoRegister pedidoForm,
			UriComponentsBuilder uriBuilder) {
		return pedidoService.atualizarPedido(pedidoId, pedidoForm, uriBuilder);
	}
	
	@GetMapping("/calculaTroco/{pedidoId}")
	@Transactional
	public ResponseEntity<BigDecimal> retornaCalculoTrocoPedido(@PathVariable Long pedidoId, @RequestParam(required = true) BigDecimal valorPago) throws ValorPagoInsuficienteException {
		return pedidoService.retornaCalculoTrocoPedido(pedidoId, valorPago);
	} // estado pago se aprovar 

	//deletar um pedido  // remover os itens deste pedido também idependente do seu status 
	@DeleteMapping("/{pedidoId}")
	@Transactional
	public ResponseEntity<?> removerPedido(@PathVariable Long pedidoId) {
		return pedidoService.removerPedido(pedidoId);
	}
	
}
