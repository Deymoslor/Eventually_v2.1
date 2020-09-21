package cursos.alain.eventually_v2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Buscar_Grupos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleBuscarGrupoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleBuscarGrupoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView TextEtiqueta;

    public DetalleBuscarGrupoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleBuscarGrupoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleBuscarGrupoFragment newInstance(String param1, String param2) {
        DetalleBuscarGrupoFragment fragment = new DetalleBuscarGrupoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_buscar_grupo, container, false);
        TextEtiqueta = (TextView) vista.findViewById(R.id.TxtEtiquetadetalle);

        Bundle objetoEtiqueta = getArguments();
        Buscar_Grupos buscar_grupos = null;

        if (objetoEtiqueta != null){
            buscar_grupos = (Buscar_Grupos) objetoEtiqueta.getSerializable("objeto");
            TextEtiqueta.setText(buscar_grupos.getEtiqueta());
        }

        return vista;
    }
}