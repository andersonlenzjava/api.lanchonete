package lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa;

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
public class SalgadinhoMassa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoMassa;

    public SalgadinhoMassa(BigDecimal precoVenda, String dataValidade, double peso, String tipoMassa) {
        this.ingrediente.setPrecoVenda(precoVenda);
        this.ingrediente.setDataValidade(dataValidade);
        this.ingrediente.setPeso(peso);
        this.tipoMassa = tipoMassa;
    }
}
