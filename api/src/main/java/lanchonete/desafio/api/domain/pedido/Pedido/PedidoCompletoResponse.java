package lanchonete.desafio.api.domain.pedido.Pedido;

import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheResponse;
import lanchonete.desafio.api.domain.item.Pizza.MontarPizzaResponse;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.Salgadinho.MontarSalgadinhoResponse;
import lanchonete.desafio.api.domain.item.Salgadinho.Salgadinho;
import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.data.domain.Page;

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
        Page<MontarLancheResponse> listaLanche,
        Page<MontarPizzaResponse> listaPizza,
        Page<MontarSalgadinhoResponse> listaSalgadinho) {

    public PedidoCompletoResponse(
            Pedido pedido,
            Page<Lanche> lanches,
            Page<Pizza> pizzas,
            Page<Salgadinho> salgadinhos) {
        this(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getStatusPedido(),
                pedido.getValorTotalServico(),
                pedido.getValorPago(),
                pedido.getTroco(),
                MontarLancheResponse.converter(lanches),
                MontarPizzaResponse.converter(pizzas),
                MontarSalgadinhoResponse.converter(salgadinhos)
                );
    }

    public static PedidoCompletoResponse converterUmPedido(
            Pedido pedido,
            Page<Lanche> listaLanche,
            Page<Pizza> listaPizza,
            Page<Salgadinho> listaSalgadinho) {
        return new PedidoCompletoResponse(pedido, listaLanche, listaPizza, listaSalgadinho);
    }
}
