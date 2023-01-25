package lanchonete.desafio.api.domain.item.Salgadinho;

import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparo;
import lanchonete.desafio.api.domain.item.Pizza.MontarPizzaResponse;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.item.Item;
import lanchonete.desafio.api.domain.pedido.Pedido.Pedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MontarSalgadinhoResponse (
        Long id,
        BigDecimal totalItem,
        LocalDate dataValidade,
        Double pesoItem,
        Long pedidoId,
        SalgadinhoMassa salgadinhoMassa,
        SalgadinhoRecheio salgadinhoRecheio,
        SalgadinhoTipoPreparo salgadinhoTipoPreparo) {

    public MontarSalgadinhoResponse (Salgadinho salgadinho) {
        this (  salgadinho.getId(),
                salgadinho.getItem().getTotalItem(),
                salgadinho.getItem().getDataValidade(),
                salgadinho.getItem().getPesoItem(),
                salgadinho.getPedido().getId(),
                salgadinho.getSalgadinhoMassa(),
                salgadinho.getSalgadinhoRecheio(),
                salgadinho.getSalgadinhoTipoPreparo());
    }

    public static Page<MontarSalgadinhoResponse> converter(Page<Salgadinho> salgadinhos) {
        return salgadinhos.map(MontarSalgadinhoResponse::new);
    }

    public static MontarSalgadinhoResponse converterUmSalgadinho(Salgadinho salgadinho) {
        return new MontarSalgadinhoResponse(salgadinho);
    }

}