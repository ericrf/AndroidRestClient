package br.ufpr.sept.androidrestclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends AppCompatActivity {

    TextView greetingIdText;
    TextView greetingContentText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startViews();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new HttpRequestTask().execute();
            }
        });
    }

    private void startViews() {
        greetingIdText = (TextView) findViewById(R.id.id_value);
        greetingContentText = (TextView) findViewById(R.id.content_value);
        button = (Button) findViewById(R.id.button);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Aluno> {
        @Override
        protected Aluno doInBackground(Void... params) {
            try {
                final String url = "http://10.0.2.2:8080/alunos/1";
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
            Log.d("[ALUNO]", aluno.toString());
            for(Endereco endereco : aluno.getEnderecos()){
                Log.d("[ALUNO][ENDERECO]", endereco.toString());
            }
        }

        @Override
        protected void onPreExecute() {
            Log.d("[ALUNO]", "");

        }
    }
}

