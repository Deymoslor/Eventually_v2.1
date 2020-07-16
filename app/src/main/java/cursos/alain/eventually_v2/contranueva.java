package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class contranueva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contranueva);
    }
    public void Enviar(View view){
        Toast.makeText(this, "Se le ha enviado un correo de verificacion.", Toast.LENGTH_LONG).show();

    }
}
