package lanchonete.desafio.api.domain.item.Pizza;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MontarPizzaRegister(
        @NotNull
        @Min(value = 0)
        Long pedidoId,

        @NotNull
        @Min(value = 0)
        Long pizzaBordaId,

        @NotNull
        @Min(value = 0)
        Long pizzaMolhoId,

        @NotNull
        @Min(value = 0)
        Long pizzaRecheioId

) {
}
