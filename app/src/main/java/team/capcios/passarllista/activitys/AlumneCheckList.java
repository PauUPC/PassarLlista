package team.capcios.passarllista.activitys;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.Array;

import team.capcios.passarllista.Keys;
import team.capcios.passarllista.MainActivity;
import team.capcios.passarllista.R;
import team.capcios.passarllista.adapters.AlumnesListCursorAdapter;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Alumne;
import team.capcios.passarllista.model.Assignatura;

public class AlumneCheckList extends AppCompatActivity {

    private Assignatura assignatura;
    private ListView alumnes;
    private Cursor alumnesCursor;
    private DadesDatabaseHelper dadesDatabaseHelper;
    private AlumneCheckList.OnItemTouchListener onItemTouchListener;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alumnes_list);
        createObjects();
        createToolbar();
        createListeners();
        createAdapter();
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
                super.onBackPressed();
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

    public interface OnItemTouchListener {
        void onClick(Alumne alumne);
        void onLongClick(Alumne alumne);
    }

    private void createObjects() {
        dadesDatabaseHelper = new DadesDatabaseHelper(this);
        assignatura =  (Assignatura) getIntent().getSerializableExtra(Keys.ASSIGNATURA_INTENT_KEY);

    }

    private void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.alumnes_list_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
        toolbar.setTitle("Alumnes");
        setSupportActionBar(toolbar);
    }

    private void createListeners() {
        onItemTouchListener = new AlumneCheckList.OnItemTouchListener() {
            @Override
            public void onClick(Alumne alumne) {

            }

            @Override
            public void onLongClick(Alumne alumne) {

            }
        };
    }

    private void createAdapter() {
        alumnesCursor = dadesDatabaseHelper.getAlumnesCursor(assignatura);
        alumnes = (ListView) findViewById(R.id.ListViewAlumnes);
        AlumnesListCursorAdapter alumnesListCursorAdapter =
                new AlumnesListCursorAdapter(this, alumnesCursor, onItemTouchListener);
        alumnes.setAdapter(alumnesListCursorAdapter);
    }

    private void save(){}
}
