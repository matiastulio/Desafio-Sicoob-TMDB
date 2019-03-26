package br.com.tuliomatias.desafiosicoob;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.tuliomatias.desafiosicoob.fragments.AbstractListFragment;
import br.com.tuliomatias.desafiosicoob.fragments.FavoritosFragment;
import br.com.tuliomatias.desafiosicoob.fragments.FuturosFragment;
import br.com.tuliomatias.desafiosicoob.fragments.NosCinemasFragment;
import br.com.tuliomatias.desafiosicoob.fragments.PopularesFragment;
import br.com.tuliomatias.desafiosicoob.fragments.SobreFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.nav_view)
    protected NavigationView navView;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;

    private ActionBarDrawerToggle drawerToggle;
    AbstractListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        itemSelecionado(R.id.drawer_popular);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawer.closeDrawers();
        return itemSelecionado(item.getItemId());
    }

    private boolean itemSelecionado(int item){
        FragmentTransaction frgTr = getSupportFragmentManager().beginTransaction();
        switch (item){
            case R.id.drawer_popular:
                fragment = new PopularesFragment();
                break;
            case R.id.drawer_nos_cinemas:
                fragment = new NosCinemasFragment();
                break;
            case R.id.drawer_futuros:
                fragment = new FuturosFragment();
                break;
            case R.id.drawer_favoritos:
                fragment = new FavoritosFragment();
                break;
            case R.id.drawer_sobre:
                fragment = new SobreFragment();
                break;
            default:
                return false;
        }

        frgTr.replace(R.id.fragment_holder, fragment);
        frgTr.commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(isDrawerAberto())
            fechaDrawer();
        else
            super.onBackPressed();
    }

    private void fechaDrawer() {
        if (drawer != null) drawer.closeDrawer(GravityCompat.START);
    }

    private boolean isDrawerAberto(){
        return drawer != null && drawer.isDrawerOpen(GravityCompat.START);
    }
}
