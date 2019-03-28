package br.com.tuliomatias.desafiosicoob.fragments;

import br.com.tuliomatias.desafiosicoob.R;

public class FavoritosFragment extends AbstractListFragment {

    @Override
    public String getListPath() {
        return null;
    }

    @Override
    public String titulo() {
        return getResources().getString(R.string.favoritos);
    }
}
