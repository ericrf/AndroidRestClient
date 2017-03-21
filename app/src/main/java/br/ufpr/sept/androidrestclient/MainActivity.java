package br.ufpr.sept.androidrestclient;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Arrays;

import br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.listadapters.AlunoListAdapter;
import br.ufpr.sept.androidrestclient.services.FindAllRequestTask;
import br.ufpr.sept.androidrestclient.services.FindOneRequestTask;

public class MainActivity extends AppCompatActivity {
    private ListView alunosListView;
    private AlunoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("QUERY", query);
        }else{
            findAllRequestTask();
        }
    }

    private void findAllRequestTask() {
        new FindAllRequestTask(){
            @Override
            protected void onPostExecute(Aluno[] alunos) {
                alunosListView = (ListView) findViewById(R.id.alunos);
                adapter = new AlunoListAdapter(getApplicationContext(), Arrays.asList(alunos));
                alunosListView.setAdapter(adapter);
                alunosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), UpdateAlunoActivity.class);
                        intent.putExtra("aluno", (Aluno) parent.getItemAtPosition(position));
                        startActivity(intent);
                    }
                });
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView view = (SearchView) item.getActionView();
        view.setInputType(InputType.TYPE_CLASS_NUMBER);
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                if("0".equals(query)){
                    findAllRequestTask();
                }else{
                    findOneRequestTask(query);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    private void findOneRequestTask(String query) {
        new FindOneRequestTask(){
            @Override
            protected void onPostExecute(Aluno aluno) {
                adapter = new AlunoListAdapter(getApplicationContext(), Arrays.asList(new Aluno[]{aluno}));
                alunosListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }.execute(Long.valueOf(query));
    }

    public void novoAluno(View view){
        startActivity(new Intent(this, InserirAlunoActivity.class));
    }
}

