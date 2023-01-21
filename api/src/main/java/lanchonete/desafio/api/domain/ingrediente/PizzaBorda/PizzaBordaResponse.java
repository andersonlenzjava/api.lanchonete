package lanchonete.desafio.api.domain.ingrediente.PizzaBorda;

import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPao;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record PizzaBordaResponse(
        Long id,
        String tipoBorda,
        BigDecimal precoVenda,
        String dataValidade,
        double peso) {
    public PizzaBordaResponse (PizzaBorda pizzaBorda) {
        this(pizzaBorda.getId(),
                pizzaBorda.getTipoBorda(),
                pizzaBorda.getIngrediente().getPrecoVenda(),
                pizzaBorda.getIngrediente().getDataValidade(),
                pizzaBorda.getIngrediente().getPeso());
    }

    public static Page<PizzaBordaResponse> converter(Page<PizzaBorda> pizzaBordas) {
        return pizzaBordas.map(PizzaBordaResponse::new);
    }

    public static PizzaBordaResponse converterUmLancheMolho(PizzaBorda pizzaBordas) {
        return new PizzaBordaResponse(pizzaBordas);
    }

}