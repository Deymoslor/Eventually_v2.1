package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PersonalizarDatosUsuario extends AppCompatActivity {
    EditText TxtDocumento,TxtNombre,TxtApellido,TxtCelular;
    Button Btn_Editar;
    String IdActualizar;

    private static final String TAG = "PersonalizarDatosUsuario";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    RequestQueue requestQueue; //Definimos este request aquí ya que varios metodos harán uso del mismo objeto.

        Button BtnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar_datos_usuario);

        mDisplayDate = (TextView) findViewById(R.id.Txt_FechaNacimiento);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PersonalizarDatosUsuario.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDataSet: date" + dayOfMonth + "/" + month + "/" + year);

                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        recuperarId();

        TxtDocumento = findViewById(R.id.Txt_Documento);
        TxtNombre = findViewById(R.id.Txt_Nombre);
        TxtApellido = findViewById(R.id.Txt_Apellido);
        TxtCelular = findViewById(R.id.TxtCelular);
        Btn_Editar = findViewById(R.id.Btn_Editar);

        Btn_Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ejecutarAdicionUsuario("http://192.168.1.66/Eventually_01/Adicion_Usuario.php");
                //ejecutarAdicionUsuario("http://192.168.1.65/Eventually_01/Adicion_Usuario.php");
                //ejecutarAdicionUsuario("http://192.168.1.56/Eventually_01/Adicion_Usuario.php");
            }
        });


    }

    private void ejecutarAdicionUsuario(String URL){ //Meotodo que enviará las peticiones al servidor.


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() { //Declararemos una petición declarando el tipo de llamada (POST como en el WS).
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Edición realizada ^^", Toast.LENGTH_SHORT).show();
                TxtCelular.setText("");
                mDisplayDate.setText("");
                TxtDocumento.setText("");
                TxtApellido.setText("");
                TxtNombre.setText("");




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
                parametros.put("FechaNacimiento",mDisplayDate.getText().toString());
                parametros.put("Celular",TxtCelular.getText().toString());
                parametros.put("Documento",TxtDocumento.getText().toString());
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