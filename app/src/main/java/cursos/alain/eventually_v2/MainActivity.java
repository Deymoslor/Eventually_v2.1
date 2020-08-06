package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String E_Mail, Contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Txt_Contraseña_L = findViewById(R.id.Txt_Contra_L);
        Txt_E_Mail_L = findViewById(R.id.Txt_Email_L);
        Btn_Ingresar = findViewById(R.id.Btn_Ingresar);

        recuperarPreferencias(); //Llamamos aquí el método recuperar preferencias permitiendo que de este modo cada que iniciemos nos aparecerá el usuario y password correctos.

        Btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                E_Mail = Txt_E_Mail_L.getText().toString();
                Contraseña = Txt_Contraseña_L.getText().toString();

                //Evaluamos si algúno de los campos de el login están vacíos.
                if (!E_Mail.isEmpty() || !Contraseña.isEmpty()){

                    validarCliente("http://192.168.1.69/Eventually_01/Validar_Usuario.php");

                }else{

                    Toast.makeText(MainActivity.this, "Email o contraseña vacios.", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }



    private void validarCliente(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()){ //Codicional que nos permite evaluar si nuestro response está vacío.

                    guardapreferencias(); //Este metodo lo agregamos aquí ya que se guardaran los datos unicamente cuando el Email y Contraseña existan usando este método.

                    Intent intent = new Intent(getApplicationContext(),Drawer_principal.class);
                    startActivity(intent);
                    finish(); //Para dejar solo la que se está por abrir y cerrar la actual.

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
                parametros.put("E_Mail",E_Mail);
                parametros.put("Contraseña",Contraseña);

                return parametros; //Retornamos toda la colección de datos.
            }
        };

        //Creamos una instancia detodo la colección de datos que devolvimos arriba.
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

    //Creamos un nuevo método que nos ayudará a guardar el usuario y contraseña para que el usuario final no se tenga que estar loggeando.
    private void guardapreferencias(){

        //Creamos una nuevo objeto con el constructor getSharedPreferences al cual le pasaremos por parámetro el nombre de las preferencias y el modo de acceso de estas.
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        //Definimos que lo que queremos hacer es guardar o actualizar datos en la preferencia.
        SharedPreferences.Editor editor = preferences.edit();

        //A continuación mediante el put string agregamos el usuario y contraseña y el valor que se guardará en cada uno.
        editor.putString("E_Mail",E_Mail);
        editor.putString("Contraseña",Contraseña);

        editor.putBoolean("sesion",true); //Esta la utilizaremos más adelante para guardar la sesión en caso de ser correcto.

        editor.commit(); //Mediante este método guardamos todos los cambios.

    }

    //Cremos otro método que nos permita recuperar los datos.
    private void recuperarPreferencias(){

        //Cremos estos métodos con exactamente los mismos nombres que en los de arriba.
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        //Asignamos los valores a cada espacio que corresponde.
        Txt_E_Mail_L.setText(preferences.getString("E_Mail",""));
        Txt_Contraseña_L.setText(preferences.getString("Contraseña",""));

    }

 }
