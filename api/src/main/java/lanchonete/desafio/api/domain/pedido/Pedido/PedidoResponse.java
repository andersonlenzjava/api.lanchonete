package lanchonete.desafio.api.domain.pedido.Pedido;

import java.math.BigDecimal;

import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.data.domain.Page;


public class PedidoResponse {

	Long pedidoId;
	private String nomeCliente;
	private BigDecimal valorTotalServico = BigDecimal.ZERO;
	private BigDecimal valorPago = BigDecimal.ZERO;
	private BigDecimal troco = BigDecimal.ZERO;
	private StatusPedido statusPedido = StatusPedido.ABERTO;
	
	public PedidoResponse(Pedido pedido) {
		this.pedidoId = pedido.getId();
		this.nomeCliente = pedido.getNomeCliente();
		this.valorTotalServico = pedido.getValorTotalServico();
		this.valorPago = pedido.getValorPago();
		this.troco = pedido.getTroco();
		this.statusPedido = pedido.getStatusPedido();
	}

	public Long getPedidoId() {
		return pedidoId;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public BigDecimal getValorTotalServico() {
		return valorTotalServico;
	}
	public void setValorTotalServico(BigDecimal valorTotalServico) {
		this.valorTotalServico = valorTotalServico;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public BigDecimal getTroco() {
		return troco;
	}
	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	public static Page<PedidoResponse> converter(Page<Pedido> pedidos) {
		return pedidos.map(PedidoResponse::new);
	}
	public static PedidoResponse converterUmPedido(Pedido pedido) {
		return new PedidoResponse(pedido);
	}
}
