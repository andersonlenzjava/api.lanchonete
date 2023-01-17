package lanchonete.desafio.api.domain.item.Salgadinho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalgadinhoRepository extends JpaRepository<Salgadinho, Long> {

    @Query("SELECT u FROM Salgadinho u WHERE u.pedido.id = :pedidoId ")
    Page<Salgadinho> findSalgadinhosByPedidoNumero(@Param("pedidoId") Long pedidoId, Pageable paginacao);

    @Query("SELECT u FROM Salgadinho u WHERE u.pedido.id = :pedidoId AND u.id = :salgadinhoId ")
    Optional<Salgadinho> findUmSalgadinhoPorIdEPedido(@Param("pedidoId") Long pedidoId, @Param("salgadinhoId") Long salgadinhoId);

    @Modifying
    @Query("DELETE FROM Salgadinho u WHERE u.pedido.id = :pedidoId ")
    void deleteSalgadinhoByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT u FROM Salgadinho u WHERE u.pedido.id = :pedidoId ")
    List<Salgadinho> findListSalgadinhosPedido(Long pedidoId);

}
