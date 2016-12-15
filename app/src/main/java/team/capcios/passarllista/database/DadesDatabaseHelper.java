package team.capcios.passarllista.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import team.capcios.passarllista.model.Alumne;
import team.capcios.passarllista.model.Assignatura;

public class DadesDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    //Database info
    private static final String DATABASE_NAME = "DadesDatabase";
    private static final int DATABASE_VERSION = 6;

    //Table Names
    private static final String TAULA_ALUMNE = "Alumne";
    private static final String TAULA_ASSIGNATURA = "Assignatura";
    private static final String TAULA_DIA = "Dia";
    private static final String TAULA_MATRICULATS = "Matriculats";
    private static final String TAULA_IMPARTEIX = "Imparteix";
    private static final String TAULA_LLISTA_ASSISTENCIA = "LlistaAssistencia";
    private static final String TAULA_APUNTAT = "Apuntat";

    //Taula Alumne Columnes
    private static final String KEY_ALUMNE_ID = "idAlumne";
    private static final String KEY_ALUMNE_NOM = "nom";
    private static final String KEY_ALUMNE_MAIL = "mail";

    //Taula Assignatura Columnes
    private static final String KEY_ASSIGNATURA_ID = "idAssignatura";
    public static final String KEY_ASSIGNATURA_SIGLES = "sigles";
    private static final String KEY_ASSIGNATURA_NOM = "nom";
    private static final String KEY_ASSIGNATURA_AULA = "aula";
    private static final String KEY_ASSIGNATURA_TEXT = "text";

    //Taula Dia Columnes
    private static final String KEY_DIA_ID = "data";

    //Taula Imparteix Columnes
    private static final String KEY_IMPARTEIX_IDASSIGNATURA = "idAssignatura";
    private static final String KEY_IMPARTEIX_IDDIA = "idDia";

    //Taula Matriculats Columnes
    private static final String KEY_MATRICULATS_IDALUMNE = "idAlumne";
    private static final String KEY_MATRICULATS_IDASSIGNATURA = "idAssignatura";

    //Taula LlistaAssistencia Columnes
    private static final String KEY_LLISTA_ASSISTENCIA_IDASSIGNATURA = "idAssignatura";
    private static final String KEY_LLISTA_ASSISTENCIA_IDDIA = "idDia";

    //Taula Apuntat Columnes
    private static final String KEY_APUNTAT_IDALUMNE = "idAlumne";
    private static final String KEY_APUNTAT_IDASSIGNATURA = "idAssignatura";
    private static final String KEY_APUNTAT_IDDIA = "idDia";



    public DadesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create_all_tables(db);

    }

    public void poblar_assignatures() {
        for (int i=0; i<10; i++){
            Assignatura assignatura = new Assignatura("Assignatura"+String.valueOf(i),"CL-"+String.valueOf(i),"A0"+String.valueOf(i));
            addAssignatura(assignatura);
        }
    }

    private void create_all_tables(SQLiteDatabase db) {
        String CREATE_ALUMNE_TABLE = "CREATE TABLE " + TAULA_ALUMNE +
                "(" +
                KEY_ALUMNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_ALUMNE_NOM + " TEXT NOT NULL," +
                KEY_ALUMNE_MAIL + " TEXT NOT NULL" +
                ")";

        String CREATE_APUNTAT_TABLE = "CREATE TABLE " + TAULA_APUNTAT +
                "(" +
                KEY_APUNTAT_IDALUMNE + " INTEGER NOT NULL," +
                KEY_APUNTAT_IDASSIGNATURA + " INTEGER NOT NULL," +
                KEY_APUNTAT_IDDIA + " DATE NOT NULL," +
                " CONSTRAINT Apuntat_idAlumne_idDia_idAssignatura_pk PRIMARY KEY (" + KEY_APUNTAT_IDALUMNE + ", " + KEY_APUNTAT_IDDIA + ", " + KEY_APUNTAT_IDASSIGNATURA + ")," +
                " CONSTRAINT Apuntat_Alumne_idAlumne_fk FOREIGN KEY (" + KEY_APUNTAT_IDALUMNE + ") REFERENCES " + TAULA_ALUMNE + "(" + KEY_ALUMNE_ID +")," +
                " CONSTRAINT Apuntat_LlistaAssistencia_idDia_idAssignatura_fk FOREIGN KEY (" + KEY_APUNTAT_IDDIA + ", " + KEY_APUNTAT_IDASSIGNATURA + ") REFERENCES " + TAULA_LLISTA_ASSISTENCIA + " (" + KEY_LLISTA_ASSISTENCIA_IDDIA + ", " + KEY_LLISTA_ASSISTENCIA_IDASSIGNATURA + ")" +
                ")";
        String CREATE_ASSIGNATURA_TABLE = "CREATE TABLE " + TAULA_ASSIGNATURA +
                "(" +
                KEY_ASSIGNATURA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_ASSIGNATURA_SIGLES + " TEXT NOT NULL," +
                KEY_ASSIGNATURA_NOM + " TEXT NOT NULL," +
                KEY_ASSIGNATURA_AULA + " TEXT NOT NULL," +
                KEY_ASSIGNATURA_TEXT + " TEXT" +
                ")";
        String CREATE_DIA_TABLE = "CREATE TABLE " + TAULA_DIA +
                "(" +
                KEY_DIA_ID + " DATE PRIMARY KEY NOT NULL" +
                ")";
        String CREATE_IMPARTEIX_TABLE = "CREATE TABLE " + TAULA_IMPARTEIX +
                "(" +
                KEY_IMPARTEIX_IDASSIGNATURA + " INTEGER NOT NULL," +
                KEY_IMPARTEIX_IDDIA + " DATE NOT NULL," +
                " CONSTRAINT Imparteix_idAssignatura_idDia_pk PRIMARY KEY (" + KEY_IMPARTEIX_IDASSIGNATURA + ", " + KEY_IMPARTEIX_IDDIA + ")," +
                " CONSTRAINT Imparteix_Assignatura_idAssignatura_fk FOREIGN KEY (" + KEY_IMPARTEIX_IDASSIGNATURA + ") REFERENCES " + TAULA_ASSIGNATURA + " (" + KEY_ASSIGNATURA_ID + ")," +
                " CONSTRAINT Imparteix_Dia_data_fk FOREIGN KEY (" + KEY_IMPARTEIX_IDDIA + ") REFERENCES " + TAULA_DIA + " (" + KEY_DIA_ID + ")" +
                ")";
        String CREATE_LLISTA_ASSISTENCIA_TABLE = "CREATE TABLE " + TAULA_LLISTA_ASSISTENCIA +
                "(" +
                KEY_LLISTA_ASSISTENCIA_IDASSIGNATURA + " INTEGER NOT NULL," +
                KEY_LLISTA_ASSISTENCIA_IDDIA + " DATE NOT NULL," +
                " CONSTRAINT LlistaAssistencia_idAssignatura_idDia_pk PRIMARY KEY (" + KEY_LLISTA_ASSISTENCIA_IDASSIGNATURA + ", " + KEY_LLISTA_ASSISTENCIA_IDDIA + ")," +
                " CONSTRAINT LlistaAssistencia_Dia_data_fk FOREIGN KEY (" + KEY_LLISTA_ASSISTENCIA_IDDIA + ") REFERENCES " + TAULA_DIA + " (" + KEY_DIA_ID + ")," +
                " CONSTRAINT LlistaAssistencia_Assignatura_idAssignatura_fk FOREIGN KEY ("  + KEY_LLISTA_ASSISTENCIA_IDASSIGNATURA + ") REFERENCES " + TAULA_ASSIGNATURA + " (" + KEY_ASSIGNATURA_ID + ")" +
                ")";
        String CREATE_MATRICULATS_TABLE = "CREATE TABLE " + TAULA_MATRICULATS +
                "(" +
                KEY_MATRICULATS_IDALUMNE + " INTEGER NOT NULL," +
                KEY_MATRICULATS_IDASSIGNATURA + " INTEGER NOT NULL," +
                " CONSTRAINT Matriculats_idAlumne_idAssignatura_pk PRIMARY KEY (" + KEY_MATRICULATS_IDALUMNE + ", " + KEY_MATRICULATS_IDASSIGNATURA + ")," +
                " CONSTRAINT Matriculats_Alumne_idAlumne_fk FOREIGN KEY (" + KEY_MATRICULATS_IDALUMNE + ") REFERENCES " + TAULA_ALUMNE + " (" + KEY_ALUMNE_ID + ")," +
                " CONSTRAINT Matriculats_Assignatura_idAssignatura_fk FOREIGN KEY (" + KEY_MATRICULATS_IDASSIGNATURA + ") REFERENCES " + TAULA_ASSIGNATURA + " (" + KEY_ASSIGNATURA_ID + ")" +
                ")";
        String CREATE_UNIQUE_SIGLES_INDEX = "CREATE UNIQUE INDEX Assignatura_sigles_uindex ON " + TAULA_ASSIGNATURA + " (" + KEY_ASSIGNATURA_SIGLES + ")";

        db.execSQL(CREATE_ALUMNE_TABLE);
        db.execSQL(CREATE_APUNTAT_TABLE);
        db.execSQL(CREATE_ASSIGNATURA_TABLE);
        db.execSQL(CREATE_DIA_TABLE);
        db.execSQL(CREATE_IMPARTEIX_TABLE);
        db.execSQL(CREATE_LLISTA_ASSISTENCIA_TABLE);
        db.execSQL(CREATE_MATRICULATS_TABLE);
        db.execSQL(CREATE_UNIQUE_SIGLES_INDEX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_ALUMNE);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_ASSIGNATURA);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_DIA);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_IMPARTEIX);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_MATRICULATS);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_APUNTAT);
        db.execSQL("DROP TABLE IF EXISTS " + TAULA_LLISTA_ASSISTENCIA);

        onCreate(db);


    }

    //Obtenir tots els Alumnes
    public List<Alumne> getAllAlumnes() {
        List<Alumne> alumnes = new ArrayList<>();

        //SELECT * FROM Alumne
        String ALUMNE_SELECT_QUERY = String.format("SELECT * FROM %s", TAULA_ALUMNE);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(ALUMNE_SELECT_QUERY, null);
        try {
            if(cursor.moveToFirst()){
                do {
                    Alumne alumne = new Alumne();
                    alumne.setId(getValue(cursor,KEY_ALUMNE_ID));
                    alumne.setNom(getValue(cursor,KEY_ALUMNE_NOM));
                    alumne.setEmail(getValue(cursor,KEY_ALUMNE_MAIL));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d(TAG, "Error intentant obtenir els alumnes de la base de dades");

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return alumnes;
    }

    //Inserir un Alumne
    public void addAlumne(Alumne alumne) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ALUMNE_NOM, alumne.getNom());
            values.put(KEY_ALUMNE_MAIL, alumne.getEmail());

            db.insertOrThrow(TAULA_ALUMNE, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.d(TAG, "Error intentant afegir un alumne a la base de dades");
        } finally {
            db.endTransaction();
        }
    }

    public void addAssignatura(Assignatura assignatura) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ASSIGNATURA_NOM, assignatura.getNom());
            values.put(KEY_ASSIGNATURA_AULA, assignatura.getAula());
            values.put(KEY_ASSIGNATURA_SIGLES, assignatura.getSigles());

            db.insertOrThrow(TAULA_ASSIGNATURA, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e){
            Log.d(TAG, "Error intentant afegir una assignatura a la base de dades");
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public Cursor getAssignaturesCursor() {
        SQLiteDatabase db = getReadableDatabase();
        String ASSIGNATURA_SELECT_QUERY = String.format("SELECT %s as _id, * FROM %s", KEY_ASSIGNATURA_ID,
                TAULA_ASSIGNATURA);
        return db.rawQuery(ASSIGNATURA_SELECT_QUERY, null);
    }

    private String getValue (Cursor c, String key) {
        return c.getString(c.getColumnIndex(key));
    }
}