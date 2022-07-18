package br.com.compass.avaliacao4.models;

public enum Ideology {
    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private final String descricao;
 
    Ideology( String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
