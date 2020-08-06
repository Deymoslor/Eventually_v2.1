package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.nio.channels.InterruptedByTimeoutException;

public class Presentacion extends AppCompatActivity {

    ProgressBar progressBar; //llamamos una nueva variable de tipo progressbar.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        progressBar = findViewById(R.id.progressBar); //Ubicamos la variable.
        progressBar.setVisibility(View.VISIBLE); //Hacemos que la barra de progreso sea visible.

        //Haciendo uso de la clase handler y la interfaz Runnable crearemos una tarea que se ejecute despues de 2 segundos.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Recuperamos las preferencias con la sesión que habíamos guardado, validando así si hay una sesión guardada o no.
                SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion",false);
                if (sesion){ //Mediante esta condicion evaluamos si es verdadera y por lo tanto que le envie a la actividad principal.

                    Intent intent = new Intent(getApplicationContext(),Drawer_principal.class);
                    startActivity(intent);
                    finish();

                }else{

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        },3000);


    }
}