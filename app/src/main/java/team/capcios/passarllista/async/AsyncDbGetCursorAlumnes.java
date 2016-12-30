package team.capcios.passarllista.async;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;

import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Assignatura;


public class AsyncDbGetCursorAlumnes extends AsyncTask<Assignatura, Void, Cursor> {
    private AsyncDbGetCursorAlumnes.AsyncDbGetCursorAssignaturaResponse
            asyncDbGetCursorAssignaturaResponse = null;
    private DadesDatabaseHelper dadesDatabaseHelper;

    public interface AsyncDbGetCursorAssignaturaResponse {
        void onFinishAsyncDbGetCursorAssignatura(Cursor cursor);
    }

    public AsyncDbGetCursorAlumnes(Activity listeningActivity, DadesDatabaseHelper dadesDatabaseHelper) {
        asyncDbGetCursorAssignaturaResponse =
                (AsyncDbGetCursorAlumnes.AsyncDbGetCursorAssignaturaResponse) listeningActivity;
        this.dadesDatabaseHelper = dadesDatabaseHelper;
    }

    @Override
    protected Cursor doInBackground(Assignatura... assignatura) {
        return dadesDatabaseHelper.getAlumnesCursor(assignatura[0]);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        asyncDbGetCursorAssignaturaResponse.onFinishAsyncDbGetCursorAssignatura(cursor);
    }
}
