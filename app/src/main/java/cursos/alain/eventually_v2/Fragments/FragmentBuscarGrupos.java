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
import android.widget.LinearLayout;
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
import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Buscar_Grupos;
import cursos.alain.eventually_v2.interfaces.iComunicaFragments;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBuscarGrupos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBuscarGrupos extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerGrupos;
    ArrayList<Buscar_Grupos> listaGrupos;

    Activity activity;
    iComunicaFragments interfaceComunicaFragments;

    ProgressDialog progress;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public FragmentBuscarGrupos() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBuscarGrupos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBuscarGrupos newInstance(String param1, String param2) {
        FragmentBuscarGrupos fragment = new FragmentBuscarGrupos();
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
        View vista = inflater.inflate(R.layout.fragment_buscar_grupos, container, false);

        listaGrupos = new ArrayList<>();

        recyclerGrupos = (RecyclerView) vista.findViewById(R.id.idRecycler);
        recyclerGrupos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerGrupos.setHasFixedSize(true);

        request = Volley.newRequestQueue(getContext());

        cargarWebservice();





        return vista;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_buscar_grupos, container, false);
    }

    private void cargarWebservice() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando. . .");
        progress.show();

        String url = "https://eventually02.000webhostapp.com/buscar_grupo.php ";
        //String url = "http://192.168.1.67/Eventually_01/Buscar_Grupo.php";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        Buscar_Grupos buscar_grupos = null;

        try {
            JSONArray json = response.optJSONArray("grupo");

            for (int i = 0;i < json.length(); i++){

                buscar_grupos = new Buscar_Grupos();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                buscar_grupos.setEtiqueta(jsonObject.optString("Etiquetas"));
                listaGrupos.add(buscar_grupos);
            }
            BuscarGruposAdapter adapter = new BuscarGruposAdapter(listaGrupos);
            recyclerGrupos.setAdapter(adapter);
            adapter.setOnclickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interfaceComunicaFragments.enviarEtiqueta
                            (listaGrupos.get(recyclerGrupos.getChildAdapterPosition(v)));
                }
            });
            progress.hide();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se ha podido establecer conexión con el servidor" + " " + response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"No se puede conectar" + error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR",error.toString());
        progress.hide();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicaFragments= (iComunicaFragments) this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
