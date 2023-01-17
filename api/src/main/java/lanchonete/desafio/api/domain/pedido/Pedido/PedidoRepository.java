package lanchonete.desafio.api.domain.pedido.Pedido;

import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findbyStatusPedido(StatusPedido status, Pageable paginacao);
    
}
