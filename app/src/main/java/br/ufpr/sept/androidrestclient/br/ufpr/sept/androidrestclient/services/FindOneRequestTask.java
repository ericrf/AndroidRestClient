package br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services;

import android.util.Log;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;

/**
 * Created by Eric on 16/03/2017.
 */
class FindOneRequestTask extends AbstractRequestTask<Long, Void, Aluno> {

    @Override
    protected Aluno doInBackground(Long... matriculas) {
        try {
            return createRestTemplate().getForObject(Constants.BASE_URL + "/" + matriculas[0], Aluno.class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}
