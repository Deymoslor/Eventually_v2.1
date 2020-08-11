package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

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

public class PersonalizarDatosUsuario extends AppCompatActivity {
        EditText TxtDocumento,TxtNombre1,TxtNombre2,TxtApellido1,TxtApellido2,
    TxtEdad,TxtCelular,TxtEtiqueta1,TxtEtiqueta2,TxtEtiqueta3;
    RequestQueue requestQueue;

        Button BtnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_datos_usuario);

        TxtApellido1 = findViewById(R.id.Txt_Apellido1);
        TxtApellido2 = findViewById(R.id.Txt_Apellido2);
        TxtDocumento = findViewById(R.id.Txt_Documento);
        TxtNombre1 = findViewById(R.id.Txt_Nombre1);
        TxtNombre2 = findViewById(R.id.Txt_Nombre2);
        TxtEdad = findViewById(R.id.Txt_Edad);
        TxtCelular = findViewById(R.id.Txt_Celular);
        TxtEtiqueta1 = findViewById(R.id.Txt_Etiqueta1);
        TxtEtiqueta2 = findViewById(R.id.Txt_Etiqueta2);
        TxtEtiqueta3 = findViewById(R.id.Txt_Etiqueta3);
        BtnGuardar = findViewById(R.id.Btn_Editar);

        BtnGuardar.setOnClickListener(new View.OnClickListener() { //Llamamos el botón para registrar usuarios.
            @Override
            public void onClick(View v) {

                //ejecutarServicio("http://192.168.1.56/Eventually_01/Registrar_Usuario.php");
                ejecutarServicio("http://192.168.1.69/Eventually_01/Registrar_Documento.php");

            }
        });
    }
    private void ejecutarServicio(String URL){ //Meotodo que enviará las peticiones al servidor.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Registrado exitosamente ^^", Toast.LENGTH_SHORT).show();
                TxtDocumento.setText("");
                TxtNombre1.setText("");
                TxtNombre2.setText("");
                TxtApellido1.setText("");
                TxtApellido2.setText("");
                TxtEdad.setText("");
                TxtCelular.setText("");
                TxtEtiqueta1.setText("");
                TxtEtiqueta2.setText("");
                TxtEtiqueta3.setText("");
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
                parametros.put("Documento",TxtDocumento.getText().toString());
                parametros.put("Nombre1",TxtNombre1.getText().toString());
                parametros.put("Nombre2",TxtNombre2.getText().toString());
                parametros.put("Apellido1",TxtApellido1.getText().toString());
                parametros.put("Apeliido2",TxtApellido2.getText().toString());
                parametros.put("Edad",TxtEdad.getText().toString());
                parametros.put("Celular",TxtCelular.getText().toString());
                parametros.put("Etiqueta1",TxtEtiqueta1.getText().toString());
                parametros.put("Etiqueta2",TxtEtiqueta2.getText().toString());
                parametros.put("Etiqueta3",TxtEtiqueta3.getText().toString());

                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.

    }
}