package lanchonete.desafio.api.domain.item.Salgadinho;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MontarSalgadinhoRegister(
        @NotNull
        @Min(value = 0)
        Long pedidoId,

        @NotNull
        @Min(value = 0)
        Long salgadinhoMassaId,

        @NotNull
        @Min(value = 0)
        Long salgadinhoRecheioId,

        @NotNull
        @Min(value = 0)
        Long salgadinhoTipoPreparoId

) {
}
