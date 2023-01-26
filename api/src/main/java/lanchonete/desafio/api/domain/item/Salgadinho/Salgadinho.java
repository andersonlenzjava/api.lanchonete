package lanchonete.desafio.api.domain.item.Salgadinho;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
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

    public Salgadinho(Pedido pedido, SalgadinhoMassa salgadinhoMassa, SalgadinhoRecheio salgadinhoRecheio, SalgadinhoTipoPreparo salgadinhoTipoPreparo) throws ItemVencidoException {
        this.pedido = pedido;
        this.salgadinhoMassa = salgadinhoMassa;
        this.salgadinhoRecheio = salgadinhoRecheio;
        this.salgadinhoTipoPreparo = salgadinhoTipoPreparo;

        this.VerificaValidade();

        this.item = new Item();

        this.item.setDataValidade(LocalDate.now().plusDays(4));

        this.CalculosSalgadinho();
    }

    public void CalculosSalgadinho() {
        this.item.setTotalItem(salgadinhoMassa.getIngrediente().getPrecoVenda()
                .add(salgadinhoRecheio.getIngrediente().getPrecoVenda()
                        .add(salgadinhoTipoPreparo.getIngrediente().getPrecoVenda())));

        this.item.setPesoItem(salgadinhoMassa.getIngrediente().getPeso() +
                salgadinhoRecheio.getIngrediente().getPeso() +
                salgadinhoTipoPreparo.getIngrediente().getPeso());
    }

    public void VerificaValidade() throws ItemVencidoException {
        this.salgadinhoMassa.getIngrediente().verificaValidade(this.salgadinhoMassa.getTipoMassa());
        this.salgadinhoRecheio.getIngrediente().verificaValidade(this.salgadinhoRecheio.getTipoRecheio());
        this.salgadinhoTipoPreparo.getIngrediente().verificaValidade(this.salgadinhoTipoPreparo.getTipoPreparo());
    }

}
