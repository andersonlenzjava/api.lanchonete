package lanchonete.desafio.api.domain.item.Pizza;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Item item;
    @ManyToOne
    Pedido pedido;
    @ManyToOne
    private PizzaBorda pizzaBorda;
    @ManyToOne
    private PizzaMolho pizzaMolho;
    @ManyToOne
    private PizzaRecheio pizzaRecheio;

    public Pizza(Pedido pedido, PizzaBorda pizzaBorda, PizzaMolho pizzaMolho, PizzaRecheio pizzaRecheio) {
        this.pedido = pedido;
        this.pizzaBorda = pizzaBorda;
        this.pizzaMolho = pizzaMolho;
        this.pizzaRecheio = pizzaRecheio;
        this.item = new Item();
        this.CalculosPizza();
    }

    public void CalculosPizza() {
        this.item.setTotalItem(pizzaBorda.getIngrediente().getPrecoVenda()
                .add(pizzaMolho.getIngrediente().getPrecoVenda()
                        .add(pizzaRecheio.getIngrediente().getPrecoVenda())));

        this.item.setPesoItem(pizzaBorda.getIngrediente().getPeso() +
                pizzaMolho.getIngrediente().getPeso() +
                pizzaRecheio.getIngrediente().getPeso());
    }

}
