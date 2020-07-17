package cursos.alain.eventually_v2.Fragments;

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

import java.util.ArrayList;

import cursos.alain.eventually_v2.Adapters.AdapterGrupos;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos;

public class FragmentGrupos extends Fragment {

    AdapterGrupos adapterGrupos;
    RecyclerView recyclerViewGrupos;
    ArrayList<Grupos> listaGrupos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grupos_fragment,container,false);
        recyclerViewGrupos = view.findViewById(R.id.recyclerview);
        listaGrupos = new ArrayList<>();

        //cargar la lista
        cargarLista();
        //mostrar data
        mostrarData();
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
                Toast.makeText(getContext(),"Seleccionado: " + nombre, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
