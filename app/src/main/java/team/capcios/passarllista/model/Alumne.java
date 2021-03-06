package team.capcios.passarllista.model;

import android.provider.ContactsContract;

import java.io.Serializable;

public class Alumne implements Serializable {
    private String id;
    private String nom;
    private String email;

    public Alumne() {
        this.id = "";
        this.nom = "";
        this.email = "";
    }

    public Alumne(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public Alumne(String nom, String email) {
        this.id = "";
        this.nom = nom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nom + "\n" + email;
    }
}
