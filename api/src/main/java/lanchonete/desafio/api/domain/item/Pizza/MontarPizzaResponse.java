package lanchonete.desafio.api.domain.item.Pizza;

import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Lanche.MontarLancheResponse;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MontarPizzaResponse (
        Long id,
        BigDecimal totalItem,
        LocalDate dataValidade,
        Double pesoItem,
        Long pedidoId,
        PizzaBorda pizzaBorda,
        PizzaMolho pizzaMolho,
        PizzaRecheio pizzaRecheio) {

    public MontarPizzaResponse (Pizza pizza) {
        this (  pizza.getId(),
                pizza.getItem().getTotalItem(),
                pizza.getItem().getDataValidade(),
                pizza.getItem().getPesoItem(),
                pizza.getPedido().getId(),
                pizza.getPizzaBorda(),
                pizza.getPizzaMolho(),
                pizza.getPizzaRecheio());
    }

    public static Page<MontarPizzaResponse> converter(Page<Pizza> pizzas) {
        return pizzas.map(MontarPizzaResponse::new);
    }

    public static MontarPizzaResponse converterUmaPizza(Pizza pizza) {
        return new MontarPizzaResponse(pizza);
    }

}