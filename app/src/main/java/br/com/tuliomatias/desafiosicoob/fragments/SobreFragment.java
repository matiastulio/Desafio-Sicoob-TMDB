package br.com.tuliomatias.desafiosicoob.fragments;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.Filme;


//TODO essa classe não deve extender de AbstractListFragment
public class SobreFragment extends AbstractListFragment {

    @Override
    public String getListPath() {
        return "Sobre";
    }

    @Override
    public String titulo() {
        return getResources().getString(R.string.sobreApp);
    }
}
