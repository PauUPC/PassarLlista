package team.capcios.passarllista.activitys;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import team.capcios.passarllista.Keys;
import team.capcios.passarllista.R;
import team.capcios.passarllista.adapters.AlumnesListCursorAdapter;
import team.capcios.passarllista.async.AsyncDbGetCursorAlumnes;
import team.capcios.passarllista.database.CustomCursor;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Alumne;
import team.capcios.passarllista.model.Assignatura;

public class AlumneCheckList extends AppCompatActivity
        implements AsyncDbGetCursorAlumnes.AsyncDbGetCursorAssignaturaResponse {

    private Assignatura assignatura;
    private ListView alumnes;
    private DadesDatabaseHelper dadesDatabaseHelper;
    private Toolbar toolbar;
    private int checked;
    private int unChecked;
    private Map<String, Boolean> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alumnes_list);
        createObjects();
        createToolbar();
        createListeners();
        requestcursor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alumne_check_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.alumne_check_list_forward:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        save();
        super.onBackPressed();
    }

    @Override
    public void onFinishAsyncDbGetCursorAssignatura(Cursor cursor) {
        createAdapter(cursor);
    }

    private void createObjects() {
        dadesDatabaseHelper = new DadesDatabaseHelper(this);
        assignatura =  (Assignatura) getIntent().getSerializableExtra(Keys.ASSIGNATURA_INTENT_KEY);
        checked = getResources().getColor(R.color.alumneChecked);
        unChecked = getResources().getColor(R.color.alumneUnchecked);
        map = new HashMap<>();
    }

    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.alumnes_list_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setTitle("Alumnes");
        setSupportActionBar(toolbar);
    }

    private void createListeners() {
    }

    private void requestcursor(){
        AsyncDbGetCursorAlumnes asyncDbGetCursorAlumnes =
                new AsyncDbGetCursorAlumnes(this, dadesDatabaseHelper);
        asyncDbGetCursorAlumnes.execute(assignatura);
    }

    private void createAdapter(Cursor alumnesCursor) {
        alumnes = (ListView) findViewById(R.id.ListViewAlumnes);
        AlumnesListCursorAdapter alumnesListCursorAdapter =
                new AlumnesListCursorAdapter(this, alumnesCursor);
        alumnes.setAdapter(alumnesListCursorAdapter);
        alumnes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                CustomCursor customCursor = new CustomCursor(cursor);
                Alumne alumne = customCursor.cursorToAlumne();

                Boolean eval = map.get(alumne.getId());
                if(eval != null){
                    map.put(alumne.getId(),!eval);
                } else {
                    map.put(alumne.getId(), true);
                }

                ColorDrawable drawable = (ColorDrawable)view.getBackground();
                if(drawable.getColor() == checked)
                    view.setBackgroundColor(unChecked);
                else
                    view.setBackgroundColor(checked);
            }
        });
    }

    private void save(){
        //TODO save map to database
        finish();
    }
}
