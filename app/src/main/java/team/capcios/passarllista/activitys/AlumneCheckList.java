package team.capcios.passarllista.activitys;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import team.capcios.passarllista.R;
import team.capcios.passarllista.database.DadesDatabaseHelper;

public class AlumneCheckList extends AppCompatActivity {

    private ListView alumnes;
    private Cursor alumnesCursor;
    private DadesDatabaseHelper dadesDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alumnes_list);
        createObjects();
        createToolbar();
        createListeners();
        createAdapter();
    }

    private void createObjects() {

    }

    private void createToolbar() {

    }

    private void createListeners() {

    }

    private void createAdapter() {
        //TODO get correct method // alumnesCursor = dadesDatabaseHelper. //
//        alumnes = (ListView) findViewById(R.id.ListViewAlumnes);
//        AlumnesListCursorAdapter alumnesListCursorAdapter = new AlumnesListCursorAdapter(this, alumnesCursor);
//        alumnes.setAdapter(alumnesListCursorAdapter);
    }
}
