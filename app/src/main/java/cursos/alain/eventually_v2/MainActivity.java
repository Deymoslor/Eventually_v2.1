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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    EditText Txt_Contraseña_L, Txt_E_Mail_L;
    Button Btn_Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Txt_Contraseña_L = findViewById(R.id.Txt_Contra_L);
        Txt_E_Mail_L = findViewById(R.id.Txt_Email_L);
        Btn_Ingresar = findViewById(R.id.Btn_Ingresar);


        Btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarCliente("http://192.168.1.56/Eventually_01/Validar_Usuario.php");

            }
        });

    }



    private void validarCliente(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){ //Codicional que nos permite evaluar si nustro response está vacío.

                    Intent intent = new Intent(getApplicationContext(),Drawer_principal.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"Usuario y/o contraseña ingresadas son incorrectas :c", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //En este método colocaremos los parametros que nuestro servicio solicita para devolver una respuesta.

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("E_Mail",Txt_E_Mail_L.getText().toString());
                parametros.put("Contraseña",Txt_Contraseña_L.getText().toString());

                return parametros; //Retornamos toda la colección de datos.
            }
        };

        //Creamos una instancia de todo la colección de datos que devolvimos arriba.
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Esta nos ayuda a procesar todas las peticiones hechas desde nuestra app.



    }

    //listener  Registro
    public void Registro(View view){

        Intent siguiente = new Intent(this, registro_Usuarios.class);
        startActivity(siguiente);
    }
    public void Recuperar(View view){

        Intent RecuperarContra = new Intent(this, contranueva.class);
        startActivity(RecuperarContra);
    }



/*
    public void Ingresar(View view){

        Intent Ingresar_principal = new Intent(this, Drawer_principal.class);
        startActivity(Ingresar_principal);

    }

 */

    public void Login(View view) {
        Intent Atras = new Intent(this, MainActivity.class);
        startActivity(Atras);
    }

 }
