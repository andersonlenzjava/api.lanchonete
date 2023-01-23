package lanchonete.desafio.api.controller.ingredientes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaBorda.PizzaBordaResponse;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaMolho.PizzaMolhoReponse;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioRegister;
import lanchonete.desafio.api.domain.ingrediente.PizzaRecheio.PizzaRecheioResponse;
import lanchonete.desafio.api.service.ingredientes.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ingrediente/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/borda")
    public Page<PizzaBordaResponse> listarPizzaBorda(@RequestParam(required = false) String tipoBorda,
                                                     @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pizzaService.listarPizzaBorda(tipoBorda, paginacao);
    }

    @GetMapping("/borda/{id}")
    public ResponseEntity<PizzaBordaResponse> detalharPizzaBorda(@PathVariable Long id) {
        return pizzaService.detalharPizzaBordaPorId(id);
    }

    @PostMapping("/borda")
    @Transactional
    public ResponseEntity<PizzaBordaResponse> cadastrarPizzaBorda(@RequestBody @Valid PizzaBordaRegister pizzaBordaRegister,
                                                             UriComponentsBuilder uriBuilder) throws Exception {
        return pizzaService.cadastrarPizzaBorda(pizzaBordaRegister, uriBuilder);
    }

    @PutMapping("/borda/{id}")
    @Transactional
    public ResponseEntity<PizzaBordaResponse> atualizarPizzaBorda(@PathVariable Long id, @RequestBody @Valid PizzaBordaRegister pizzaBordaRegister) {
        return pizzaService.atualizarLancheTipoPao(id, pizzaBordaRegister);
    }

    @DeleteMapping("/borda/{id}")
    @Transactional
    public ResponseEntity<?> removerPizzaBorda(@PathVariable Long id) {
        return pizzaService.removerPizzaBorda(id);
    }

//================================================================================================================//

    @GetMapping("/molho")
    public Page<PizzaMolhoReponse> listarPizzaMolho(@RequestParam(required = false) String tipoMolho,
                                                    @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pizzaService.listarPizzaMolho(tipoMolho, paginacao);
    }

    @GetMapping("/molho/{id}")
    public ResponseEntity<PizzaMolhoReponse> detalharPizzaMolho(@PathVariable Long id) {
        return pizzaService.detalharPizzaMolhoPorId(id);
    }

    @PostMapping("/molho")
    @Transactional
    public ResponseEntity<PizzaMolhoReponse> cadastraPizzaMolho(@RequestBody @Valid PizzaMolhoRegister pizzaMolhoRegister,
                                                            UriComponentsBuilder uriBuilder) throws Exception {
        return pizzaService.cadastrarPizzaMolho(pizzaMolhoRegister, uriBuilder);
    }

    @PutMapping("/molho/{id}")
    @Transactional
    public ResponseEntity<PizzaMolhoReponse> atualizarPizzaMolho(@PathVariable Long id, @RequestBody @Valid PizzaMolhoRegister pizzaMolhoRegister) {
        return pizzaService.atualizarLancheTipoPao(id, pizzaMolhoRegister);
    }

    @DeleteMapping("/molho/{id}")
    @Transactional
    public ResponseEntity<?> removerPizzaMolho(@PathVariable Long id) {
        return pizzaService.removerPizzaMolho(id);
    }

//================================================================================================================//

    @GetMapping("/recheio")
    public Page<PizzaRecheioResponse> listarPizzaRecheio(@RequestParam(required = false) String tipoRecheio,
                                                         @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pizzaService.listarPizzaRecheio(tipoRecheio, paginacao);
    }

    @GetMapping("/recheio/{id}")
    public ResponseEntity<PizzaRecheioResponse> detalharPizzaRecheio(@PathVariable Long id) {
        return pizzaService.detalharPizzaRecheioPorId(id);
    }

    @PostMapping("/recheio")
    @Transactional
    public ResponseEntity<PizzaRecheioResponse> cadastrarPizzaRecheio(@RequestBody @Valid PizzaRecheioRegister pizzaRecheioRegister,
                                                                 UriComponentsBuilder uriBuilder) throws Exception {
        return pizzaService.cadastrarPizzaRecheio(pizzaRecheioRegister, uriBuilder);
    }

    @PutMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<PizzaRecheioResponse> atualizarPizzaRecheio(@PathVariable Long id, @RequestBody @Valid PizzaRecheioRegister pizzaRecheioRegister) {
        return pizzaService.atualizarPizzaRecheio(id, pizzaRecheioRegister);
    }

    @DeleteMapping("/recheio/{id}")
    @Transactional
    public ResponseEntity<?> removerPizzaRecheio(@PathVariable Long id) {
        return pizzaService.removerPizzaRecheio(id);
    }

}