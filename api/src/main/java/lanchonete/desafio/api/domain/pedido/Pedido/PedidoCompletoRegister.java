package lanchonete.desafio.api.domain.pedido.Pedido;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoCompletoRegister(
        @NotNull
        @NotEmpty
        String nomeCliente) {

        public Pedido converter() {
                return new Pedido(nomeCliente);
        }

}
