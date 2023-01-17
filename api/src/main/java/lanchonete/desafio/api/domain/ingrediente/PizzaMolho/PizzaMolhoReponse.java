package lanchonete.desafio.api.domain.ingrediente.PizzaMolho;

import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBorda;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record PizzaMolhoReponse(Long id,
                                 String tipoMolho,
                                 BigDecimal precoVenda,
                                 String dataValidade,
                                 double peso) {
    public PizzaMolhoReponse (PizzaMolho pizzaMolho) {
        this(pizzaMolho.getId(),
                pizzaMolho.getTipoMolho(),
                pizzaMolho.getIngrediente().getPrecoVenda(),
                pizzaMolho.getIngrediente().getDataValidade(),
                pizzaMolho.getIngrediente().getPeso());
    }

    public static Page<PizzaMolhoReponse> converter(Page<PizzaMolho> pizzaMolhos) {
        return pizzaMolhos.map(PizzaMolhoReponse::new);
    }

    public static PizzaMolhoReponse converterUmLancheMolho(PizzaMolho pizzaMolhos) {
        return new PizzaMolhoReponse(pizzaMolhos);
    }

}