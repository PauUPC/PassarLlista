package team.capcios.passarllista.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import team.capcios.passarllista.model.Assignatura;


public class CustomCursor extends CursorWrapper {

    private Cursor cursor;

    public CustomCursor(Cursor cursor) {
        super(cursor);
        this.cursor = cursor;
    }

    public Assignatura CursorToAssignatura() {
        Assignatura assignatura = new Assignatura();
        try {
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_NOM));
            String sigles = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_SIGLES));
            String aula = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_AULA));
            assignatura = new Assignatura(nom, sigles, aula);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignatura;
    }
}
