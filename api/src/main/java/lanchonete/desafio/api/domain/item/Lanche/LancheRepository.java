package lanchonete.desafio.api.domain.item.Lanche;

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
public interface LancheRepository extends JpaRepository<Lanche, Long> {

    @Query("SELECT u FROM Lanche u WHERE u.pedido.id = :pedidoId ")
    Page<Lanche> findLanchesByPedidoNumero(@Param("pedidoId") Long pedidoId, Pageable paginacao);

    @Query("SELECT u FROM Lanche u WHERE u.pedido.id = :pedidoId AND u.id=:lancheId ")
    Optional<Lanche> findUmLanchePorIdEPedido(@Param("pedidoId") Long pedidoId, @Param("lancheId") Long lancheId);

    @Modifying
    @Query("DELETE FROM Lanche u WHERE u.pedido.id=:pedidoId ")
    void deleteLancheByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT u FROM Lanche u WHERE u.pedido.id = :pedidoId ")
    Page<Lanche> findListLanchesPedidoPage(Long pedidoId, Pageable paginacao);

    @Query("SELECT u FROM Lanche u WHERE u.pedido.id = :pedidoId ")
    List<Lanche> findListLanchesPedido(Long pedidoId);
}
