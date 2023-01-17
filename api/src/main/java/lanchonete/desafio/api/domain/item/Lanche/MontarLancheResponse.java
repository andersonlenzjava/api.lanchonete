package lanchonete.desafio.api.domain.item.Lanche;

import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoResponse;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import org.springframework.data.domain.Page;

public record MontarLancheResponse (
        Long id,
        Item item,
        Pedido pedido,
        LancheTipoPao lancheTipoPao,
        LancheRecheio lancheRecheio,
        LancheMolho lancheMolho) {

    public MontarLancheResponse (Lanche lanche) {
        this (lanche.getId(),
                lanche.getItem(),
                lanche.getPedido(),
                lanche.getLancheTipoPao(),
                lanche.getLancheRecheio(),
                lanche.getLancheMolho());
    }

    public static Page<MontarLancheResponse> converter(Page<Lanche> lanches) {
        return lanches.map(MontarLancheResponse::new);
    }

    public static MontarLancheResponse converterUmLanche(Lanche lanche) {
        return new MontarLancheResponse(lanche);
    }

}
