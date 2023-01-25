package lanchonete.desafio.api.domain.item.Pizza;

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
public interface PizzaRepository extends JpaRepository<Pizza, Long>  {

    @Query("SELECT u FROM Pizza u WHERE u.pedido.id = :pedidoId ")
    Page<Pizza> findPizzasByPedidoNumero(@Param("pedidoId") Long pedidoId, Pageable paginacao);

    @Query("SELECT u FROM Pizza u WHERE u.pedido.id =:pedidoId AND u.id=:pizzaId ")
    Optional<Pizza> findUmaPizzaPorIdEPedido(@Param("pedidoId") Long pedidoId, @Param("pizzaId") Long pizzaId);

    @Modifying
    @Query("DELETE FROM Pizza u WHERE u.pedido.id=:pedidoId ")
    void deletePizzaByPedidoId(@Param("pedidoId") Long pedidoId);

    @Query("SELECT u FROM Pizza u WHERE u.pedido.id =:pedidoId ")
    Page<Pizza> findListPizzasPedidoPage(Long pedidoId, Pageable paginacao);

    @Query("SELECT u FROM Pizza u WHERE u.pedido.id =:pedidoId ")
    List<Pizza> findListPizzasPedido(Long pedidoId);
}
