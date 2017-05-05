package com.example.thaouet.memoire;

/**
 * Created by TAHAR on 05/05/2017.
 */

public class Joueur {

    private Integer id;
    private String nom;
    private Integer score;


    public Joueur(){}

    public Joueur(String nom, Integer score) {
        super();
        this.setNom(nom);
        this.setScore(score);
    }

    //getters & setters

    @Override
    public String toString() {
        return "Joueur [id=" + getId() + ", Nom=" + getNom() + ", Score=" + getScore()
                + "]";
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
