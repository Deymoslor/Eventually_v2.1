package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class registros_admin extends AppCompatActivity {

    EditText Txt_Documento, Txt_Usuario, Txt_Email, Txt_Contra, Txt_Contra_c;
    Button Btn_Buscar,Btn_Editar,Btn_Eliminar;



    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_admin);


        Txt_Documento = (EditText) findViewById(R.id.Txt_Documento);
        Txt_Usuario = (EditText) findViewById(R.id.Txt_Usuario);
        Txt_Email = (EditText) findViewById(R.id.Txt_Email);
        Txt_Contra = (EditText) findViewById(R.id.Txt_Contra);
        Txt_Contra_c = (EditText) findViewById(R.id.Txt_Contra_c);
        Btn_Buscar = (Button) findViewById(R.id.Btn_Buscar);
        Btn_Editar = (Button) findViewById(R.id.Btn_Editar);
        Btn_Eliminar = (Button) findViewById(R.id.Btn_Eliminar);

        Btn_Buscar.setOnClickListener(new View.OnClickListener() { //llamamos el botón para buscar usuarios.
            @Override
            public void onClick(View v) {

                buscarCliente("http://192.168.1.69/Eventually_01/Buscar_Usuarios.php?Documento="+Txt_Documento.getText()+"");

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
                        Txt_Usuario.setText(jsonObject.getString("Nombre_Cliente"));
                        Txt_Email.setText(jsonObject.getString("E_Mail"));
                        Txt_Contra.setText(jsonObject.getString("Contraseña"));
                        Txt_Contra_c.setText(jsonObject.getString("Confirmar_Contraseña"));

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
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

}