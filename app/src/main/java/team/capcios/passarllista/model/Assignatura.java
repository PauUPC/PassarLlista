package team.capcios.passarllista.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Assignatura implements Serializable {
        private String id;
        private String nom;
        private String sigles;
        private String aula;
        private List<Alumne> alumnes;
        // private Array de Dies de la setmana que s'imparteix la assignatura.


        public Assignatura() {
            this.nom = "";
            this.sigles = "";
            this.aula = "";
            this.alumnes = new ArrayList<>();
        }

        public Assignatura(String nom, String sigles, String aula) {
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

        public String getAula() {
            return aula;
        }

        public void setAula(String aula) {
            this.aula = aula;
        }

        public String getSigles() {
            return sigles;
        }

        public void setSigles(String sigles) {
            this.sigles = sigles;
        }

        public String getId() {
        return id;
    }

        public void setId(String id) {
        this.id = id;
    }
}
