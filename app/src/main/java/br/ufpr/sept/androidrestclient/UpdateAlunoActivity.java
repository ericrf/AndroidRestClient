package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.domain.Endereco;
import br.ufpr.sept.androidrestclient.services.DeleteRequestTask;
import br.ufpr.sept.androidrestclient.services.UpdateRequestTask;

public class UpdateAlunoActivity extends AbstractAlunoFormularioActivity {

    private Aluno aluno = null;

    @Override
    protected long obterAlunoEnderecoId() {
        return aluno.getEnderecos().get(0).getId();
    }

    @Override
    protected long obterAlunoId() {
        return Long.valueOf(aluno.getMatricula());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_aluno);
        startComponents();
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (null != aluno) {
            preencherAluno();
        }else{
            Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void preencherAluno() {
        etNome.setText(aluno.getNome());
        etCpf.setText(aluno.getCpf());
        etIdade.setText(String.valueOf(aluno.getIdade()));

        preencherEndereco();
    }

    private void preencherEndereco() {
        Endereco endereco = aluno.getEnderecos().get(0);
        etCEP.setText(String.valueOf(endereco.getCep()));
        etLogradouro.setText(endereco.getLogradouro());
        etNumero.setText(endereco.getNumero());
        etComplemento.setText(endereco.getComplemento());
        etBairro.setText(endereco.getBairro());
        etCidade.setText(endereco.getCidade());
        etEstado.setText(endereco.getEstado());
    }

    public void update(View v) {
        Aluno a = getAluno();
        new UpdateRequestTask() {
            @Override
            protected void onPostExecute(Aluno aluno) {
                if (null == aluno) {
                    Toast.makeText(getApplicationContext(), "Erro ao atualizar aluno", Toast.LENGTH_LONG).show();
                } else {
                    startMainActivity();
                }
            }
        }.execute(a);
    }

    public void remover(View v) {
        new DeleteRequestTask() {
            @Override
            protected void onPostExecute(Void aVoid) {
                startMainActivity();
            }
        }.execute(aluno);
    }


}
