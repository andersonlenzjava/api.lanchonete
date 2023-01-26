package lanchonete.desafio.api.domain.item.Pizza;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolho;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import lanchonete.desafio.api.infra.exeption.ItemVencidoException;
import lombok.*;

import java.time.LocalDate;

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

    public Pizza(Pedido pedido, PizzaBorda pizzaBorda, PizzaMolho pizzaMolho, PizzaRecheio pizzaRecheio) throws ItemVencidoException {
        this.pedido = pedido;
        this.pizzaBorda = pizzaBorda;
        this.pizzaMolho = pizzaMolho;
        this.pizzaRecheio = pizzaRecheio;

        this.VerificaValidade();

        this.item = new Item();

        this.item.setDataValidade(LocalDate.now().plusDays(4));

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

    public void VerificaValidade() throws ItemVencidoException {
        this.pizzaBorda.getIngrediente().verificaValidade(this.pizzaBorda.getTipoBorda());
        this.pizzaMolho.getIngrediente().verificaValidade(this.pizzaMolho.getTipoMolho());
        this.pizzaRecheio.getIngrediente().verificaValidade(this.pizzaRecheio.getTipoRecheio());
    }

}
