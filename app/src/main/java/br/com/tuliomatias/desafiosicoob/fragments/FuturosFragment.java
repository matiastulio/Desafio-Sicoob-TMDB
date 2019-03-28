package br.com.tuliomatias.desafiosicoob.fragments;

import br.com.tuliomatias.desafiosicoob.R;

public class FuturosFragment extends AbstractListFragment {

    @Override
    public String getListPath() {
        return "upcoming";
    }

    @Override
    public String titulo() {
        return getResources().getString(R.string.futuros);
    }
}
