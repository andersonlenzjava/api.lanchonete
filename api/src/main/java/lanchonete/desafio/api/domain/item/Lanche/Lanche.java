package lanchonete.desafio.api.domain.item.Lanche;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Lanche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Item item;
    @ManyToOne
    Pedido pedido;
    @ManyToOne
    private LancheTipoPao lancheTipoPao;
    @ManyToOne
    private LancheRecheio lancheRecheio;
    @ManyToOne
    private LancheMolho lancheMolho;

    public Lanche(Pedido pedido, LancheTipoPao lancheTipoPao, LancheRecheio lancheRecheio, LancheMolho lancheMolho) {
        this.pedido = pedido;
        this.lancheTipoPao = lancheTipoPao;
        this.lancheRecheio = lancheRecheio;
        this.lancheMolho = lancheMolho;
        this.item = new Item();
        this.CalculosLanche();
    }

    public void CalculosLanche() {
        this.item.setTotalItem(   // se refere a esta classe
                lancheTipoPao.getIngrediente().getPrecoVenda()
                        .add(lancheRecheio.getIngrediente().getPrecoVenda()
                                .add(lancheMolho.getIngrediente().getPrecoVenda())));

        this.item.setPesoItem(
                lancheTipoPao.getIngrediente().getPeso() +
                        lancheRecheio.getIngrediente().getPeso() +
                        lancheMolho.getIngrediente().getPeso()); ;

    }

}
