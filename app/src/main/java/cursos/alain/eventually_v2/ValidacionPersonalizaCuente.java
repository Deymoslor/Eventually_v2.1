package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ValidacionPersonalizaCuente extends AppCompatActivity {

    EditText Txt_Documento_E;
    Button Btn_enter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_personaliza_cuente);

        Btn_enter=findViewById(R.id.Btn_enter);
        Txt_Documento_E = (EditText) findViewById(R.id.Txt_Documento_E);

        Btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ejecutarServicioDocumento("http://192.168.1.56/Eventually_01/Registrar_Documento.php");
                ejecutarServicioDocumento("http://192.168.1.56/Eventually_01/Registrar_Documento.php");
            }
        });

    }

    public void Ingresa(View view){
        Intent Siguiente=new Intent(this,PersonalizarDatosUsuario.class);
        startActivity(Siguiente);
    }

    private void ejecutarServicioDocumento(String URL){ //Meotodo que enviará las peticiones al servidor.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registrado exitosamente ^^", Toast.LENGTH_SHORT).show();

                Txt_Documento_E.setText("");
                Intent intent = new Intent(getApplicationContext(),PersonalizarDatosUsuario.class);
                startActivity(intent);



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
                parametros.put("Documento",Txt_Documento_E.getText().toString().trim());
                /*
                parametros.put("Id_Usuario","");
                parametros.put("Nombre1","".toString());
                parametros.put("Nombre2","".toString());
                parametros.put("Apellido1","".toString());
                parametros.put("Apellido2", "".toString());
                parametros.put("Edad","".toString());
                parametros.put("Celular","".toString());
                parametros.put("Etiquetas1","".toString());
                parametros.put("Etiquetas2","".toString());
                parametros.put("Etiquetas3","".toString());*/


                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.

    }

}