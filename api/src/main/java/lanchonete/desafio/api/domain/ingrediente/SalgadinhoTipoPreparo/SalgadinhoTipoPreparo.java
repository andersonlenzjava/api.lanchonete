package lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo;

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
public class SalgadinhoTipoPreparo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoPreparo;

    public SalgadinhoTipoPreparo(BigDecimal precoVenda, String dataValidade, double peso, String tipoPreparo) {
        this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
        this.tipoPreparo = tipoPreparo;
    }
}
