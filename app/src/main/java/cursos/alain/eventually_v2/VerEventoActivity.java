package cursos.alain.eventually_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class VerEventoActivity extends AppCompatActivity {
    TextView textIdgrupo;

    String Idgrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_evento);

        textIdgrupo = findViewById(R.id.textIdGrupoDetalleMisgrupos1);


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
        WebView webView = (WebView) findViewById(R.id.webViewEvento2);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.1.67/Changa/prueba.php?Id_Grupo=" + Idgrupo);
    }
}