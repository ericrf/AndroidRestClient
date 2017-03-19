package br.ufpr.sept.androidrestclient;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Endereco;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.InsertRequestTask;

public class InserirAlunoActivity extends AppCompatActivity implements AlunoFormularioFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_aluno);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void inserir(View v){
        String nome = ((EditText)findViewById(R.id.nome)).getText().toString();
        String cpf = ((EditText)findViewById(R.id.cpf)).getText().toString();
        String idade = ((EditText)findViewById(R.id.idade)).getText().toString();
        String cep = ((EditText)findViewById(R.id.cep)).getText().toString();
        String logradouro = ((EditText)findViewById(R.id.logradouro)).getText().toString();
        String numero = ((EditText)findViewById(R.id.numero)).getText().toString();
        String complemento = ((EditText)findViewById(R.id.complemento)).getText().toString();
        String bairro = ((EditText)findViewById(R.id.bairro)).getText().toString();
        String cidade = ((EditText)findViewById(R.id.cidade)).getText().toString();
        String estado = ((EditText)findViewById(R.id.estado)).getText().toString();

        Endereco endereco = new Endereco(0L, logradouro, numero, complemento, bairro, Integer.valueOf(cep), cidade, estado);

        Aluno aluno = new Aluno(0L, cpf, nome, Integer.valueOf(idade), Arrays.asList(new Endereco[]{endereco}));

        new InsertRequestTask(){
            @Override
            protected void onPostExecute(Aluno aluno) {
                if(null == aluno){
                    //TODO: imprimir mensagem de erro
                }else{
                    Log.d("InsertRequestTask", aluno.toString());
                }
            }
        }.execute(aluno);

    }
}
