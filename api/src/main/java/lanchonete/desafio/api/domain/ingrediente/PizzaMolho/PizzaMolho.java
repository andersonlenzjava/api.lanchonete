package lanchonete.desafio.api.domain.ingrediente.PizzaMolho;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.ingrediente.Ingrediente;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PizzaMolho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoMolho;

    public PizzaMolho(BigDecimal precoVenda, String dataValidade, double peso, String tipoMolho) {
        this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
        this.tipoMolho = tipoMolho;
    }
}
