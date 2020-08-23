package cursos.alain.eventually_v2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos;

public class AdapterGrupos extends RecyclerView.Adapter<AdapterGrupos.GruposHolder> {
    List<Grupos> listaGrupos;

    public AdapterGrupos(List<Grupos> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    @NonNull
    @Override
    public GruposHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_grupos,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new GruposHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GruposHolder holder, int position) {
        holder.TxtNombreGrupo.setText(listaGrupos.get(position).getNombre().toString());
        holder.TxtEtiquetas.setText(listaGrupos.get(position).getEtiqueta().toString());
        holder.TxtDescripcion.setText(listaGrupos.get(position).getDescripcion().toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class GruposHolder extends RecyclerView.ViewHolder {

        TextView TxtNombreGrupo,TxtEtiquetas,TxtDescripcion;

        public GruposHolder(@NonNull View itemView) {
            super(itemView);

            TxtNombreGrupo = (TextView) itemView.findViewById(R.id.Txt_Documento);
            TxtEtiquetas = (TextView) itemView.findViewById(R.id.Txt_Etiquetas);
            TxtDescripcion = (TextView) itemView.findViewById(R.id.Txt_Descripcion);
        }
    }
}
