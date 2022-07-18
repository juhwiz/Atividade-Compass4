package br.com.compass.avaliacao4.dto.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.avaliacao4.models.AssociatesModel;
import br.com.compass.avaliacao4.models.Ideology;
import lombok.Data;

@Data
public class RequestPoliticalPartyDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    @NotBlank
    private Ideology ideologia;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy", timezone="GMT-3")
    private Date dataFundacao;
    private AssociatesModel associados;

    
}
