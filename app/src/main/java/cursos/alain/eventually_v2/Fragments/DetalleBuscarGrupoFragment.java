package cursos.alain.eventually_v2.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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

import cursos.alain.eventually_v2.Adapters.BuscarGruposAdapter;
import cursos.alain.eventually_v2.Adapters.BuscarGruposDetalleAdapter;
import cursos.alain.eventually_v2.Adapters.GruposAdapter;
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Buscar_Grupos;
import cursos.alain.eventually_v2.entidades.Grupos1;
import cursos.alain.eventually_v2.interfaces.iComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleBuscarGrupoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleBuscarGrupoFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView TextEtiqueta;

    Button btnUnirse;

    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.


    String etiqueta1;

    RecyclerView recyclerGrupos;
    ArrayList<Grupos1> listaGrupos2;

    Activity activity;
    iComunicaFragments interfaceComunicaFragments;

    ProgressDialog progress;

    RequestQueue request;

    JsonObjectRequest jsonObjectRequest;

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
        listaGrupos2 = new ArrayList<>();


        recyclerGrupos = (RecyclerView) vista.findViewById(R.id.RecyclerdetalleGrupos);
        recyclerGrupos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerGrupos.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        TextEtiqueta = (TextView) vista.findViewById(R.id.TxtEtiquetadetalle);




        Bundle objetoEtiqueta = getArguments();
        Buscar_Grupos buscar_grupos = null;

        if (objetoEtiqueta != null){
            buscar_grupos = (Buscar_Grupos) objetoEtiqueta.getSerializable("objeto");
            TextEtiqueta.setText(buscar_grupos.getEtiqueta());
            etiqueta1 = buscar_grupos.toString();
        }
        recuperarEtiqueta();
        cargarWebservice();

        return vista;
    }

    private void recuperarEtiqueta() {
        etiqueta1 = TextEtiqueta.getText().toString();
        Log.d(etiqueta1, "SI: ");
    }

    private void cargarWebservice() {
        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();



        String url = "http://192.168.1.67/Eventually_01/Consultar_Grupos_detalle.php?etiquetas=" + etiqueta1;
        //String url = "http://192.168.1.66/Eventually_01/Consultar_Grupos_detalle.php";
        //String url = "http://192.168.1.56/Eventually_01/Consultar_Grupos_detalle.php";

        jsonObjectRequest = new  JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
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
                grupos1.setIdGrupo(jsonObject.optString("Id_Grupo"));
                listaGrupos2.add(grupos1);
            }
            progress.hide();
            BuscarGruposDetalleAdapter adapter = new BuscarGruposDetalleAdapter(listaGrupos2);
            recyclerGrupos.setAdapter(adapter);
            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    interfaceComunicaFragments.enviarDetalleGruposBuscarGrupos(listaGrupos2.get(recyclerGrupos.getChildAdapterPosition(v)));
                }
            });



            Log.d(String.valueOf(response), "onResponse: ");
        }
        catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se pudo conectar con el servidor" + e.toString(),Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicaFragments = (iComunicaFragments) this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}