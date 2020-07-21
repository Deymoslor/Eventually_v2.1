package cursos.alain.eventually_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import cursos.alain.eventually_v2.Fragments.DetalleGrupoFragment;
import cursos.alain.eventually_v2.Fragments.FragmentAdmin;
import cursos.alain.eventually_v2.Fragments.FragmentGrupos;
import cursos.alain.eventually_v2.Fragments.FragmentIntereses;
import cursos.alain.eventually_v2.Fragments.FragmentPersonalizarCuenta;
import cursos.alain.eventually_v2.Fragments.MainFragment;
import cursos.alain.eventually_v2.entidades.Grupos;

public class Drawer_principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    //variables para cargar el fragment principal
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Variable del fragmentdetalle Grupo
    DetalleGrupoFragment detalleGrupoFragment;

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
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //En este metodo se programan los eventos onclick del drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.grupos){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentGrupos());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.registros) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentAdmin());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.intereses) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentIntereses());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.personalizarCuenta) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentPersonalizarCuenta());
            fragmentTransaction.commit();
        }


        return false;
    }

    @Override
    public void enviarGrupo(Grupos grupos) {//comunicar ambos fragments.
        //aqui se realizara la logica para realizar el envio.
        detalleGrupoFragment = new DetalleGrupoFragment();
        //objeto de tipo bundle para transportar la informacion.
        Bundle bundleEnvio = new Bundle();
        //enviar el objeto que esta llegando con serializable.
        bundleEnvio.putSerializable("objeto", grupos);
        detalleGrupoFragment.setArguments(bundleEnvio);
        //abrir fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, detalleGrupoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}