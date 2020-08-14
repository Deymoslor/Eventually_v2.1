package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class CreacionGrupo extends AppCompatActivity {
    private static final String[] Generos = new String[]{
            "Hombre","Mujer","Otro genero"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_grupo);

        AutoCompleteTextView AcEt_Genero = findViewById(R.id.AcEt_genero);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Generos);
        AcEt_Genero.setAdapter(adapter);
    }
}