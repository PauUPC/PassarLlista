package team.capcios.passarllista.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import team.capcios.passarllista.model.Dia;

public class ConjuntDiesClasses implements Serializable {
    private List<Dia> diesClasses;

    public ConjuntDiesClasses() {
        diesClasses = new ArrayList<>();
    }

    public void addDia(Dia dia){
        diesClasses.add(dia);
    }

    public List<Dia> getDiesClasses() {
        return diesClasses;
    }
}
