package lanchonete.desafio.api.domain.ingrediente.PizzaRecheio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRecheioRepository extends JpaRepository<PizzaRecheio, Long> {

    Page<PizzaRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio, Pageable paginacao);

    Optional<PizzaRecheio> findByTipoRecheioIgnoreCase(String tipoMolho);

}
