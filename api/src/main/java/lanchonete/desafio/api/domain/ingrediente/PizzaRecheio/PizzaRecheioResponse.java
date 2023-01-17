package lanchonete.desafio.api.domain.ingrediente.PizzaRecheio;

import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoReponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record PizzaRecheioResponse(Long id,
                                String tipoRecheio,
                                BigDecimal precoVenda,
                                String dataValidade,
                                double peso) {
    public PizzaRecheioResponse (PizzaRecheio pizzaRecheio) {
        this(pizzaRecheio.getId(),
                pizzaRecheio.getTipoRecheio(),
                pizzaRecheio.getIngrediente().getPrecoVenda(),
                pizzaRecheio.getIngrediente().getDataValidade(),
                pizzaRecheio.getIngrediente().getPeso());
    }

    public static Page<PizzaRecheioResponse> converter(Page<PizzaRecheio> pizzaRecheios) {
        return pizzaRecheios.map(PizzaRecheioResponse::new);
    }

    public static PizzaRecheioResponse converterUmLancheMolho(PizzaRecheio pizzaRecheios) {
        return new PizzaRecheioResponse(pizzaRecheios);
    }

}