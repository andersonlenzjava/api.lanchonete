package lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalgadinhoMassaRepository extends JpaRepository<SalgadinhoMassa, Long> {


    Optional<SalgadinhoMassa> findByTipoMassaIgnoreCase(String tipoMassa);


    Page<SalgadinhoMassa> findByTipoMassaIgnoreCase(String tipoMassa, Pageable paginacao);

}
