package edu.curso.Entidade;

public class Plano {
    private int id;
    private String nome;
    private String descricao;
    private int duracao;
    private double preco;
    private String modalidade;
    private boolean status;

    public Plano() {
    }

    public Plano(int id, String nome, String descricao, int duracao,
            double preco, String modalidade, boolean status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
        this.modalidade = modalidade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}