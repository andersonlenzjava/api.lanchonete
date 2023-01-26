package lanchonete.desafio.api.domain.ingrediente.ingrediente;

import jakarta.persistence.Embeddable;
import lanchonete.desafio.api.infra.exeption.ItemVencidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    private BigDecimal precoVenda;
    private String dataValidade;
    private double peso;

    public void verificaValidade (String nomeIngrediente) throws ItemVencidoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateValidade = LocalDate.parse(this.dataValidade,formatter);

        if (LocalDate.now().isAfter(dateValidade)) {
            throw new ItemVencidoException("O item : " + nomeIngrediente + " Está vencido!");
        }
    }
}
