package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import med.voll.api.domain.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Alexandre
 */
@RestControllerAdvice
public class ErrorHandling {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400a(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationError::new).toList());
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity error400b(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity AccessDeniedError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }
    
    private record DataValidationError(String field, String message){
        public DataValidationError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity error(ValidateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
}
