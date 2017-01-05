package team.capcios.passarllista.async;

import android.os.AsyncTask;
import java.util.HashMap;

import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Assignatura;
import team.capcios.passarllista.model.Dia;

public class AsyncDbSaveAssistenciaMap extends AsyncTask<HashMap<String,Boolean>, Void, Void> {

    private DadesDatabaseHelper dadesDatabaseHelper;
    private Assignatura assignatura;
    private Dia dia;

    public AsyncDbSaveAssistenciaMap(DadesDatabaseHelper dadesDatabaseHelper, Assignatura assignatura, Dia dia) {
        this.dadesDatabaseHelper = dadesDatabaseHelper;
        this.assignatura = assignatura;
        this.dia = dia;
    }

    @Override
    protected Void doInBackground(HashMap<String, Boolean>... params) {
        dadesDatabaseHelper.saveAlumnesAssistenceMap(params[0], assignatura, dia);
        return null;
    }

}
