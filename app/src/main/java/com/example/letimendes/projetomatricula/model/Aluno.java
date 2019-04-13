package com.example.letimendes.projetomatricula.model;

public class Aluno {

    private String nome;
    private String cpf;
    private String email;
    private String codDisciplina;

    //Status - 0 = feito, 1 = enviado para o professor, 2 = matricula aceita, 3 = matricula recusada
    private int status;

    public boolean selecionado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
