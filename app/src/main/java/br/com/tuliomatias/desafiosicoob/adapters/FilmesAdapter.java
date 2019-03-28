package br.com.tuliomatias.desafiosicoob.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class FilmesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Filme> filmes;
    private static AdapterViewItemClickListener listener;


    public FilmesAdapter(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_layout,parent,false);

        return new FilmeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Filme f = filmes.get(position);
        FilmeHolder filmeHolder = (FilmeHolder) holder;

        filmeHolder.getImagem().setImageBitmap(f.getImage());
        filmeHolder.getTitulo().setText(f.getTitulo());


    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public void setAdapterViewItemClickListener(AdapterViewItemClickListener listener){
        FilmesAdapter.listener = listener;
    }

    public class FilmeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Getter @Setter(AccessLevel.PROTECTED) TextView titulo;

        @Getter @Setter(AccessLevel.PROTECTED)  ImageView imagem;

        public FilmeHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.item_titulo);
            imagem = (ImageView) itemView.findViewById(R.id.item_imagem);
            imagem.setOnClickListener(this);
            imagem.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onLongClick(view,getAdapterPosition());
            return true;
        }
    }
    public interface AdapterViewItemClickListener{
        public void onClick(View v, int position);
        public void onLongClick(View v, int position);
    }
}
