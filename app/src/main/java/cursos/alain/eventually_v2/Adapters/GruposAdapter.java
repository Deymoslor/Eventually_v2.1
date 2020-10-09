package cursos.alain.eventually_v2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos1;

public class GruposAdapter extends RecyclerView.Adapter<GruposAdapter.GruposHolder> implements View.OnClickListener {

    List<Grupos1> listaGrupos;

    private View.OnClickListener listener;

    public GruposAdapter(List<Grupos1> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    @Override
    public GruposHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_grupos,parent,false);
        vista.setOnClickListener(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new GruposHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GruposHolder holder, int position) {

        holder.TxtNombre_Grupo.setText(listaGrupos.get(position).getNombre_Grupo().toString());
        holder.TxtEtiqueta.setText(listaGrupos.get(position).getEtiqueta().toString());
        holder.TxtDescripcion.setText(listaGrupos.get(position).getDescripcion().toString());
        holder.TxtIdGrupo.setText(listaGrupos.get(position).getIdGrupo().toString());
    }

    @Override
    public int getItemCount() {
        if (listaGrupos != null){
            return listaGrupos.size();
    }else{
        return 0;
    }
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public class GruposHolder extends RecyclerView.ViewHolder {

        TextView TxtNombre_Grupo,TxtEtiqueta,TxtDescripcion,TxtIdGrupo;

        public GruposHolder(@NonNull View itemView) {
            super(itemView);
            TxtIdGrupo = (TextView) itemView.findViewById(R.id.textIdGrupoMisGrupos);
            TxtNombre_Grupo = (TextView) itemView.findViewById(R.id.Txt_Nombre_Grupo);
            TxtEtiqueta = (TextView) itemView.findViewById(R.id.Txt_Etiqueta);
            TxtDescripcion = (TextView) itemView.findViewById(R.id.Txt_Descripcion);
        }
    }
}
