package lanchonete.desafio.api.domain.item.Lanche;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MontarLancheRegister(
        @NotNull
        @Min(value = 0)
        Long pedidoId,

        @NotNull
        @Min(value = 0)
        Long lancheTipoPaoId,

        @NotNull
        @Min(value = 0)
        Long lancheRecheioId,

        @NotNull
        @Min(value = 0)
        Long lancheMolhoId

) {
}
