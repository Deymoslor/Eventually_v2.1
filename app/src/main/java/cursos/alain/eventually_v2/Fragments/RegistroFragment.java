package cursos.alain.eventually_v2.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import cursos.alain.eventually_v2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText TxT_Usuario, Txt_Email, Txt_Contra, Txt_Contra_c;
    Button Btn_Registrar;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest; //Nos permitirán establecer la conexión con nuestro WB.


    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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

        //Empezamos a mapear los componentes en la parte superior.
        View vista=inflater.inflate(R.layout.fragment_registro, container, false);

        TxT_Usuario = (EditText) vista.findViewById(R.id.Txt_Usuario);
        Txt_Email = (EditText) vista.findViewById(R.id.Txt_Email);
        Txt_Contra = (EditText) vista.findViewById(R.id.Txt_Contra);
        Txt_Contra_c = (EditText) vista.findViewById(R.id.Txt_Contra_c);
        Btn_Registrar = (Button) vista.findViewById(R.id.Btn_Registrar);

        request = Volley.newRequestQueue(getContext());

        Btn_Registrar.setOnClickListener(new View.OnClickListener() { //Creamos un onClick para nuestro botón que nos enviará a una clase.
            @Override
            public void onClick(View view) {

                cargarWebService(); //Enviamos a la clase que tenemos abajo.

            }
        });



        return vista;
                //inflater.inflate(R.layout.fragment_registro, container, false);
    }

    private void cargarWebService() { //Cuando se precione el botón hará todo lo que esté aquí adentro.

        progreso = new ProgressDialog(getContext()); //Creamos el dialogo.
        progreso.setMessage("Cargando. . ."); //Definimos el mensaje que tendrá.
        progreso.show(); //Mostramos el dialogo.

        //Enviamos todos los datos para que el WB los reciba.
        String url = "http://192.168.56.1/Eventually_01/Registrar_Usuario.php?Id_Cliente&Nombre_Cliente="+TxT_Usuario.getText().toString()+
                "E_Mail="+Txt_Email.getText().toString()+
                "&Contraseña="+Txt_Contra.getText().toString()+
                "&Confirmar_Contraseña="+Txt_Contra_c.getText().toString();

       // url = url.replace(" ","%20"); //Reemplazará el espacio por un %20 para que funcione nuestro WB.

        //Estas 2 lineas nos permiten establecer la comunicación con los 2 métodos de onErrorResponse y onResponse.
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this); /*Con esta realiza el llamado e intenta conectarse para saber si hay error e
                                                                                            ir a onErrorResponse o correcto e ir a onResponse.*/
        request.add(jsonObjectRequest);

    }


    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT).show(); //Creamos un aviso para cuando se registre exitosamente.
        progreso.hide(); //Ocultamos el dialogo de el mensaje de progreso.
        //Limpiamos todos los textos.
        TxT_Usuario.setText("");
        Txt_Email.setText("");
        Txt_Contra.setText("");
        Txt_Contra_c.setText("");

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar "+error.toString(),Toast.LENGTH_SHORT).show(); //Creamos un aviso para cuando se registre exitosamente.
        Log.i("Error",error.toString());

    }


}