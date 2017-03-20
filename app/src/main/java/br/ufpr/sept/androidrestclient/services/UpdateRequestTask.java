package br.ufpr.sept.androidrestclient.services;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import br.ufpr.sept.androidrestclient.domain.Aluno;

public class UpdateRequestTask extends AsyncTask<Aluno, Aluno, Aluno> {

    @Override
    protected Aluno doInBackground(Aluno... alunos) {
        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpEntity<Aluno> requestEntity = new HttpEntity<Aluno>(alunos[0], new HttpHeaders());
            HttpEntity<Aluno> response = template.exchange(Constants.BASE_URL, HttpMethod.PUT, requestEntity, Aluno.class, new HashMap<String, String>());

            return response.getBody();

        } catch (Exception e) {
            Log.e("UpdateRequestTask", e.getMessage(), e);
        }

        return null;
    }
}