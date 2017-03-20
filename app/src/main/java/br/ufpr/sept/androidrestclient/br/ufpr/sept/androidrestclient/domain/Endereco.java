package br.ufpr.sept.androidrestclient.br.ufpr.sept.androidrestclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Eric on 08/03/2017.
 */

public class Endereco implements Serializable{
    private long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private int cep;
    private String cidade;
    private String estado;

    public Endereco() {}

    public Endereco(long id, String logradouro, String numero, String complemento, String bairro, int cep, String cidade, String estado) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return logradouro + ", nº " + numero + ", " + complemento
                + ", " + bairro + ", " +  cidade + ", " + estado + " - " + String.valueOf(cep).replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})", "$1.$2-$3");
    }

}
