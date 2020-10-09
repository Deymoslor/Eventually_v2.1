package cursos.alain.eventually_v2.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import cursos.alain.eventually_v2.EventoActivity;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.VerEventoActivity;
import cursos.alain.eventually_v2.contranueva;
import cursos.alain.eventually_v2.entidades.Grupos1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleMisGrupos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleMisGrupos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textNombregrupo;
    TextView textDescripcion;
    TextView textEtiqueta,textIdGrupo,textIdUsuarioAdmin;

    String IdActualizar,IdGrupo;

    Button btnEventos,btnParticipantes,btnVerEventos;

    RequestQueue requestQueue;

    public DetalleMisGrupos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleMisGrupos.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleMisGrupos newInstance(String param1, String param2) {
        DetalleMisGrupos fragment = new DetalleMisGrupos();
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
        View vista = inflater.inflate(R.layout.fragment_detalle_mis_grupos, container, false);
        recuperarId();
        Log.d(IdActualizar, "onCreateView: ");
        textDescripcion = (TextView) vista.findViewById(R.id.MisGrupos_descripcion);
        textEtiqueta = (TextView) vista.findViewById(R.id.MisGrupos_etiqueta);
        textNombregrupo = (TextView) vista.findViewById(R.id.MisGrupos_nombreGrupo);
        btnEventos = vista.findViewById(R.id.MisGrupos_btnEvento);
        textIdUsuarioAdmin = vista.findViewById(R.id.IdUsuarioAdmin);
        textIdGrupo = vista.findViewById(R.id.textIdGrupoDetalleMisgrupos2);
        btnVerEventos = vista.findViewById(R.id.MisGrupos_btnVerEvento);

        btnParticipantes = vista.findViewById(R.id.MisGrupos_btnParticipantes);

        Bundle objetoDetalle = getArguments();
        Grupos1 grupos1 = null;

        if (objetoDetalle != null){
            grupos1 = (Grupos1) objetoDetalle.getSerializable("objeto");
            textNombregrupo.setText(grupos1.getNombre_Grupo());
            textEtiqueta.setText(grupos1.getEtiqueta());
            textDescripcion.setText(grupos1.getDescripcion());
            textIdGrupo.setText(grupos1.getIdGrupo());

        }
        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Eventos = new Intent(getContext(), EventoActivity.class);
                Eventos.putExtra("IdGrupo",textIdGrupo.getText().toString());
                startActivity(Eventos);
            }
        });
        btnVerEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent VerEventos = new Intent(getContext(), VerEventoActivity.class);
                VerEventos.putExtra("IdGrupo",textIdGrupo.getText().toString());
                startActivity(VerEventos);
            }
        });


        recuperarIdAdmin();


                buscarCliente("http://192.168.1.67/Eventually_01/Admin_Usuario.php?idok="+IdActualizar+"&Id_Grupo="+IdGrupo+"");



        return vista;
    }
    private void recuperarId(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        IdActualizar =preferences.getString("idok", "");
    }
    public void buscarCliente(String URL){
    JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {//Declaramos el típo de llamada que queremos realizar, en este caso GET como lo tenemos en nuestro PHP.

        @Override
        public void onResponse(JSONArray jsonArray) {
            /**/
            JSONObject jsonObject = null; //Declaramos un objeto tipo JSON.

            for (int i = 0; i < jsonArray.length(); i++) { //Nos permitirá recorrer los datos obtenidos en el WS.

                try { //Este try lo utilizaremos para asignar a cada uno de los controles que tenemos en nuestra app por medio de nuestra tabla Cliente.

                    jsonObject = jsonArray.getJSONObject(i); //Vamos almacenando con cada vuelta de ciclo la información y la vamos colocando en su respectivo punto.
                    textIdUsuarioAdmin.setText(jsonObject.getString("Id_Usuario"));

                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Busqueda realizada ^^ " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }
    }, new Response.ErrorListener() { //Generamos el onErrorResponse para el caso de que ocurra algún problema en la conexión.
        @Override
        public void onErrorResponse(VolleyError error) {

            //Toast.makeText(getContext(),"Error de conexión :c " + error,Toast.LENGTH_SHORT).show();
            Log.d(String.valueOf(error), "onErrorResponse: ");
            btnEventos.setVisibility(View.GONE);
        }
    });
    requestQueue = Volley.newRequestQueue(getContext());
    requestQueue.add(jsonArrayRequest);
}

    private void recuperarIdAdmin() {
        IdGrupo = textIdGrupo.getText().toString();
        Log.d(IdGrupo, "recuperarIdAdmin: ");
    }
}