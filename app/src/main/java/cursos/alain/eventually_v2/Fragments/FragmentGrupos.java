package cursos.alain.eventually_v2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cursos.alain.eventually_v2.Adapters.AdapterGrupos;
import cursos.alain.eventually_v2.CreacionGrupo;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos;
import cursos.alain.eventually_v2.iComunicaFragments;
import cursos.alain.eventually_v2.registros_admin;

public class FragmentGrupos extends Fragment {

    AdapterGrupos adapterGrupos;
    RecyclerView recyclerViewGrupos;
    FloatingActionButton fab;
    ArrayList<Grupos> listaGrupos;

    //Referencias para comunicar fragments
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grupos_fragment,container,false);
        recyclerViewGrupos = view.findViewById(R.id.recyclerview);
        fab = view.findViewById(R.id.fab_CrearGrupo);
        listaGrupos = new ArrayList<>();

        //cargar la lista
        cargarLista();
        //mostrar data
        mostrarData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(getContext(), CreacionGrupo.class);
                startActivity(siguiente);
            }
        });

        return view;
    }
    public void cargarLista(){
        listaGrupos.add(new Grupos("Grupo free","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Grupo caseta","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Grupo Inead","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Grupo MiraFlorez","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Grupo esquina","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Grupo los parceros","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("aquellos","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("nacional","movil,juegos",R.mipmap.logoxd_foreground));
        listaGrupos.add(new Grupos("Medellín","movil,juegos",R.mipmap.logoxd_foreground));
    }
    public void mostrarData(){
        recyclerViewGrupos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterGrupos = new AdapterGrupos(getContext(), listaGrupos);
        recyclerViewGrupos.setAdapter(adapterGrupos);

        adapterGrupos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = listaGrupos.get(recyclerViewGrupos.getChildAdapterPosition(view)).getNombre();
                //Toast.makeText(getContext(),"Seleccionado: " + nombre, Toast.LENGTH_SHORT).show();
                interfaceComunicaFragments.enviarGrupo(listaGrupos.get(recyclerViewGrupos.getChildAdapterPosition(view)));

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
            interfaceComunicaFragments = (iComunicaFragments) this.actividad;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
