package lanchonete.desafio.api.domain.item.Salgadinho;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Salgadinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Item item;
    @ManyToOne
    Pedido pedido;
    @ManyToOne
    private SalgadinhoMassa salgadinhoMassa;
    @ManyToOne
    private SalgadinhoRecheio salgadinhoRecheio;
    @ManyToOne
    private SalgadinhoTipoPreparo salgadinhoTipoPreparo;

    public Salgadinho(Pedido pedido, SalgadinhoMassa salgadinhoMassa, SalgadinhoRecheio salgadinhoRecheio, SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
        this.pedido = pedido;
        this.salgadinhoMassa = salgadinhoMassa;
        this.salgadinhoRecheio = salgadinhoRecheio;
        this.salgadinhoTipoPreparo = salgadinhoTipoPreparo;
    }

    public void CalculosSalgadinho() {
        this.item.setTotalItem(salgadinhoMassa.getIngrediente().getPrecoVenda()
                .add(salgadinhoRecheio.getIngrediente().getPrecoVenda()
                        .add(salgadinhoTipoPreparo.getIngrediente().getPrecoVenda())));

        this.item.setPesoItem(salgadinhoMassa.getIngrediente().getPeso() +
                salgadinhoRecheio.getIngrediente().getPeso() +
                salgadinhoTipoPreparo.getIngrediente().getPeso());
    }

}
