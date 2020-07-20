package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void Ingresar(View view){

        Intent Ingresar_principal = new Intent(this, Drawer_principal.class);
        startActivity(Ingresar_principal);

    }

    public void Login(View view) {
        Intent Atras = new Intent(this, MainActivity.class);
        startActivity(Atras);
    }
}
