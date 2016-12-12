package team.capcios.passarllista.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ALEJANDRO on 23/11/2016.
 */
public class ModelProva {
    public static HashMap<String, List<String>> getData(){
        HashMap<String, List<String>> expanStringListDetail = new HashMap<>();

        List<String> dia1 = new ArrayList<>();
        dia1.add("DAMO");
        dia1.add("AMEP");

        List<String> dia2 = new ArrayList<>();
        dia2.add("INEP");
        dia2.add("EESO");

        expanStringListDetail.put("23/11", dia1);
        expanStringListDetail.put("24/11", dia2);

        return expanStringListDetail;
    }
}
