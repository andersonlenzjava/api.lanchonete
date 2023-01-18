package lanchonete.desafio.api.domain.pedido.Pedido;

import lanchonete.desafio.api.domain.pedido.StatusPedido.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT u FROM Pedido u WHERE u.statusPedido = :status ")
    Page<Pedido> findbyStatusPedido(@Param("status") StatusPedido status, Pageable paginacao);


//    Page<Pedido> findbyStatusPedido(StatusPedido status, Pageable paginacao);
    
}
