package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.listadapters.AlunoListAdapter;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.FindAllRequestTask;

public class MainActivity extends AppCompatActivity {
    private ListView alunosListView;
    private AlunoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new FindAllRequestTask(){
            @Override
            protected void onPostExecute(Aluno[] alunos) {
                alunosListView = (ListView) findViewById(R.id.alunos);
                adapter = new AlunoListAdapter(getApplicationContext(), Arrays.asList(alunos));
                alunosListView.setAdapter(adapter);
            }
        }.execute();
    }

    public void novoAluno(View view){
        startActivity(new Intent(this, InserirAlunoActivity.class));
    }
}

