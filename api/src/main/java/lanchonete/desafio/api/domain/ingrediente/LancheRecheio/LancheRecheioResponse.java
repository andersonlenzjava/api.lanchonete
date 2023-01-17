package lanchonete.desafio.api.domain.ingrediente.LancheRecheio;

import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolho;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record LancheRecheioResponse(Long id,
                                  String tipoRecheio,
                                  BigDecimal precoVenda,
                                  String dataValidade,
                                  double peso) {
    public LancheRecheioResponse (LancheRecheio lancheRecheio) {
        this(lancheRecheio.getId(),
                lancheRecheio.getTipoRecheio(),
                lancheRecheio.getIngrediente().getPrecoVenda(),
                lancheRecheio.getIngrediente().getDataValidade(),
                lancheRecheio.getIngrediente().getPeso());
    }

    public static Page<LancheRecheioResponse> converter(Page<LancheRecheio> recheios) {
        return recheios.map(LancheRecheioResponse::new);
    }

    public static LancheRecheioResponse converterUmLancheMolho(LancheRecheio lancheRecheio) {
        return new LancheRecheioResponse(lancheRecheio);
    }

}
