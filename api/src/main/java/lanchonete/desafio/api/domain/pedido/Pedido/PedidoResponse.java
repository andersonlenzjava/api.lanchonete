package lanchonete.desafio.api.domain.pedido.Pedido;

import java.math.BigDecimal;

import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.data.domain.Page;


public record PedidoResponse (
		Long pedidoId,
		String nomeCliente,
		BigDecimal valorTotalServico,
		BigDecimal valorPago,
		BigDecimal troco,
		StatusPedido statusPedido) {

	public PedidoResponse(Pedido pedido) {
		this (
				pedido.getId(),
				pedido.getNomeCliente(),
				pedido.getValorTotalServico(),
				pedido.getValorPago(),
				pedido.getTroco(),
				pedido.getStatusPedido()
		);
	}
	
	public static Page<PedidoResponse> converter(Page<Pedido> pedidos) {
		return pedidos.map(PedidoResponse::new);
	}
	public static PedidoResponse converterUmPedido(Pedido pedido) {
		return new PedidoResponse(pedido);
	}
}
