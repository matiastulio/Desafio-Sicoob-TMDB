package br.com.tuliomatias.desafiosicoob.fragments;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.Filme;

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
