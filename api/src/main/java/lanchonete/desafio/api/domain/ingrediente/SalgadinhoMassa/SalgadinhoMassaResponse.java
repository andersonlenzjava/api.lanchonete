package lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa;

import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheio;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record SalgadinhoMassaResponse(Long id,
                                   String tipoMassa,
                                   BigDecimal precoVenda,
                                   String dataValidade,
                                   double peso) {
    public SalgadinhoMassaResponse (SalgadinhoMassa salgadinhoMassa) {
        this(salgadinhoMassa.getId(),
                salgadinhoMassa.getTipoMassa(),
                salgadinhoMassa.getIngrediente().getPrecoVenda(),
                salgadinhoMassa.getIngrediente().getDataValidade(),
                salgadinhoMassa.getIngrediente().getPeso());
    }

    public static Page<SalgadinhoMassaResponse> converter(Page<SalgadinhoMassa> salgadinhoMassas) {
        return salgadinhoMassas.map(SalgadinhoMassaResponse::new);
    }

    public static SalgadinhoMassaResponse converterUmLancheMolho(SalgadinhoMassa salgadinhoMassas) {
        return new SalgadinhoMassaResponse(salgadinhoMassas);
    }

}
