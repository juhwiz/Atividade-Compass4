package br.com.compass.avaliacao4.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.compass.avaliacao4.exceptions.AssociateNotFoundException;
import br.com.compass.avaliacao4.exceptions.PoliticalPartyNotFoundException;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler{

    private static final String PARTY_NOT_FOUND = "Partido não encontrado";
    private static final String ASSOCIATE_NOT_FOUND = "Associado não encontrado";

    protected ResponseEntity<ErrorMessage> handlerPartyNotFound(PoliticalPartyNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(PARTY_NOT_FOUND));
    }
    protected ResponseEntity<ErrorMessage> handlerAssociateNotFound(AssociateNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ASSOCIATE_NOT_FOUND));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> 
            "Campo '" + fieldError.getField() + "' "+ fieldError.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(validationList));
    }

    
}
