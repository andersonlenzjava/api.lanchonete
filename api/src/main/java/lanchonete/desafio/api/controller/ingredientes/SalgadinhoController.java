package lanchonete.desafio.api.controller.ingredientes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoMassa.SalgadinhoMassaResponse;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoRecheio.SalgadinhoRecheioResponse;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoRegister;
import lanchonete.desafio.api.domain.ingrediente.SalgadinhoTipoPreparo.SalgadinhoTipoPreparoResponse;
import lanchonete.desafio.api.service.ingredientes.SalgadinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ingrediente/salgadinhos")
public class SalgadinhoController {

    @Autowired
    private SalgadinhoService salgadinhoService;

    @GetMapping("/massa")
    public Page<SalgadinhoMassaResponse> listarSalgadinhoMassa(@RequestParam(required = false) String tipoMassa,
                                                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return salgadinhoService.listarSalgadinhoMassa(tipoMassa, paginacao);
    }

    @GetMapping("/massa/{id}")
    public ResponseEntity<SalgadinhoMassaResponse> detalharSalgadinhoMassa(@PathVariable Long id) {
        return salgadinhoService.detalharSalgadinhoMassa(id);
    }

    @PostMapping("/massa")
    @Transactional
    public ResponseEntity<SalgadinhoMassaResponse> cadastrarSalgadinhoMassa(@RequestBody @Valid SalgadinhoMassaRegister salgadinhoMassaForm,
                                                                            UriComponentsBuilder uriBuilder) throws Exception {
        return salgadinhoService.cadastrarSalgadinhoMassa(salgadinhoMassaForm, uriBuilder);
    }

    @PutMapping("/massa/{id}")
    @Transactional
    public ResponseEntity<SalgadinhoMassaResponse> atualizarSalgadinhoMassa(@PathVariable Long id, @RequestBody @Valid SalgadinhoMassaRegister salgadinhoMassaForm) {
        return salgadinhoService.atualizarSalgadinhoMassa(id, salgadinhoMassaForm);
    }

    @DeleteMapping("/massa/{id}")
    @Transactional
    public ResponseEntity<?> removerSalgadinhoMassa(@PathVariable Long id) {
        return salgadinhoService.removerSalgadinhoMassa(id);
    }

    //================================================================================================================//

    @GetMapping("/recheio")
    public Page<SalgadinhoRecheioResponse> listarSalgadinhoRecheio(@RequestParam(required = false) String tipoRecheio,
                                                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return salgadinhoService.listarSalgadinhoRecheio(tipoRecheio, paginacao);
    }

    @GetMapping("/recheio/{id}")
    public ResponseEntity<SalgadinhoRecheioResponse> detalharSalgadinhoRecheio(@PathVariable Long id) {
        return salgadinhoService.detalharSalgadinhoRecheioPorId(id);
    }

    @PostMapping("/recheio")
    @Transactional
    public ResponseEntity<SalgadinhoRecheioResponse> cadastraSalgadinhoRecheio(@RequestBody @Valid SalgadinhoRecheioRegister salgadinhoRecheioForm,
                                                                          UriComponentsBuilder uriBuilder) throws Exception {
        return salgadinhoService.cadastrarSalgadinhoRecheio(salgadinhoRecheioForm, uriBuilder);
    }

    @PutMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<SalgadinhoRecheioResponse> atualizarSalgadinhoRecheio(@PathVariable Long id, @RequestBody @Valid SalgadinhoRecheioRegister salgadinhoRecheioForm) {
        return salgadinhoService.atualizarSalgadinhoRecheio(id, salgadinhoRecheioForm);
    }

    @DeleteMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<?> removerSalgadinhoRecheio(@PathVariable Long id) {
        return salgadinhoService.removerSalgadinhoRecheio(id);
    }

    //================================================================================================================//

    @GetMapping("/tipoPreparo")
    public Page<SalgadinhoTipoPreparoResponse> listarSalgadinhoTipoPreparo(@RequestParam(required = false) String tipoPreparo,
                                                                           @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return salgadinhoService.listarSalgadinhoTipoPreparo(tipoPreparo, paginacao);
    }

    @GetMapping("/tipoPreparo/{id}")
    public ResponseEntity<SalgadinhoTipoPreparoResponse> detalharSalgadinhoTipoPreparo(@PathVariable Long id) {
        return salgadinhoService.detalharSalgadinhoTipoPreparoPorId(id);
    }

    @PostMapping("/tipoPreparo")
    @Transactional
    public ResponseEntity<SalgadinhoTipoPreparoResponse> cadastrarSalgadinhoTipoPreparo(@RequestBody @Valid SalgadinhoTipoPreparoRegister salgadinhoTipoPreparoForm,
                                                                                   UriComponentsBuilder uriBuilder) throws Exception {
        return salgadinhoService.cadastrarSalgadinhoTipoPreparo(salgadinhoTipoPreparoForm, uriBuilder);
    }

    @PutMapping("/tipoPreparo/{id}")
    @Transactional
    public ResponseEntity<SalgadinhoTipoPreparoResponse> atualizarSalgadinhoTipoPreparo(@PathVariable Long id, @RequestBody @Valid SalgadinhoTipoPreparoRegister salgadinhoTipoPreparoForm) {
        return salgadinhoService.atualizarSalgadinhoTipoPreparo(id, salgadinhoTipoPreparoForm);
    }

    @DeleteMapping("/tipoPreparo/{id}")
    @Transactional
    public ResponseEntity<?> removerSalgadinhoTipoPreparo(@PathVariable Long id) {
        return salgadinhoService.removerSalgadinhoTipoPreparo(id);
    }

}