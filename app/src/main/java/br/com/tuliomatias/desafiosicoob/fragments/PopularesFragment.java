package br.com.tuliomatias.desafiosicoob.fragments;

import br.com.tuliomatias.desafiosicoob.R;

public class PopularesFragment extends AbstractListFragment {

    @Override
    public String getListPath() {
        return "popular";
    }

    @Override
    public String titulo() {
        return getResources().getString(R.string.popular);
    }
}
