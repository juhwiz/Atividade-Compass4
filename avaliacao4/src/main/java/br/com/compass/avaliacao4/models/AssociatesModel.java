package br.com.compass.avaliacao4.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;


@Entity
@Data
public class AssociatesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private PoliticalOffice cargoPolitico;
    private Date dataNascimento;
    @Enumerated(EnumType.STRING)
    private Sex sexo;
    @ManyToOne
    @JoinColumn(name = "partido")
    private PoliticalPartyModel partido;

    
}
