package com.khalid.projectaandroid.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.khalid.projectaandroid.AddProf.AjoutFragment;
import com.khalid.projectaandroid.AddProf.addProf;
import com.khalid.projectaandroid.R;

public class Admin_inter extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inter);

        drawer = findViewById(R.id.nav_admin);
        NavigationView navigationView = findViewById(R.id.naceç_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toog = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);

        drawer.addDrawerListener(toog);
        toog.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelyt, new AjoutFragment()).commit();
navigationView.setCheckedItem(R.id.ajoutp);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.ajoutp:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelyt, new AjoutFragment()).commit();

                break;

            case R.id.updatep:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;

            case R.id.deletep:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.ajoute:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.updatee:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.deletee:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.ajoutc:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.updatec:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;
            case R.id.deletec:
                Toast.makeText(this, "click trvaille bien", Toast.LENGTH_LONG).show();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();
        }
    }


}