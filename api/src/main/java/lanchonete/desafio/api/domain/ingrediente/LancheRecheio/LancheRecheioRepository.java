package lanchonete.desafio.api.domain.ingrediente.LancheRecheio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LancheRecheioRepository extends JpaRepository<LancheRecheio, Long> {

    Page<LancheRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio, Pageable paginacao);

    Optional<LancheRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio);


}