package team.capcios.passarllista.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dia implements Serializable {
    private Date date;
    private List<Assignatura> aClasses;

    public Dia(Date date) {
        this.date = date;
        aClasses = new ArrayList<>();
    }

    public void addClasse(Assignatura assignatura){
        aClasses.add(assignatura);
    }

    public Date getDate() {
        return date;
    }

    public List<Assignatura> getaClasses() {
        return aClasses;
    }
}
