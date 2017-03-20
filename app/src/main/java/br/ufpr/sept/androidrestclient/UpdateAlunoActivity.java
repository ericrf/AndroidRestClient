package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.Arrays;

import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain.Endereco;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.DeleteRequestTask;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.InsertRequestTask;
import br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.services.UpdateRequestTask;

public class UpdateAlunoActivity extends AppCompatActivity implements AlunoFormularioFragment.OnFragmentInteractionListener {

    private Aluno aluno = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_aluno);
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if(null != aluno){
            ((EditText)findViewById(R.id.nome)).setText(aluno.getNome());
            ((EditText)findViewById(R.id.cpf)).setText(aluno.getCpf());
            ((EditText)findViewById(R.id.idade)).setText(String.valueOf(aluno.getIdade()));
            Endereco endereco = aluno.getEnderecos().get(0);
            ((EditText)findViewById(R.id.cep)).setText(String.valueOf(endereco.getCep()));
            ((EditText)findViewById(R.id.logradouro)).setText(endereco.getLogradouro());
            ((EditText)findViewById(R.id.numero)).setText(endereco.getNumero());
            ((EditText)findViewById(R.id.complemento)).setText(endereco.getComplemento());
            ((EditText)findViewById(R.id.bairro)).setText(endereco.getBairro());
            ((EditText)findViewById(R.id.cidade)).setText(endereco.getCidade());
            ((EditText)findViewById(R.id.estado)).setText(endereco.getEstado());
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void update(View v){
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

        Endereco endereco = new Endereco(aluno.getEnderecos().get(0).getId(), logradouro, numero, complemento, bairro, Integer.valueOf(cep), cidade, estado);
        Aluno a = new Aluno(Long.valueOf(aluno.getMatricula()), cpf, nome, Integer.valueOf(idade), Arrays.asList(new Endereco[]{endereco}));

        new UpdateRequestTask(){
            @Override
            protected void onPostExecute(Aluno aluno) {
                if(null == aluno){
                    //TODO: imprimir mensagem de erro
                }else{
                    startMainActivity();
                }
            }
        }.execute(a);
    }

    public void remover(View v) {

         new DeleteRequestTask(){
             @Override
             protected void onPostExecute(Void aVoid) {
                 startMainActivity();
             }
         }.execute(aluno);
    }

    public void startMainActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
