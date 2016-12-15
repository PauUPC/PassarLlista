package team.capcios.passarllista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import team.capcios.passarllista.activitys.AlumneCheckList;
import team.capcios.passarllista.adapters.AssignaturaListCursorAdapter;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Assignatura;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private Cursor listCursor;
    private DadesDatabaseHelper dadesDatabaseHelper;
    private SharedPreferences sharedPreferences;
    private ActivityLauncher activityLauncher;
    private MainActivity.OnItemTouchListener onItemTouchListener;
    private final static int CODE_ALUMNE_CHECK_LIST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createObjects();
        createToolbar();
        createListeners();
        createAdapter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.getBoolean("firstrun", true)) {
            //TODO inicialitzar database per primer cop
            sharedPreferences.edit().putBoolean("firstrun", false).apply();
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == CODE_ALUMNE_CHECK_LIST) {
            //TODO
        }
    }

    public interface OnItemTouchListener {
        void onClick(Assignatura assignatura);
        void onLongClick(Assignatura assignatura);
    }

    private void createObjects() {
        sharedPreferences = getSharedPreferences("team.capcios.passarllista", MODE_PRIVATE);
        dadesDatabaseHelper = new DadesDatabaseHelper(this);
        activityLauncher = new ActivityLauncher(this, dadesDatabaseHelper);
    }

    private void createToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void createListeners() {
        onItemTouchListener = new OnItemTouchListener() {
            @Override
            public void onClick(Assignatura assignatura) {

            }

            @Override
            public void onLongClick(Assignatura assignatura) {

            }
        };
    }

    private void createAdapter() {
        dadesDatabaseHelper.poblar_assignatures();
        listCursor = dadesDatabaseHelper.getAssignaturesCursor();
        listView = (ListView) findViewById(R.id.ListView);
        AssignaturaListCursorAdapter assignaturaListCursorAdapter =
                new AssignaturaListCursorAdapter(this, listCursor, onItemTouchListener);
        listView.setAdapter(assignaturaListCursorAdapter);
    }



    private class ActivityLauncher {

        private Context context;
        private DadesDatabaseHelper dadesDatabaseHelper;

        ActivityLauncher(Context context, DadesDatabaseHelper dadesDatabaseHelper) {
            this.context = context;
            this.dadesDatabaseHelper = dadesDatabaseHelper;
        }

        public boolean LaunchAlumneCheckList(Assignatura assignatura){
            Intent intent = new Intent(context, AlumneCheckList.class);
            intent.putExtra("CLASSE", assignatura);
            startActivityForResult(intent, CODE_ALUMNE_CHECK_LIST);
            return true;
        }
    }
}