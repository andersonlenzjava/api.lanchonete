package lanchonete.desafio.api.domain.item.Lanche;

import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MontarLancheResponse (
        Long id,
        BigDecimal totalItem,
        LocalDate dataValidade,
        Double pesoItem,
        Long pedidoId,
        LancheTipoPao lancheTipoPao,
        LancheRecheio lancheRecheio,
        LancheMolho lancheMolho) {

    public MontarLancheResponse (Lanche lanche) {
        this (  lanche.getId(),
                lanche.getItem().getTotalItem(),
                lanche.getItem().getDataValidade(),
                lanche.getItem().getPesoItem(),
                lanche.getPedido().getId(),
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
