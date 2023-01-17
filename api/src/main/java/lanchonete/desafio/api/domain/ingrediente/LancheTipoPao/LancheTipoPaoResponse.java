package lanchonete.desafio.api.domain.ingrediente.LancheTipoPao;

import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheio;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record LancheTipoPaoResponse(Long id,
                                    String tipoPao,
                                    BigDecimal precoVenda,
                                    String dataValidade,
                                    double peso) {
    public LancheTipoPaoResponse (LancheTipoPao lancheTipoPao) {
        this(lancheTipoPao.getId(),
                lancheTipoPao.getTipoPao(),
                lancheTipoPao.getIngrediente().getPrecoVenda(),
                lancheTipoPao.getIngrediente().getDataValidade(),
                lancheTipoPao.getIngrediente().getPeso());
    }

    public static Page<LancheTipoPaoResponse> converter(Page<LancheTipoPao> tipoPaos) {
        return tipoPaos.map(LancheTipoPaoResponse::new);
    }

    public static LancheTipoPaoResponse converterUmLancheMolho(LancheTipoPao tipoPaos) {
        return new LancheTipoPaoResponse(tipoPaos);
    }

}
