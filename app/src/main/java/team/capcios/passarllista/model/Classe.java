package team.capcios.passarllista.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEJANDRO on 23/11/2016.
 */
public class Classe {
    private String nom;
    private String sigles;
    private String aula;
    private List<Alumne> alumnes;
    // private Array de Dies de la setmana que s'imparteix la assignatura.


    public Classe() {
        this.nom = "";
        this.sigles = "";
        this.aula = "";
        this.alumnes = new ArrayList<>();
    }

    public Classe(String nom, String sigles, String aula) {
        this.nom = nom;
        this.sigles = sigles;
        this.aula = aula;
        this.alumnes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Alumne> getAlumnes() {
        return alumnes;
    }

    public void setAlumnes(List<Alumne> alumnes) {
        this.alumnes = alumnes;
    }
}
