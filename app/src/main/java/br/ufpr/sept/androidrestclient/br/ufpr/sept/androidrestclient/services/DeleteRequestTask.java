package br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services;

import android.util.Log;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;

/**
 * Created by Eric on 16/03/2017.
 */
public class DeleteRequestTask extends AbstractRequestTask<Aluno, Void, Void> {
    @Override
    protected Void doInBackground(Aluno... alunos) {
        try {
            createRestTemplate().delete(Constants.BASE_URL + "/" + alunos[0].getMatricula());
        } catch (Exception e) {
            Log.e("DeleteRequestTask", e.getMessage(), e);
        }

        return null;
    }
}
