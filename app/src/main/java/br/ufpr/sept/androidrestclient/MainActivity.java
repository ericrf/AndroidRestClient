package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    TextView greetingIdText;
    TextView greetingContentText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.findOne)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new FindOneRequestTask().execute();
            }
        });

        ((Button) findViewById(R.id.findAll)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new FindAllRequestTask().execute();
            }
        });

        ((Button) findViewById(R.id.insert)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new InsertRequestTask().execute();
            }
        });

        ((Button) findViewById(R.id.update)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateRequestTask t = new UpdateRequestTask(){
                    @Override
                    protected void onPostExecute(Aluno aluno) {
                        logarAluno(aluno);
                    }

                    @Override
                    protected void onPreExecute() {
                        Log.d("[HTTPREQUEST]","UpdateRequestTask");
                    }
                };

                t.execute();
            }
        });

        ((Button) findViewById(R.id.delete)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DeleteRequestTask().execute();
            }
        });
    }


    final String baseUrl = "https://soa-service.herokuapp.com/alunos/";

    public void novoAluno(View view) {
        Intent i = new Intent(this, FormularioActivity.class);
        startActivity(i);
    }


    private class DeleteRequestTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String url = baseUrl + "1";
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                template.delete(url);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d("[HTTPREQUEST]", "DeleteRequestTask");
        }
    }


    private class InsertRequestTask extends AsyncTask<Void, Void, Aluno> {
        @Override
        protected Aluno doInBackground(Void... params) {
            try {

                Endereco endereco = new Endereco(0L, "Rua Acelino Grande", "1003", "casa", "Butiatuvinha", 82320390, "Curitiba", "Paraná");
                Aluno aluno = new Aluno();
                aluno.setMatricula(0);
                aluno.setNome("Ana Paula Bail dos Santos");
                aluno.setCpf("62461336490");
                aluno.setIdade(27);
                aluno.setEnderecos(Arrays.asList(endereco));

                String url = baseUrl;
                RestTemplate template = new RestTemplate();
                template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                ResponseEntity<Aluno> entity = template.postForEntity(url, aluno, Aluno.class);

                return entity.getBody();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Aluno aluno) {
            logarAluno(aluno);
        }

        @Override
        protected void onPreExecute() {
            Log.d("[HTTPREQUEST]","InsertRequestTask");
        }
    }

    private class FindAllRequestTask extends AsyncTask<Void, Void, Aluno[]> {
        @Override
        protected Aluno[] doInBackground(Void... params) {
            try {
                String url = baseUrl;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(url, Aluno[].class);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Aluno[] alunos) {
            for (Aluno aluno: alunos){
                logarAluno(aluno);
            }
        }

        @Override
        protected void onPreExecute() {
            Log.d("[HTTPREQUEST]","FindAllRequestTask");
        }
    }

    private class FindOneRequestTask extends AsyncTask<Void, Void, Aluno> {
        @Override
        protected Aluno doInBackground(Void... params) {
            try {
                String url = baseUrl + "1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(url, Aluno.class);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Aluno aluno) {
            logarAluno(aluno);
        }

        @Override
        protected void onPreExecute() {
            Log.d("[HTTPREQUEST]","FindOneRequestTask");
        }
    }

    private void logarAluno(Aluno aluno) {
        Log.d("[ALUNO]", aluno.toString());
        for(Endereco endereco : aluno.getEnderecos()){
            Log.d("[ALUNO][ENDERECO]", endereco.toString());
        }
    }
}

