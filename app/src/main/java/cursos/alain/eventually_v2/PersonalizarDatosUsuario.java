package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    EditText TxtDocumento,TxtNombre,TxtApellido,TxtEdad,TxtCelular,TxtEtiqueta1,TxtEtiqueta2,TxtEtiqueta3;
    Button Btn_Editar;

    String IdActualizar;

    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.

        Button BtnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_datos_usuario);

        recuperarId();

        TxtEtiqueta3 = findViewById(R.id.Txt_Etiqueta3);
        TxtEtiqueta2 = findViewById(R.id.Txt_Etiqueta2);
        TxtEtiqueta1 = findViewById(R.id.Txt_Etiqueta1);
        TxtDocumento = findViewById(R.id.Txt_Documento);
        TxtNombre = findViewById(R.id.Txt_Nombre);
        TxtApellido = findViewById(R.id.Txt_Apellido);
        TxtEdad = findViewById(R.id.Txt_Edad);
        TxtCelular = findViewById(R.id.TxtCelular);
        Btn_Editar = findViewById(R.id.Btn_Editar);

        Btn_Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ejecutarAdicionUsuario("http://192.168.1.69/Eventually_01/Adicion_Usuario.php");
                ejecutarAdicionUsuario("http://192.168.1.65/Eventually_01/Adicion_Usuario.php");
                //ejecutarAdicionUsuario("http://192.168.1.56/Eventually_01/Adicion_Usuario.php");
            }
        });
    }

    private void ejecutarAdicionUsuario(String URL){ //Meotodo que enviará las peticiones al servidor.


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Edición realizada ^^", Toast.LENGTH_SHORT).show();
                TxtEtiqueta3.setText("");
                TxtEtiqueta2.setText("");
                TxtEtiqueta1.setText("");
                TxtCelular.setText("");
                TxtEdad.setText("");
                TxtDocumento.setText("");
                TxtApellido.setText("");
                TxtNombre.setText("");

                Log.d("cadena", response);


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

                parametros.put("Nombre",TxtNombre.getText().toString());
                parametros.put("Apellido",TxtApellido.getText().toString());
                parametros.put("Edad",TxtEdad.getText().toString());
                parametros.put("Celular",TxtCelular.getText().toString());
                parametros.put("Documento",TxtDocumento.getText().toString());
                parametros.put("Etiqueta1",TxtEtiqueta1.getText().toString());
                parametros.put("Etiqueta2",TxtEtiqueta2.getText().toString());
                parametros.put("Etiqueta3",TxtEtiqueta3.getText().toString());
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