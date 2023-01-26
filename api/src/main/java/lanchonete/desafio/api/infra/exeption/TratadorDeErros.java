package lanchonete.desafio.api.infra.exeption;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// funciona com um conjunto de filters que interceptam as requisições e realizam alguma ação
@RestControllerAdvice
public class TratadorDeErros {

    // acessar algum recurso que não existe 404 - not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    } //404

//    ===============================================================================================================
    // dois a baixo, converte o field error para dados erro validação pegando só as partes significativas

    // erro de validação 400 // por que o spring detalha todos os erros de uma forma complexa
    @ExceptionHandler(MethodArgumentNotValidException.class) // objeto de erro recebido e passado como paramentro
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        // pega os erros e converte de uma lista para outra
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // pega a minha exception e joga no corpo da requisição este erro
    @ExceptionHandler(ItemJaExisteException.class)
    public ResponseEntity tratarError400(ItemJaExisteException ex){
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(PedidoVazioException.class)
    public ResponseEntity tratarError400(PedidoVazioException ex){
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    // pega a minha exception e joga no corpo da requisição este erro
    @ExceptionHandler(ValorPagoInsuficienteException.class)
    public ResponseEntity tratarError400(ValorPagoInsuficienteException ex){
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }


    // record = dto --> simplificar o json enviado
    private record DadosErroValidacao(String campo, String mensagem) { // especificado só o que precisa
        public DadosErroValidacao(FieldError erro) { // construtor para o map
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

//    ===============================================================================================================
}
