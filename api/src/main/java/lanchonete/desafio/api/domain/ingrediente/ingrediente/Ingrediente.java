package lanchonete.desafio.api.domain.ingrediente.ingrediente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    private BigDecimal precoVenda;
    private String dataValidade;
    private double peso;

}
