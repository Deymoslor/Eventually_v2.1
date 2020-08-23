package cursos.alain.eventually_v2.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cursos.alain.eventually_v2.Adapters.AdapterGrupos;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link grupos_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class grupos_fragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerGrupos;
    ArrayList<Grupos> listaGrupos;

    ProgressDialog progress;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    public grupos_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment grupos_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static grupos_fragment newInstance(String param1, String param2) {
        grupos_fragment fragment = new grupos_fragment();
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
        View vista = inflater.inflate(R.layout.grupos_fragment,container,false);

        listaGrupos = new ArrayList<>();

        recyclerGrupos = (RecyclerView) vista.findViewById(R.id.recyclerview);
        recyclerGrupos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerGrupos.setHasFixedSize(true);

        requestQueue = Volley.newRequestQueue(getContext());

        cargarWebservice();

        return vista;
    }

    private void cargarWebservice() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = "http:/192.168.1.66/Eventually_01/Consultar_Grupos.php";
        //String url = "http:/192.168.1.65/Eventually_01/Consultar_Grupos.php";
        //String url = "http:/192.168.1.56/Eventually_01/Consultar_Grupos.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onResponse(JSONObject response) {
        Grupos grupos = null;

        JSONArray json = response.optJSONArray("grupo");
        Log.d("ERROR: ", String.valueOf(response));
        try {

            for( int i = 0; i <json.length();i++){
                grupos = new Grupos();
                JSONObject jsonObject = null;

                jsonObject = json.getJSONObject(i);
                grupos.setNombre(jsonObject.optString("Nombre_Grupo"));
                grupos.setEtiqueta(jsonObject.optString("Etiquetas"));
                grupos.setDescripcion(jsonObject.optString("Descripción_Grupo"));
                listaGrupos.add(grupos);
            }

            progress.hide();
            AdapterGrupos adapterGrupos = new AdapterGrupos(listaGrupos);
            recyclerGrupos.setAdapter(adapterGrupos);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se ha podido establecer la conexion con el servidor :c" +
                    "" + response,Toast.LENGTH_SHORT).show();
            progress.hide();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo conectar" + error.toString(),Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }


}