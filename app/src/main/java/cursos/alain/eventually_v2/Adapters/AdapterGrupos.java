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

import cursos.alain.eventually_v2.R;
import cursos.alain.eventually_v2.entidades.Grupos;

public class AdapterGrupos extends RecyclerView.Adapter<AdapterGrupos.ViewHolder> implements View.OnClickListener {

    ArrayList<Grupos> model;
    LayoutInflater inflater;

    //listener
    private View.OnClickListener listener;

    public AdapterGrupos(Context context, ArrayList<Grupos> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_grupos, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre();
        String intereses = model.get(position).getIntereses();
        int imagen = model.get(position).getImagenid();
        holder.nombres.setText(nombre);
        holder.intereses.setText(intereses);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres,intereses;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombre_grupo);
            intereses = itemView.findViewById(R.id.intereses);
            imagen = itemView.findViewById(R.id.imagen_grupo);
        }
    }
}
