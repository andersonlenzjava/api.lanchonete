package lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalgadinhoRecheioRepository extends JpaRepository<SalgadinhoRecheio, Long> {

    Page<SalgadinhoRecheio> findByTipoRecheioIgnoreCase(String tipoRecheio, Pageable paginacao);

    Optional<SalgadinhoRecheio> findByTipoRecheioIgnoreCase(String tipoMassa);


}
