package lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record SalgadinhoTipoPreparoRegister(
        @NotNull
        @NotEmpty
        @Length(min = 3, max = 80)
        String tipoPreparo,

        @DecimalMin(value = "0.1", inclusive = true)
        @Digits(integer=3, fraction=2)
                BigDecimal precoVenda,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                String dataValidade,

        @DecimalMin(value = "0.1", inclusive = true)
        @Digits(integer=4, fraction=2)
        double peso

) {
        public SalgadinhoTipoPreparo converter() {
                return new SalgadinhoTipoPreparo(precoVenda, dataValidade, peso, tipoPreparo);
        }
}
