package br.com.compass.avaliacao4.handler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String message;

    private List<String> validationsErrors;
    
    public ErrorMessage(String message){
        this.message = message;
    }
    public ErrorMessage(List<String> validationErrors){
        this.validationsErrors = validationErrors;
    }
}
