package lanchonete.desafio.api.domain.pedido.Pedido;

import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.Salgadinho.Salgadinho;
import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record PedidoCompletoResponse (
        Long pedidoId,
        String nomeCliente,
        StatusPedido statusPedido,
        BigDecimal valorTotalServico,
        BigDecimal valorPago,
        BigDecimal troco,
        List<Lanche> listaLanche,
        List<Pizza> listaPizza,
        List<Salgadinho> listaSalgadinho) {

    public PedidoCompletoResponse(
            Pedido pedido,
            List<Lanche> lanches,
            List<Pizza> pizzas,
            List<Salgadinho> salgadinhos) {
        this(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getStatusPedido(),
                pedido.getValorTotalServico(),
                pedido.getValorPago(),
                pedido.getTroco(),
                lanches,
                pizzas,
                salgadinhos
                );
    }

    public static PedidoCompletoResponse converterUmPedido(
            Pedido pedido,
            List<Lanche> listaLanche,
            List<Pizza> listaPizza,
            List<Salgadinho> listaSalgadinho) {
        return new PedidoCompletoResponse(pedido, listaLanche, listaPizza, listaSalgadinho);
    }
}
