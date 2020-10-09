package cursos.alain.eventually_v2.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleGruposBuscarGruposFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleGruposBuscarGruposFragment extends Fragment {

    TextView textNombregrupo;
    TextView textDescripcion;
    TextView textEtiqueta;
    TextView textIdGrupo;
    RequestQueue requestQueue;
    String IdActualizar;

    Button btnUnirseGrupo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleGruposBuscarGruposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleGruposBuscarGruposFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleGruposBuscarGruposFragment newInstance(String param1, String param2) {
        DetalleGruposBuscarGruposFragment fragment = new DetalleGruposBuscarGruposFragment();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_detalle_grupos_buscar_grupos, container, false);

        textDescripcion = (TextView) vista.findViewById(R.id.MisGrupos_descripcion);
        textEtiqueta = (TextView) vista.findViewById(R.id.MisGrupos_etiqueta);
        textNombregrupo = (TextView) vista.findViewById(R.id.MisGrupos_nombreGrupo);
        textIdGrupo = (TextView) vista.findViewById(R.id.textIdGrupoDetalle);
        btnUnirseGrupo = vista.findViewById(R.id.btnUnirse);

        Bundle objetoDetalle = getArguments();
        Grupos1 grupos1 = null;

        if (objetoDetalle != null){
            grupos1 = (Grupos1) objetoDetalle.getSerializable("objeto");
            textNombregrupo.setText(grupos1.getNombre_Grupo());
            textEtiqueta.setText(grupos1.getEtiqueta());
            textDescripcion.setText(grupos1.getDescripcion());
            textIdGrupo.setText(grupos1.getIdGrupo());
        }
        recuperarId();

        btnUnirseGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutar_Unirse_Grupo("http://192.168.1.67/Eventually_01/Unirse_Grupo.php");
            }
        });

        return vista;
    }
    public void ejecutar_Unirse_Grupo(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "Union exitosa  ^^", Toast.LENGTH_SHORT).show();

                if (!response.isEmpty()) { //Codicional que nos permite evaluar si nuestro response está vacío.

                    Log.d("cadena", response); //Escribe en el run la respuesta.

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Algo ha salido mal :c " + error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){


            //Generamos el metodo Getparams para definir los parametros que enviaremos a el servidor para lo que haremos uso de un objeto Math.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<String, String>();

                //Meidante el método put, definimos los datos que vamos a enviar.

                parametros.put("Id_Grupo",textIdGrupo.getText().toString());
                parametros.put("idok",IdActualizar);


                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.


    }
    private void recuperarId(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        IdActualizar =preferences.getString("idok", "");
    }

}