package team.capcios.passarllista.async;

import android.os.AsyncTask;
import java.util.HashMap;

import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AsyncDbSaveAssistenciaMap extends AsyncTask<HashMap<String,Boolean>, Void, Void> {

    private DadesDatabaseHelper dadesDatabaseHelper;

    public AsyncDbSaveAssistenciaMap(DadesDatabaseHelper dadesDatabaseHelper) {
        this.dadesDatabaseHelper = dadesDatabaseHelper;
    }

    @Override
    protected Void doInBackground(HashMap<String, Boolean>... params) {
        dadesDatabaseHelper.saveAlumnesAssistenceMap(params[0]);
        return null;
    }

}
