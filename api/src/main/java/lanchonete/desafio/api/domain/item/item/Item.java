package lanchonete.desafio.api.domain.item.item;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public class Item {

    private BigDecimal totalItem = BigDecimal.ZERO;
    private LocalDate dataValidade = LocalDate.now();
    private double pesoItem;

}
