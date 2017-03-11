package br.ufpr.sept.androidrestclient;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

public class UpdateRequestTask extends AsyncTask<Void, Void, Aluno> {

    final String baseUrl = "https://soa-service.herokuapp.com/alunos/";
    @Override
    protected Aluno doInBackground(Void... params) {
        try {
            Aluno aluno = new Aluno();
            aluno.setMatricula(1);
            aluno.setNome("Ana Paula Bail dos Santos");
            aluno.setCpf("3333333333");
            aluno.setIdade(27);
            aluno.setEnderecos(Arrays.asList( new Endereco[]{
                    new Endereco(1L, "Rua Acelino Grande", "1003", "casa", "Butiatuvinha", 82320390, "Curitiba", "Paraná"),
                    new Endereco(2L, "Rua modificada", "1003", "casa", "Butiatuvinha", 82320390, "Curitiba", "Paraná")
            }));

            String url = baseUrl;
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            HttpEntity<Aluno> requestEntity = new HttpEntity<Aluno>(aluno, new HttpHeaders());
            HttpEntity<Aluno> response = template.exchange(url, HttpMethod.PUT, requestEntity, Aluno.class, new HashMap<String, String>());

            return response.getBody();

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }
}