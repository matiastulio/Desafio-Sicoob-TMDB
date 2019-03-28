package br.com.tuliomatias.desafiosicoob.fragments;

import br.com.tuliomatias.desafiosicoob.R;


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
