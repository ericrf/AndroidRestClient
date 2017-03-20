package br.ufpr.sept.androidrestclient.services;

import android.util.Log;

import br.ufpr.sept.androidrestclient.domain.EnderecoBuscaCEP;

/**
 * Created by Eric on 16/03/2017.
 */
public class BuscaCEPRequestTask extends AbstractRequestTask<String, Void, EnderecoBuscaCEP> {

    @Override
    protected EnderecoBuscaCEP doInBackground(String... cep) {
        try {
            return createRestTemplate().getForObject("http://correiosapi.apphb.com/cep/" + cep[0], EnderecoBuscaCEP.class);
        } catch (Exception e) {
            Log.e("BuscaCEPRequestTask", e.getMessage(), e);
        }
        return null;
    }
}
