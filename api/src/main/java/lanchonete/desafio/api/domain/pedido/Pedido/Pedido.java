package lanchonete.desafio.api.domain.pedido.Pedido;

import jakarta.persistence.*;
import lanchonete.desafio.api.domain.item.Lanche.Lanche;
import lanchonete.desafio.api.domain.item.Pizza.Pizza;
import lanchonete.desafio.api.domain.item.Salgadinho.Salgadinho;
import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    private BigDecimal valorTotalServico = BigDecimal.ZERO;

    private BigDecimal valorPago = BigDecimal.ZERO;

    private BigDecimal troco = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido = StatusPedido.ABERTO;

    public Pedido(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void adicionaPizza(Pizza pizza) {
        this.valorTotalServico = this.valorTotalServico.add(pizza.getItem().getTotalItem()); // adiciona no total do pedido
    }

    public void adicionaSalgadinho(Salgadinho salgadinho) {
        this.valorTotalServico = this.valorTotalServico.add(salgadinho.getItem().getTotalItem());
    }

    public void adicionaLanche(Lanche lanche) {
        this.valorTotalServico = this.valorTotalServico.add(lanche.getItem().getTotalItem());
    }

    public void removerLanche(Lanche lanche) {
        this.valorTotalServico = this.valorTotalServico.subtract(lanche.getItem().getTotalItem());
    }

    public void removerPizza(Pizza pizza) {
        this.valorTotalServico = this.valorTotalServico.subtract(pizza.getItem().getTotalItem());
    }

    public void removerSalgadinho(Salgadinho salgadinho) {
        this.valorTotalServico = this.valorTotalServico.subtract(salgadinho.getItem().getTotalItem());
    }


    public BigDecimal calcularTroco(BigDecimal valorPago) throws Exception {
        if (valorPago.compareTo(this.valorTotalServico) == -1) {
            this.troco = BigDecimal.ZERO;
            throw new Exception("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
        } else if (valorPago.compareTo(this.valorTotalServico) == 0) {
            this.troco = BigDecimal.ZERO;
            this.valorPago = valorPago;
            this.statusPedido = StatusPedido.PAGOFINALIZADO;
        } else if (valorPago.compareTo(this.valorTotalServico) == 1) {
            this.troco = valorPago.subtract(this.valorTotalServico);
            this.valorPago = valorPago;
            this.statusPedido = StatusPedido.PAGOFINALIZADO;
        }
        System.out.println(troco);
        return this.troco;
    }

}
