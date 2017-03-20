package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

import br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.domain.Endereco;
import br.ufpr.sept.androidrestclient.services.InsertRequestTask;

public class InserirAlunoActivity extends AbstractAlunoFormularioActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_aluno);
        startComponents();
    }

    @Override
    protected long obterAlunoEnderecoId() {
        return 0L;
    }

    @Override
    protected long obterAlunoId() {
        return 0L;
    }

    public void inserir(View v){
        Aluno aluno = getAluno();

        new InsertRequestTask(){
            @Override
            protected void onPostExecute(Aluno aluno) {
                if(null == aluno){
                    Toast.makeText(getApplicationContext(), "Erro ao inserir aluno", Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        }.execute(aluno);

    }
}
