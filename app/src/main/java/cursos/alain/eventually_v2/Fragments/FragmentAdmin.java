package cursos.alain.eventually_v2.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.registro_Usuarios;
import cursos.alain.eventually_v2.registros_admin;

public class FragmentAdmin extends Fragment {

    Button btnVerde;
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_admin, container, false);

        btnVerde = (Button) vista.findViewById(R.id.registros);

        btnVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente = new Intent(getContext(), registros_admin.class);
                startActivity(siguiente);
            }
        });

        return vista;
    }

}
