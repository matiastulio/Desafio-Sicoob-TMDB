package br.com.tuliomatias.desafiosicoob.fragments;

import br.com.tuliomatias.desafiosicoob.R;

public class NosCinemasFragment extends AbstractListFragment {

    @Override
    public String getListPath() {
        return "now_playing";
    }

    @Override
    public String titulo() {
        return getResources().getString(R.string.nos_cinemas);
    }

    @Override
    public boolean isStoredData() {
        return false;
    }
}
