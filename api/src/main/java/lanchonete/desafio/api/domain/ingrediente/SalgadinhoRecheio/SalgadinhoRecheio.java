package lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio;

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
public class SalgadinhoRecheio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    public String tipoRecheio;

    public SalgadinhoRecheio(BigDecimal precoVenda, String dataValidade, double peso, String tipoRecheio) {
        this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
        this.tipoRecheio = tipoRecheio;
    }
}
