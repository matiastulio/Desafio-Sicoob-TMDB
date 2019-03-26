package br.com.tuliomatias.desafiosicoob.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.adapters.FilmesAdapter;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.MessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.ApiTmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.IRespostaDaApiTmdb;

public abstract class AbstractListFragment extends android.support.v4.app.Fragment implements IRespostaDaApiTmdb {

    public abstract String getListPath();
    public abstract String titulo();

    protected ArrayList<Filme> filmes;

    protected RecyclerView listaView;
    protected FilmesAdapter adapter;
    protected ApiTmdb api;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_list_layout,container,false);

        getActivity().setTitle(titulo());

        filmes = new ArrayList<>();
        api = new ApiTmdb(Tmdb.builder()
                        .numeroPaginaAtual(1)
                        .lingua(getResources()
                        .getString(R.string.default_language))
                        .apiKey(getResources()
                        .getString(R.string.api_key))
                        .baseRequestPath(getResources().getString(R.string.base_path))
                        .build(),this);

        int qtdColunas = getResources().getInteger(R.integer.qtd_colunas);

        listaView = (RecyclerView) v.findViewById(R.id.lista_filmes);
        listaView.setLayoutManager(new GridLayoutManager(getActivity(), qtdColunas));
        listaView.setHasFixedSize(false);

        adapter = new FilmesAdapter(filmes);

        listaView.setAdapter(adapter);

        api.getFilmes();

        return v;
    }

    @Override
    public void respostaDaApi(ArrayList<Filme> filmes) {
        this.filmes.addAll(filmes);
//        adapter.notifyDataSetChanged();

    }

    @Override
    public String getPathPesquisa() {
        return getListPath();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageEvent(final MessageEvent event){
        if(event.dto == null){
            this.filmes.addAll(event.filmes);
            adapter.notifyDataSetChanged();
        }else{

        }
    }
}
