package cursos.alain.eventually_v2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cursos.alain.eventually_v2.PersonalizarDatosUsuario;
import cursos.alain.eventually_v2.R;

public class FragmentPermisoPersonalizarCuenta extends Fragment {
    Button Bnt_personalizar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permiso_personalizar_cuenta,container,false);
        Bnt_personalizar=(Button) view.findViewById(R.id.btn_personalizar);
        Bnt_personalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Siguiente=new Intent(getContext(), PersonalizarDatosUsuario.class);
                startActivity(Siguiente);
            }
        });
        return view;
    }
}
