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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registros_admin extends AppCompatActivity {

    EditText Txt_Id_Usuario, Txt_UserName, Txt_Email, Txt_Contra, Txt_Contra_c;
    Button Btn_Buscar,Btn_Editar,Btn_Eliminar,Btn_Cerrar_Sesion;



    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_admin);


        Txt_Id_Usuario = (EditText) findViewById(R.id.Txt_Id_Usuario);
        Txt_UserName = (EditText) findViewById(R.id.Txt_UserName);
        Txt_Email = (EditText) findViewById(R.id.Txt_Email);
        Txt_Contra = (EditText) findViewById(R.id.Txt_Contra);
        Txt_Contra_c = (EditText) findViewById(R.id.Txt_Contra_c);
        Btn_Buscar = (Button) findViewById(R.id.Btn_Buscar);
        Btn_Editar = (Button) findViewById(R.id.Btn_Editar);
        Btn_Eliminar = (Button) findViewById(R.id.Btn_Eliminar);
        Btn_Cerrar_Sesion = (Button) findViewById(R.id.Btn_Cerrar_Sesion);

        Btn_Buscar.setOnClickListener(new View.OnClickListener() { //llamamos el botón para buscar usuarios.
            @Override
            public void onClick(View v) {


                //buscarCliente("https://eventually02.000webhostapp.com/buscar_usuarios.php?Id_Usuario="+Txt_Id_Usuario.getText()+"");

                buscarCliente("http://192.168.1.67/Eventually_01/Buscar_Usuarios.php?Id_Usuario="+Txt_Id_Usuario.getText()+"");
                //buscarCliente("http://192.168.1.65/Eventually_01/Buscar_Usuarios.php?Id_Usuario="+Txt_Id_Usuario.getText()+"");
                //buscarCliente("http://192.168.1.56/Eventually_01/Buscar_Usuarios.php?Documento="+Txt_Id_Usuario.getText()+"");

            }
        });

        Btn_Editar.setOnClickListener(new View.OnClickListener() { //Llamamos el botón para editar usuarios.
            @Override
            public void onClick(View v) {

                //ejecutarServicio("https://eventually02.000webhostapp.com/editar_usuario.php");

                ejecutarServicio("http://192.168.1.67/Eventually_01/Editar_Usuario.php");
                //ejecutarServicio("http://192.168.1.65/Eventually_01/Editar_Usuario.php");
                //ejecutarServicio("http://192.168.1.56/Eventually_01/Editar_Usuario.php");

            }
        });

        Btn_Eliminar.setOnClickListener(new View.OnClickListener() { //Llamamos el botón para eliminar usuarios.
            @Override
            public void onClick(View v) {

                //eliminarCliente("https://eventually02.000webhostapp.com/eliminar_usuario.php");

                eliminarCliente("http://192.168.1.67/Eventually_01/Eliminar_Usuario.php");
                //eliminarCliente("http://192.168.1.65/Eventually_01/Eliminar_Usuario.php");
                //eliminarCliente("http://192.168.1.56/Eventually_01/Eliminar_Usuario.php");

            }
        });

        Btn_Cerrar_Sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperamos las preferencia.
                SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit(); //Limpiamos las preferencias mediante este método.

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    //Creamos un nuevo método que nos permitirá devolver los datos buscados desde nuestra app.
    private void buscarCliente (String URL){

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {//Declaramos el típo de llamada que queremos realizar, en este caso GET como lo tenemos en nuestro PHP.
            @Override
            public void onResponse(JSONArray jsonArray) {
                /**/
                    JSONObject jsonObject = null; //Declaramos un objeto tipo JSON.

                for (int i = 0; i < jsonArray.length(); i++) { //Nos permitirá recorrer los datos obtenidos en el WS.

                    try { //Este try lo utilizaremos para asignar a cada uno de los controles que tenemos en nuestra app por medio de nuestra tabla Cliente.

                        jsonObject = jsonArray.getJSONObject(i); //Vamos almacenando con cada vuelta de ciclo la información y la vamos colocando en su respectivo punto.
                        Txt_UserName.setText(jsonObject.getString("UserName"));
                        Txt_Email.setText(jsonObject.getString("E_Mail"));
                        Txt_Contra.setText(jsonObject.getString("Contraseña"));
                        Txt_Contra_c.setText(jsonObject.getString("Confirmar_Contraseña"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Busqueda realizada ^^ " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() { //Generamos el onErrorResponse para el caso de que ocurra algún problema en la conexión.
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error de conexión :c " + error,Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    //Creamos un nuevo método que nos permitirá hacer la edición de datos (Es exactamente el mismo código que el de insertar).
    private void ejecutarServicio(String URL){ //Meotodo que enviará las peticiones al servidor.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Edición realizada ^^", Toast.LENGTH_SHORT).show();
                Txt_Id_Usuario.setText("");
                Txt_UserName.setText("");
                Txt_Email.setText("");
                Txt_Contra.setText("");
                Txt_Contra_c.setText("");
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
                parametros.put("Id_Usuario",Txt_Id_Usuario.getText().toString());
                parametros.put("UserName",Txt_UserName.getText().toString());
                parametros.put("E_Mail",Txt_Email.getText().toString());
                parametros.put("Contraseña",Txt_Contra.getText().toString());
                parametros.put("Confirmar_Contraseña",Txt_Contra_c.getText().toString());

                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.

    }

    //Creamos un nuevo método que nos permitirá hacer la eliminación de datos (Es exactamente el mismo código que el de insertar y el editar).
    private void eliminarCliente(String URL){ //Meotodo que enviará las peticiones al servidor.

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Se ha eliminado exitosamente ^^", Toast.LENGTH_SHORT).show();
                Txt_Id_Usuario.setText("");
                Txt_UserName.setText("");
                Txt_Email.setText("");
                Txt_Contra.setText("");
                Txt_Contra_c.setText("");
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
                parametros.put("Id_Usuario",Txt_Id_Usuario.getText().toString());

                return parametros;
            }
        };
        //Aquí procesaremos las peticiones hechas por nuestra app para que la libreria se encargue de ejecutarlas.
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //Aquí enviamos la solicitud agregando el objeto string request.

    }

    private void limpiarFormulario(){

        Txt_Id_Usuario.setText("");
        Txt_UserName.setText("");
        Txt_Email.setText("");
        Txt_Contra.setText("");
        Txt_Contra_c.setText("");

    }

}