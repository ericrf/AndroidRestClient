package br.ufpr.sept.androidrestclient;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.Arrays;

import br.ufpr.sept.androidrestclient.domain.Aluno;
import br.ufpr.sept.androidrestclient.domain.Endereco;

/**
 * Created by Eric on 20/03/2017.
 */

public abstract class AbstractAlunoFormularioActivity extends AppCompatActivity implements AlunoFormularioFragment.OnFragmentInteractionListener {
    protected EditText etNome;
    protected EditText etCpf;
    protected EditText etIdade;
    protected EditText etCEP;
    protected EditText etLogradouro;
    protected EditText etNumero;
    protected EditText etComplemento;
    protected EditText etBairro;
    protected EditText etCidade;
    protected EditText etEstado;

    protected abstract long obterAlunoEnderecoId();
    protected abstract long obterAlunoId();

    protected void startComponents() {
        etCpf = (EditText) findViewById(R.id.cpf);
        etNome = (EditText) findViewById(R.id.nome);
        etIdade = (EditText) findViewById(R.id.idade);
        etCEP = (EditText) findViewById(R.id.cep);
        etLogradouro = (EditText) findViewById(R.id.logradouro);
        etNumero = (EditText) findViewById(R.id.numero);
        etComplemento = (EditText) findViewById(R.id.complemento);
        etBairro = (EditText) findViewById(R.id.bairro);
        etCidade = (EditText) findViewById(R.id.cidade);
        etEstado = (EditText) findViewById(R.id.estado);
    }

    protected Aluno getAluno() {
        String nome = etNome.getText().toString();
        String cpf = etCpf.getText().toString();
        String idade = etIdade.getText().toString();
        String cep = etCEP.getText().toString();
        String logradouro = etLogradouro.getText().toString();
        String numero = etNumero.getText().toString();
        String complemento = etComplemento.getText().toString();
        String bairro = etBairro.getText().toString();
        String cidade = etCidade.getText().toString();
        String estado = etEstado.getText().toString();

        Endereco endereco = new Endereco(obterAlunoEnderecoId(), logradouro, numero, complemento, bairro, Integer.valueOf("".equals(cep) ? "0" : cep), cidade, estado);
        return new Aluno(obterAlunoId(), cpf, nome, Integer.valueOf("".equals(idade) ? "0" : idade), Arrays.asList(new Endereco[]{endereco}));
    }

    public void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
