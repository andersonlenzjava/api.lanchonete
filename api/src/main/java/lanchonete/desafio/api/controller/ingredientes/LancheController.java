package lanchonete.desafio.api.controller.ingredientes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheMolho.LancheMolhoResponse;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheRecheio.LancheRecheioResponse;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoRegister;
import lanchonete.desafio.api.domain.ingrediente.LancheTipoPao.LancheTipoPaoResponse;
import lanchonete.desafio.api.service.ingredientes.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ingrediente/lanches")
public class LancheController {

    @Autowired
    private LancheService lancheService;

    @GetMapping("/molho")
    public Page<LancheMolhoResponse> listarMolho(@RequestParam(required = false) String tipoMolho,
                                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return lancheService.listarLancheMolho(tipoMolho, paginacao);
    }

    @GetMapping("/molho/{id}")
    public ResponseEntity<LancheMolhoResponse> detalharMolho(@PathVariable Long id) {
        return lancheService.detalharLancheMolhoPorId(id);
    }

    @PostMapping("/molho")
    @Transactional
    public ResponseEntity<LancheMolhoResponse> cadastrarMolho(@RequestBody @Valid LancheMolhoRegister lancheMolhoRegister,
                                                         UriComponentsBuilder uriBuilder) throws Exception {
        return lancheService.cadastrarLancheMolho(lancheMolhoRegister, uriBuilder);
    }

    @PutMapping("/molho/{id}")
    @Transactional
    public ResponseEntity<LancheMolhoResponse> atualizarLancheMolho(@PathVariable Long id, @RequestBody @Valid LancheMolhoRegister lancheMolhoRegister) {
        return lancheService.atualizarLancheMolho(id, lancheMolhoRegister);
    }


    @DeleteMapping("/molho/{id}")
    @Transactional
    public ResponseEntity<?> removerLancheMolho(@PathVariable Long id) {
        return lancheService.removerLancheMolho(id);
    }

    //================================================================================================================//

    @GetMapping("/recheio")
    public Page<LancheRecheioResponse> listarLancheRecheio(@RequestParam(required = false) String tipoRecheio,
                                                           @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return lancheService.listarLancheRecheio(tipoRecheio, paginacao);
    }

    @GetMapping("/recheio/{id}")
    public ResponseEntity<LancheRecheioResponse> detalharLancheRecheio(@PathVariable Long id) {
        return lancheService.detalharLancheRecheioPorId(id);
    }

    @PostMapping("/recheio")
    @Transactional
    public ResponseEntity<LancheRecheioResponse> cadastraLancheRecheio(@RequestBody @Valid LancheRecheioRegister lancheRecheioRegister,
                                                                  UriComponentsBuilder uriBuilder) throws Exception {
        return lancheService.cadastrarLancheRecheio(lancheRecheioRegister, uriBuilder);
    }

    @PutMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<LancheRecheioResponse> atualizarLancheRecheio(@PathVariable Long id, @RequestBody @Valid LancheRecheioRegister lancheRecheioRegister) {
        return lancheService.atualizarLancheRecheio(id, lancheRecheioRegister);
    }

    @DeleteMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<?> removerLancheRecheio(@PathVariable Long id) {
        return lancheService.removerLancheRecheio(id);
    }

    //================================================================================================================//

    @GetMapping("/tipoPao")
    public Page<LancheTipoPaoResponse> listarLancheTipoPao(@RequestParam(required = false) String tipoPao,
                                                           @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return lancheService.listarLancheTipoPao(tipoPao, paginacao);
    }

    @GetMapping("/tipoPao/{id}")
    public ResponseEntity<LancheTipoPaoResponse> detalharLancheTipoPao(@PathVariable Long id) {
        return lancheService.detalharLancheTipoPaoPorId(id);
    }

    @PostMapping("/tipoPao")
    @Transactional
    public ResponseEntity<LancheTipoPaoResponse> cadastrarLancheTipoPao(@RequestBody @Valid LancheTipoPaoRegister lancheTipoPaoRegister,
                                                                   UriComponentsBuilder uriBuilder) throws Exception {
        return lancheService.cadastrarLancheTipoPao(lancheTipoPaoRegister, uriBuilder);
    }

    @PutMapping("/tipoPao/{id}")
    @Transactional
    public ResponseEntity<LancheTipoPaoResponse> atualizarLancheTipoPao(@PathVariable Long id, @RequestBody @Valid LancheTipoPaoRegister lancheTipoPaoRegister) {
        return lancheService.atualizarLancheTipoPao(id, lancheTipoPaoRegister);
    }

    @DeleteMapping("/tipoPao/{id}")
    @Transactional
    public ResponseEntity<?> removerLancheTipoPao(@PathVariable Long id) {
        return lancheService.removerLancheTipoPao(id);
    }

}
