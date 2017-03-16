package br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services;

import android.util.Log;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;

/**
 * Created by Eric on 16/03/2017.
 */
class FindAllRequestTask extends AbstractRequestTask<Void, Void, Aluno[]> {
    @Override
    protected Aluno[] doInBackground(Void... params) {
        try {
            return createRestTemplate().getForObject(Constants.BASE_URL, Aluno[].class);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

}
