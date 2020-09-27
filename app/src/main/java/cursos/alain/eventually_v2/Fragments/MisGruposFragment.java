package cursos.alain.eventually_v2.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cursos.alain.eventually_v2.Adapters.GruposAdapter;
import cursos.alain.eventually_v2.CreacionGrupo;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisGruposFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisGruposFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton fab;

    String IdActualizar;

    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.


    RecyclerView recyclerGrupos;
    ArrayList<Grupos1> listaGrupos;

    ProgressDialog progress;

    RequestQueue request;

    JsonObjectRequest jsonObjectRequest;


    public MisGruposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisGruposFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisGruposFragment newInstance(String param1, String param2) {
        MisGruposFragment fragment = new MisGruposFragment();
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
        View vista = inflater.inflate(R.layout.fragment_mis_grupos,container,false);
        fab = vista.findViewById(R.id.fab_CrearGrupo);

        listaGrupos = new ArrayList<>();

        recyclerGrupos = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerGrupos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerGrupos.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        cargarWebservice();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(getContext(), CreacionGrupo.class);
                startActivity(siguiente);
            }
        });

        recuperarId();

        return vista;
    }



    private void cargarWebservice() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

       String url = "http://192.168.1.67/Eventually_01/consultar_grupos.php";
        //String url = "http://192.168.1.66/Eventually_01/Consultar_Grupos.php";
        //String url = "http://192.168.1.56/Eventually_01/Consultar_Grupos.php";

        jsonObjectRequest = new  JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String responsi) {
                Log.d(responsi,"mensaje");
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Algo ha salido mal!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String,String> parametros=new HashMap<String, String>();

            //Meidante el método put, definimos los datos que vamos a enviar.
            parametros.put("idok",IdActualizar);

            Log.d(String.valueOf(parametros), "recuperarId: ");

            return parametros;
        }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.
    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se puedo conectar " + error.toString(), Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.d("ERROR: ",error.toString());
        progress.hide();

    }

    @Override
    public void onResponse(JSONObject response) {
        Grupos1 grupos1 = null;


        try {
            JSONArray json = response.optJSONArray("grupo");

            for (int i = 0;i < json.length();i++){
                grupos1 = new Grupos1();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                grupos1.setNombre_Grupo(jsonObject.optString("Nombre_Grupo"));
                grupos1.setEtiqueta(jsonObject.optString("Etiquetas"));
                grupos1.setDescripcion(jsonObject.optString("Descripcion_Grupo"));
                listaGrupos.add(grupos1);
            }
            progress.hide();
            GruposAdapter adapter = new GruposAdapter(listaGrupos);

            recyclerGrupos.setAdapter(adapter);

            Log.d(String.valueOf(response), "onResponse: ");
        }
        catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"No se pudo conectar con el servidor" + e.toString(),Toast.LENGTH_SHORT).show();
                progress.hide();
        }
    }
    private void recuperarId(){

        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        IdActualizar =preferences.getString("idok", "");
        Log.d(IdActualizar, "recuperarId2: ");
    }

}
