package br.com.compass.avaliacao4.dto.request;


import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compass.avaliacao4.models.PoliticalOffice;
import br.com.compass.avaliacao4.models.PoliticalPartyModel;
import br.com.compass.avaliacao4.models.Sex;
import lombok.Data;

@Data
public class RequestAssociatesDto {
    @NotBlank
    private String nome;
    @NotBlank
    private PoliticalOffice cargoPolitico;
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dataNascimento;
    @NotBlank
    private Sex sexo;
    private PoliticalPartyModel partido;

    
}
