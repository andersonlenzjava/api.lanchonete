package lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo;

import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheio;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record SalgadinhoTipoPreparoResponse(Long id,
                                        String tipoPreparo,
                                        BigDecimal precoVenda,
                                        String dataValidade,
                                        double peso) {
    public SalgadinhoTipoPreparoResponse (SalgadinhoTipoPreparo salgadinhoTipoPreparo) {
        this(salgadinhoTipoPreparo.getId(),
                salgadinhoTipoPreparo.getTipoPreparo(),
                salgadinhoTipoPreparo.getIngrediente().getPrecoVenda(),
                salgadinhoTipoPreparo.getIngrediente().getDataValidade(),
                salgadinhoTipoPreparo.getIngrediente().getPeso());
    }

    public static Page<SalgadinhoTipoPreparoResponse> converter(Page<SalgadinhoTipoPreparo> salgadinhoTipoPreparos) {
        return salgadinhoTipoPreparos.map(SalgadinhoTipoPreparoResponse::new);
    }

    public static SalgadinhoTipoPreparoResponse converterUmLancheMolho(SalgadinhoTipoPreparo salgadinhoTipoPreparos) {
        return new SalgadinhoTipoPreparoResponse(salgadinhoTipoPreparos);
    }

}