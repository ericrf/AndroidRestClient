package br.ufpr.sept.androidrestclient.services;

import android.util.Log;

import br.ufpr.sept.androidrestclient.domain.Aluno;

/**
 * Created by Eric on 16/03/2017.
 */
public class InsertRequestTask extends AbstractRequestTask<Aluno, Void, Aluno> {
    @Override
    protected Aluno doInBackground(Aluno... alunos) {
        try {
            return createRestTemplate().postForEntity(Constants.BASE_URL, alunos[0], Aluno.class).getBody();
        } catch (Exception e) {
            Log.e("InsertRequestTask", e.getMessage(), e);
        }

        return null;
    }
}
