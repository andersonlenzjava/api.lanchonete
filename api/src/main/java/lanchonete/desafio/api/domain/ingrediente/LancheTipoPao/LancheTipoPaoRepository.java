package lanchonete.desafio.api.domain.ingrediente.LancheTipoPao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LancheTipoPaoRepository extends JpaRepository<LancheTipoPao, Long> {

    Optional<LancheTipoPao> findByTipoPaoIgnoreCase(String tipoPao);

    Page<LancheTipoPao> findByTipoPaoIgnoreCase(String tipoPao, Pageable paginacao);


}
