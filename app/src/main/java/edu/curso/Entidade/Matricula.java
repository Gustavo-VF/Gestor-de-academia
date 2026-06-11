package edu.curso.Entidade;

import java.sql.Date;

public class Matricula {
    private int id;
    private Date dataInicio;
    private Date dataVencimento;
    private String status;
    private String observacao;
    private int planoId;
    private int usuarioId;

    public Matricula() {
    }

    public Matricula(int id, Date dataInicio, Date dataVencimento,
            String status, String observacao,
            int planoId, int usuarioId) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.observacao = observacao;
        this.planoId = planoId;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getPlanoId() {
        return planoId;
    }

    public void setPlanoId(int planoId) {
        this.planoId = planoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}