package lanchonete.desafio.api.domain.ingrediente.LancheMolho;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record LancheMolhoResponse(Long id,
                                  String tipoMolho,
                                  BigDecimal precoVenda,
                                  String dataValidade,
                                  double peso) {
    public LancheMolhoResponse (LancheMolho lancheMolho) {
        this(lancheMolho.getId(),
                lancheMolho.getTipoMolho(),
                lancheMolho.getIngrediente().getPrecoVenda(),
                lancheMolho.getIngrediente().getDataValidade(),
                lancheMolho.getIngrediente().getPeso());
    }

    public static Page<LancheMolhoResponse> converter(Page<LancheMolho> molhos) {
        return molhos.map(LancheMolhoResponse::new);
    }

    public static LancheMolhoResponse converterUmLancheMolho(LancheMolho lancheMolho) {
        return new LancheMolhoResponse(lancheMolho);
    }

}
