package team.capcios.passarllista.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by ALEJANDRO on 23/11/2016.
 */
public class Dia {
    private Date date;
    private List<Assignatura> classes;

    public Dia(Date date) {
        this.date = date;
        classes = new ArrayList<>();
    }

    public void addClasse(Assignatura classe){
        classes.add(classe);
    }

    public Date getDate() {
        return date;
    }

    public List<Assignatura> getClasses() {
        return classes;
    }
}
