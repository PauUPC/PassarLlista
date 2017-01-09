package team.capcios.passarllista.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import team.capcios.passarllista.model.Alumne;
import team.capcios.passarllista.model.Assignatura;


public class CustomCursor extends CursorWrapper {

    private Cursor cursor;

    public CustomCursor(Cursor cursor) {
        super(cursor);
        this.cursor = cursor;
    }

    public Assignatura cursorToAssignatura() {
        Assignatura assignatura = new Assignatura();
        try {
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_NOM));
            String sigles = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_SIGLES));
            String aula = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_AULA));
            assignatura = new Assignatura(nom, sigles, aula);
            assignatura.setId(cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ASSIGNATURA_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignatura;
    }

    public Alumne cursorToAlumne() {
        Alumne alumne = new Alumne();
        try {
            //TODO: Error comentat. Correcte referir-se al _id.
//            String id = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_ID));
            String id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_NOM));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DadesDatabaseHelper.KEY_ALUMNE_MAIL));
            alumne = new Alumne(id, nom, email);
        } catch (Exception e){
            e.printStackTrace();
        }
        return alumne;
    }
}
