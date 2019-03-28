package br.com.tuliomatias.desafiosicoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.models.FilmeMessageEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

public class FilmeDetail extends AppCompatActivity {


    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.filme_image)
    protected ImageView imagem;

    @BindView(R.id.nome_filme)
    protected TextView nomeFilme;

    @BindView(R.id.release_date)
    protected  TextView releaseDate;

    private Filme filme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme_detail);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public  void onMessageEvent(FilmeMessageEvent event){

        FilmeMessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(FilmeMessageEvent.class);
        if(stickyEvent != null) {
            filme = stickyEvent.filme;
            getSupportActionBar().setTitle(filme.getTitulo());
            imagem.setImageBitmap(filme.getImage());
            nomeFilme.setText(filme.getTitulo());
            releaseDate.setText("Lançamento: "+filme.getDataLancamento());

            Log.d(TAG, " filme: " + filme.getTitulo());
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
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
