package lanchonete.desafio.api.domain.ingrediente.LancheMolho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LancheMolhoRepository extends JpaRepository<LancheMolho, Long> {

    Page<LancheMolho> findByTipoMolhoIgnoreCase(String tipoMolho, Pageable paginacao);

    Optional<LancheMolho> findByTipoMolhoIgnoreCase(String tipoMolho);

}

