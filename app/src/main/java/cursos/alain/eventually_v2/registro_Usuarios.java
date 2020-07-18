package cursos.alain.eventually_v2;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
public class registro_Usuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__usuarios);
    }
    public void Login(View view){
        Intent Atras = new Intent(this, MainActivity.class);
        startActivity(Atras);
    }
}
