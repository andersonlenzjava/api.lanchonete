package lanchonete.desafio.api.domain.ingrediente.PizzaMolho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaMolhoRepository extends JpaRepository<PizzaMolho, Long> {

    Optional<PizzaMolho> findByTipoMolhoIgnoreCase(String tipoMolho);

    Page<PizzaMolho> findByTipoMolhoIgnoreCase(String tipoMolho, Pageable paginacao);

}
