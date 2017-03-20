package br.ufpr.sept.androidrestclient.domain;

import java.io.Serializable;
import java.util.List;

public class Aluno implements Serializable {

    private long matricula;
    private String cpf;
    private String nome;
    private int idade;
    private List<Endereco> enderecos;

    public Aluno(){}

    public Aluno(long matricula, String cpf, String nome, int idade, List<Endereco> enderecos) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.enderecos = enderecos;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
