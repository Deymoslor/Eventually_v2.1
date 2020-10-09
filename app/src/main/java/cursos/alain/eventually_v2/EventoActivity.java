package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class EventoActivity extends AppCompatActivity {
    TextView textIdgrupo;

    String Idgrupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        textIdgrupo = findViewById(R.id.textIdGrupoDetalleMisgrupos);


        String Idgrupo2 = getIntent().getStringExtra("IdGrupo");
        textIdgrupo.setText(Idgrupo2);
        recuperarEtiqueta();
        initview();

    }
    private void recuperarEtiqueta() {
        Idgrupo = textIdgrupo.getText().toString();
        Log.d(Idgrupo, "claroque: ");
    }
    private void initview(){
        WebView webView = (WebView) findViewById(R.id.webViewEvento);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.1.67/Changa/content_add_coordinador_estatal.php?Id_Grupo=" + Idgrupo);
    }
    public void cerrar(View view){
        finish();
    }
}