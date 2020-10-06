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

public class BuscarGruposDetalleAdapter extends RecyclerView.Adapter<BuscarGruposDetalleAdapter.BuscarGruposAdapterHolder> implements View.OnClickListener {

    List<Grupos1> listaGrupos2;

    private View.OnClickListener listener;

    public BuscarGruposDetalleAdapter(List<Grupos1> listaGrupos2) {
        this.listaGrupos2 = listaGrupos2;
    }


    @Override
    public BuscarGruposAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_grupos_detalle,parent,false);
        vista.setOnClickListener(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new BuscarGruposAdapterHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull BuscarGruposAdapterHolder holder, int position) {

        holder.TxtNombre_Grupo.setText(listaGrupos2.get(position).getNombre_Grupo().toString());
        holder.TxtEtiqueta.setText(listaGrupos2.get(position).getEtiqueta().toString());
        holder.TxtDescripcion.setText(listaGrupos2.get(position).getDescripcion().toString());

    }

    @Override
    public int getItemCount() {
        if (listaGrupos2 != null){
            return listaGrupos2.size();
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

    public class BuscarGruposAdapterHolder extends RecyclerView.ViewHolder {

        TextView TxtNombre_Grupo,TxtEtiqueta,TxtDescripcion;

        public BuscarGruposAdapterHolder(@NonNull View itemView) {
            super(itemView);
            TxtNombre_Grupo = (TextView) itemView.findViewById(R.id.Txt_Nombre_Grupo);
            TxtEtiqueta = (TextView) itemView.findViewById(R.id.Txt_Etiqueta);
            TxtDescripcion = (TextView) itemView.findViewById(R.id.Txt_Descripcion);
        }
    }
}

