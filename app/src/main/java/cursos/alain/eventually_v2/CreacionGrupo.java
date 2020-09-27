package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

public class CreacionGrupo extends AppCompatActivity {
    private static final String[] Generos = new String[]{
            "Hombre","Mujer","Otro genero"
    };

    EditText Txt_Nombre_Grupo, Txt_Edad, Txt_Genero, Txt_Descripcion, Txt_Integrantes, Txt_Etiquetas;
    Button Btn_Crear;
    String IdActualizar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_grupo);

        recuperarId();

        //Ubicación de todos los controles.
        Txt_Nombre_Grupo = (EditText) findViewById(R.id.Txt_Nombre_Grupo);
        Txt_Edad = (EditText) findViewById(R.id.Txt_Edad);
        Txt_Genero = (EditText) findViewById(R.id.Txt_Genero);
        Txt_Descripcion = (EditText) findViewById(R.id.Txt_Descripcion);
        Txt_Integrantes = (EditText) findViewById(R.id.Txt_Integrantes);
        Txt_Etiquetas = (EditText) findViewById(R.id.Txt_Etiquetas);
        Btn_Crear = (Button) findViewById(R.id.Btn_Crear);


        Btn_Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ejecutar_Llenado_Grupo("https://eventually02.000webhostapp.com/registrar_grupo.php");
                Ejecutar_Llenado_Grupo("http://192.168.1.67/Eventually_01/Registrar_Grupo.php");
                //Ejecutar_Llenado_Grupo("http://192.168.1.65/Eventually_01/Registrar_Grupo.php");
                //Ejecutar_Llenado_Grupo("http://192.168.1.56/Eventually_01/Registrar_Grupo.php");

            }
        });

        AutoCompleteTextView AcEt_Genero = findViewById(R.id.Txt_Genero); //Realicé el cambio de id de AcEt_Genero a Txt_Genero
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Generos);
        AcEt_Genero.setAdapter(adapter);
    }

    private void Ejecutar_Llenado_Grupo(String URL){ //Meotodo que enviará las peticiones al servidor.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Grupo creado exitosamente ^^", Toast.LENGTH_SHORT).show();

                Txt_Nombre_Grupo.setText("");
                Txt_Edad.setText("");
                Txt_Genero.setText("");
                Txt_Descripcion.setText("");
                Txt_Integrantes.setText("");
                Txt_Etiquetas.setText("");

                if (!response.isEmpty()) { //Codicional que nos permite evaluar si nuestro response está vacío.

                    Log.d("cadena", response); //Escribe en el run la respuesta.

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Algo ha salido mal :c " + error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){


            //Generamos el metodo Getparams para definir los parametros que enviaremos a el servidor para lo que haremos uso de un objeto Math.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<String, String>();

                //Meidante el método put, definimos los datos que vamos a enviar.

                parametros.put("Nombre_Grupo",Txt_Nombre_Grupo.getText().toString());
                parametros.put("Restriccion_Edad",Txt_Edad.getText().toString());
                parametros.put("Restriccion_Genero",Txt_Genero.getText().toString());
                parametros.put("Descripcion_Grupo",Txt_Descripcion.getText().toString());
                parametros.put("Maximos_Integrantes",Txt_Integrantes.getText().toString());
                parametros.put("Etiquetas",Txt_Etiquetas.getText().toString());
                parametros.put("idok",IdActualizar);


                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.

    }

    private void recuperarId(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        IdActualizar =preferences.getString("idok", "");
    }

}