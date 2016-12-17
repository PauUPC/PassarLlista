package team.capcios.passarllista.async;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;

import team.capcios.passarllista.Keys;
import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AsyncDbGetCursorGeneric extends AsyncTask<Integer, Void, Cursor> {
    private AsyncDbGetCursorGeneric.AsyncDbGetCursorGenericResponse asyncDbGetCursorGenericResponse = null;
    private DadesDatabaseHelper dadesDatabaseHelper;

    public interface AsyncDbGetCursorGenericResponse {
        void onFinishAsyncDbGetCursorGeneric(Cursor cursor);
    }

    public AsyncDbGetCursorGeneric(Activity listeningActivity, DadesDatabaseHelper dadesDatabaseHelper) {
        asyncDbGetCursorGenericResponse =
                (AsyncDbGetCursorGeneric.AsyncDbGetCursorGenericResponse) listeningActivity;
        this.dadesDatabaseHelper = dadesDatabaseHelper;
    }

    @Override
    protected Cursor doInBackground(Integer... integers) {
        switch (integers[0]) {
            case Keys.ASYNC_KEY_ASSIGNATURA:
                return dadesDatabaseHelper.getAssignaturesCursor();
            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        asyncDbGetCursorGenericResponse.onFinishAsyncDbGetCursorGeneric(cursor);
    }
}
