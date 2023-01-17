package lanchonete.desafio.api.domain.ingrediente.LancheMolho;

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
public class LancheMolho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoMolho;

    public LancheMolho(BigDecimal precoVenda, String dataValidade, double peso, String tipoMolho) {
        this.ingrediente.setPrecoVenda(precoVenda);
        this.ingrediente.setDataValidade(dataValidade);
        this.ingrediente.setPeso(peso);
        this.tipoMolho = tipoMolho;

    }
}
