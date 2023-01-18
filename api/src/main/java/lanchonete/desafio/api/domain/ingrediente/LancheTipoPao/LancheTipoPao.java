package lanchonete.desafio.api.domain.ingrediente.LancheTipoPao;

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
public class LancheTipoPao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoPao;

    public LancheTipoPao(BigDecimal precoVenda, String dataValidade, double peso, String tipoPao) {
        this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
        this.tipoPao = tipoPao;
    }
}
