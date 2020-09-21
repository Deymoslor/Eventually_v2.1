package cursos.alain.eventually_v2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Buscar_Grupos;

public class BuscarGruposAdapter extends RecyclerView.Adapter<BuscarGruposAdapter.BuscarGruposHolder> implements View.OnClickListener {

    List<Buscar_Grupos> listaGrupos;
    private View.OnClickListener listener;



    public BuscarGruposAdapter(List<Buscar_Grupos> listaGrupos){
        this.listaGrupos = listaGrupos;

    }

    @NonNull
    @Override
    public BuscarGruposHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_buscargrupos,parent,false);
        vista.setOnClickListener(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new BuscarGruposHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull BuscarGruposHolder holder, int position) {



            holder.Txt_Etiquetas_Grupos.setText(listaGrupos.get(position).getEtiqueta().toString());
    }

    @Override
    public int getItemCount() {
        return listaGrupos.size();
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

    public class BuscarGruposHolder extends RecyclerView.ViewHolder{

        TextView Txt_Etiquetas_Grupos;

        public BuscarGruposHolder(View itemView){
            super(itemView);
            Txt_Etiquetas_Grupos = (TextView) itemView.findViewById(R.id.Txt_Etiquetas_Grupos);
        }
    }
}
