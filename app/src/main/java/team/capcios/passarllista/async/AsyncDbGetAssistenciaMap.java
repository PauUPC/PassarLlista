package team.capcios.passarllista.async;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import team.capcios.passarllista.Keys;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Assignatura;
import team.capcios.passarllista.model.Dia;

public class AsyncDbGetAssistenciaMap extends AsyncTask<Pair<Assignatura, Dia>, Void, HashMap<String, Boolean>> {

    private AsyncDbGetAssistenciaMap.AsyncDbGetAssistenciaMapResponse asyncDbGetAssistenciaMapResponse = null;
    private DadesDatabaseHelper dadesDatabaseHelper;

    public interface AsyncDbGetAssistenciaMapResponse {
        void onFinishAsyncDbGetAssistenciaMap(HashMap map);
    }

    public AsyncDbGetAssistenciaMap(Activity listeningActivity, DadesDatabaseHelper dadesDatabaseHelper) {
         asyncDbGetAssistenciaMapResponse =
                (AsyncDbGetAssistenciaMap.AsyncDbGetAssistenciaMapResponse) listeningActivity;
        this.dadesDatabaseHelper = dadesDatabaseHelper;
    }

    @SafeVarargs
    @Override
    protected final HashMap<String, Boolean> doInBackground(Pair<Assignatura, Dia>... params) {
        return dadesDatabaseHelper.getAlumnesAssistenceMap(params[0].first, params[0].second);
    }

    @Override
    protected void onPostExecute(HashMap<String, Boolean> map) {
        asyncDbGetAssistenciaMapResponse.onFinishAsyncDbGetAssistenciaMap(map);
    }
}
