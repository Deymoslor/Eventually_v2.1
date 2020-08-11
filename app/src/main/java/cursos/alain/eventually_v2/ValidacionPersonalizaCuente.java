package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ValidacionPersonalizaCuente extends AppCompatActivity {
    Button Btn_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_personaliza_cuente);
        Btn_enter=findViewById(R.id.Btn_enter);
    }
    public void Ingresa(View view){
        Intent Siguiente=new Intent(this,PersonalizarDatosUsuario.class);
        startActivity(Siguiente);
    }
}