package br.com.tuliomatias.desafiosicoob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.models.FilmeMessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Genero;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.ApiTmdb;
import br.com.tuliomatias.desafiosicoob.tmdb.IRespostaDaApiTmdb;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class FilmeDetailActivity extends AppCompatActivity implements IRespostaDaApiTmdb {


    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.filme_image)
    protected ImageView imagemView;

    @BindView(R.id.nome_filme)
    protected TextView nomeFilme;

    @BindView(R.id.release_date)
    protected  TextView releaseDate;

    @BindView(R.id.duracao)
    protected  TextView duracao;

    @BindView(R.id.generos)
    protected TextView generos;

    @BindView(R.id.sinopse)
    protected TextView sinopse;

    @BindView(R.id.vote_average)
    protected TextView voteAverage;


    private Filme filme;
    private int filmeId;
    private static Bitmap imagem;
    private ApiTmdb api;

    public static final String FILME_ID = "filme_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detail);
        ButterKnife.bind(this);

        api = new ApiTmdb(getApplication(),
                Tmdb.builder()
                .numeroPaginaAtual(1)
                .lingua(getResources().getString(R.string.default_language))
                .apiKey(getResources().getString(R.string.api_key))
                .baseRequestPath(getResources().getString(R.string.base_path))
                .baseImageRequestPath(getResources().getString(R.string.default_image_path))
                .imageSize(getResources().getString(R.string.default_image_size))
                .regiao(getResources().getString(R.string.regiao))
                .isStoredData(false)
                .build(),this);

        Intent intent = getIntent();
        filmeId = intent.getIntExtra(FILME_ID,0);
        api.getFilme(filmeId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public  void onMessageEvent(FilmeMessageEvent event){

        if(event != null) {
            filme = event.filme;
            populaView(filme);

            Log.d(TAG, " filme: " + filme.toString());
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public String getPathPesquisa() {
        return null;
    }

    private void populaView(Filme f){
        getSupportActionBar().setTitle(filme.getTitulo());
        nomeFilme.setText(filme.getTitulo());
        duracao.setText(filme.getRuntime() + " minutos");
        releaseDate.setText(filme.getDataLancamento());
        voteAverage.setText(Float.toString(filme.getVoteAverage()));

        StringBuilder s = new StringBuilder();
        for (Genero g :f.getGenres()) {
            s.append(g.getName()).append("\n");
        }

        generos.setText(s.toString());
        sinopse.setText(f.getSinopse());

        Picasso.get().load(
                getResources().getString(R.string.default_image_path) +
                        getResources().getString(R.string.default_image_size) +
                        filme.getImagePath())
                .noPlaceholder().into(imagemView);
    }
}
