package cursos.alain.eventually_v2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cursos.alain.eventually_v2.R;

public class DetalleGrupoFragment extends Fragment {
    TextView nombreDetalle;
    ImageView imagenDetalle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_grupo_fragment,container,false);
        nombreDetalle = view.findViewById(R.id.nombre_detalle);
        imagenDetalle = view.findViewById(R.id.imagen_detalle);
        //Crear objeto bundle para recibir el objeto enviado por argumentos
        Bundle objetoGrupo = getArguments();
        Grupos grupos = null;
        //validacion para verificar si existen argumentos enviados para mostrar
        if(objetoGrupo != null){
            grupos = (Grupos) objetoGrupo.getSerializable("objeto");
            //Establecer los datos en las vistas
            nombreDetalle.setText(grupos.getNombre());
            imagenDetalle.setImageResource(grupos.getImagenid());
        }
        return view;
    }
}
