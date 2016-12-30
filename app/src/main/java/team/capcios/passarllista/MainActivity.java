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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import team.capcios.passarllista.activitys.AlumneCheckList;
import team.capcios.passarllista.adapters.AssignaturaListCursorAdapter;
import team.capcios.passarllista.async.AsyncDbGetCursorGeneric;
import team.capcios.passarllista.database.CustomCursor;
import team.capcios.passarllista.database.DadesDatabaseHelper;
import team.capcios.passarllista.model.Assignatura;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AsyncDbGetCursorGeneric.AsyncDbGetCursorGenericResponse {

    private ListView listView;
    private DadesDatabaseHelper dadesDatabaseHelper;
    private SharedPreferences sharedPreferences;
    private ActivityLauncher activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createObjects();
        createToolbar();
        createListeners();
        requestCursor();
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
            if(dadesDatabaseHelper == null)
                dadesDatabaseHelper= new DadesDatabaseHelper(this);
            dadesDatabaseHelper.poblar();
            sharedPreferences.edit().putBoolean("firstrun", false).apply();
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == Keys.CODE_ALUMNE_CHECK_LIST) {
            //TODO
        }
    }

    @Override
    public void onFinishAsyncDbGetCursorGeneric(Cursor cursor) {
        createAdapter(cursor);
    }

    private void createObjects() {
        sharedPreferences = getSharedPreferences("team.capcios.passarllista", MODE_PRIVATE);
        dadesDatabaseHelper = new DadesDatabaseHelper(this);
        activityLauncher = new ActivityLauncher(this);
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
    }

    private void requestCursor(){
        AsyncDbGetCursorGeneric asyncDbGetCursorGeneric =
                new AsyncDbGetCursorGeneric(this, dadesDatabaseHelper);
        asyncDbGetCursorGeneric.execute(Keys.ASYNC_KEY_ASSIGNATURA);
    }

    private void createAdapter(Cursor listCursor) {
        listView = (ListView) findViewById(R.id.ListView);
        AssignaturaListCursorAdapter assignaturaListCursorAdapter =
                new AssignaturaListCursorAdapter(this, listCursor);
        listView.setAdapter(assignaturaListCursorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                CustomCursor customCursor = new CustomCursor(cursor);
                activityLauncher.LaunchAlumneCheckList(customCursor.cursorToAssignatura());
            }
        });
    }

    private class ActivityLauncher {

        private Context context;

        ActivityLauncher(Context context) {
            this.context = context;
        }

        void LaunchAlumneCheckList(Assignatura assignatura){
            Intent intent = new Intent(context, AlumneCheckList.class);
            intent.putExtra(Keys.ASSIGNATURA_INTENT_KEY, assignatura);
            startActivityForResult(intent, Keys.CODE_ALUMNE_CHECK_LIST);
        }
    }
}