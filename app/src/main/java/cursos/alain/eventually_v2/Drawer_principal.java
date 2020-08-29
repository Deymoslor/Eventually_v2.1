package cursos.alain.eventually_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import cursos.alain.eventually_v2.Fragments.FragmentAdmin;
import cursos.alain.eventually_v2.Fragments.FragmentBuscarGrupos;
import cursos.alain.eventually_v2.Fragments.FragmentPermisoPersonalizarCuenta;
import cursos.alain.eventually_v2.Fragments.InicioFragment;
import cursos.alain.eventually_v2.Fragments.MisGruposFragment;
import cursos.alain.eventually_v2.interfaces.iComunicaFragments;

public class Drawer_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //variables para cargar el fragment principal
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Variable del fragmentdetalle Grupo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_principal);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        //Establecer el evento onclick al navigationView
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        //Cargar fragment principal
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new InicioFragment());
        fragmentTransaction.commit();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //En este metodo se programan los eventos onclick del drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new InicioFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.grupos){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MisGruposFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.registros) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentAdmin());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.personalizarCuenta) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentPermisoPersonalizarCuenta());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.Buscargrupos) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentBuscarGrupos());
            fragmentTransaction.commit();
        }


        return false;
    }


    @Override
    public void iniciarMisgrupos() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new MisGruposFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void iniciarPerfil() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new FragmentPermisoPersonalizarCuenta());
        fragmentTransaction.commit();
    }

    public void iniciarBuscarGrupos() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new FragmentBuscarGrupos());
        fragmentTransaction.commit();
    }
}