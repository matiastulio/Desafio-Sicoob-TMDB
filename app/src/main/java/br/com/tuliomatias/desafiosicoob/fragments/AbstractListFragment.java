package br.com.tuliomatias.desafiosicoob.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.FilmeDetailActivity;
import br.com.tuliomatias.desafiosicoob.adapters.FilmesAdapter;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.ApiMessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.ApiTmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.IRespostaDaApiTmdb;

import static android.content.ContentValues.TAG;

public abstract class AbstractListFragment extends android.support.v4.app.Fragment implements IRespostaDaApiTmdb, FilmesAdapter.AdapterViewItemClickListener {

    public abstract String getListPath();
    public abstract String titulo();

    protected ArrayList<Filme> filmes;

    protected RecyclerView listaView;
    private GridLayoutManager layoutManager;
    protected FilmesAdapter adapter;
    protected ApiTmdb api;
    private boolean isLoading = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_list_layout,container,false);

        getActivity().setTitle(titulo());

        filmes = new ArrayList<>();
        api = new ApiTmdb(Tmdb.builder()
                        .numeroPaginaAtual(1)
                        .lingua(getResources().getString(R.string.default_language))
                        .apiKey(getResources().getString(R.string.api_key))
                        .baseRequestPath(getResources().getString(R.string.base_path))
                        .baseImageRequestPath(getResources().getString(R.string.default_image_path))
                        .imageSize(getResources().getString(R.string.default_image_size))
                        .regiao(getResources().getString(R.string.regiao))
                        .build(),this);

        int qtdColunas = getResources().getInteger(R.integer.qtd_colunas);

        listaView = (RecyclerView) v.findViewById(R.id.lista_filmes);
        layoutManager = new GridLayoutManager(getActivity(), qtdColunas);
        listaView.setLayoutManager(layoutManager);
        listaView.addOnScrollListener(getRVOnScrollListener());
        listaView.setHasFixedSize(false);

        adapter = new FilmesAdapter(filmes);
        adapter.setAdapterViewItemClickListener(this);

        listaView.setAdapter(adapter);

        api.getFilmes();

        return v;
    }


    @Override
    public String getPathPesquisa() {
        return getListPath();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v, int position) {
        Filme f = filmes.get(position);
        Log.d(TAG, "onItemClick position: " + position+" filme: " + f.getTitulo());
        Intent intent = new Intent(getActivity(), FilmeDetailActivity.class);
        intent.putExtra(FilmeDetailActivity.FILME_ID,f.getId());

        getActivity().startActivity(intent);

    }

    @Override
    public void onLongClick(View v, int position) {
        Log.d(TAG, "onItemLongClick position: " + position);

    }

    private RecyclerView.OnScrollListener getRVOnScrollListener(){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItems = layoutManager.getItemCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItems <= (lastPosition + 6)){
                    isLoading = true;
                    api.getFilmes();
                }
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageEvent(final ApiMessageEvent event){

        if(!event.isImageUpdated){
            this.filmes.addAll(event.filmes);
            isLoading = false;
        }

        adapter.notifyDataSetChanged();

    }
}
