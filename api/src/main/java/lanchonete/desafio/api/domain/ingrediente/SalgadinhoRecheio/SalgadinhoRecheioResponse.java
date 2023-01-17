package lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio;

import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassa;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record SalgadinhoRecheioResponse(Long id,
                                      String tipoRecheio,
                                      BigDecimal precoVenda,
                                      String dataValidade,
                                      double peso) {
    public SalgadinhoRecheioResponse (SalgadinhoRecheio salgadinhoRecheio) {
        this(salgadinhoRecheio.getId(),
                salgadinhoRecheio.getTipoRecheio(),
                salgadinhoRecheio.getIngrediente().getPrecoVenda(),
                salgadinhoRecheio.getIngrediente().getDataValidade(),
                salgadinhoRecheio.getIngrediente().getPeso());
    }

    public static Page<SalgadinhoRecheioResponse> converter(Page<SalgadinhoRecheio> salgadinhoRecheios) {
        return salgadinhoRecheios.map(SalgadinhoRecheioResponse::new);
    }

    public static SalgadinhoRecheioResponse converterUmLancheMolho(SalgadinhoRecheio salgadinhoRecheios) {
        return new SalgadinhoRecheioResponse(salgadinhoRecheios);
    }

}