package lanchonete.desafio.api.domain.ingrediente.PizzaBorda;

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
public class PizzaBorda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Ingrediente ingrediente;
    private String tipoBorda;

    public PizzaBorda(BigDecimal precoVenda, String dataValidade, double peso, String tipoBorda) {
        this.ingrediente = new Ingrediente(precoVenda, dataValidade, peso);
        this.tipoBorda = tipoBorda;
    }
}
