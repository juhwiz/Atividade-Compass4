package br.com.compass.avaliacao4.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.EnumType;


@Entity
@Data
public class PoliticalPartyModel{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    @Enumerated(EnumType.STRING)
    private Ideology ideologia;
    private Date dataFundacao;
    @ManyToOne
    @JoinColumn(name = "associados")
    private AssociatesModel associados;

}