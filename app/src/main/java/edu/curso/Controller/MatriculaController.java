package edu.curso.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import edu.curso.Contexto;
import edu.curso.DAO.MatriculaDAO;
import edu.curso.DAO.PlanoDAO;
import edu.curso.Entidade.Matricula;
import edu.curso.Entidade.Plano;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MatriculaController {

    private MatriculaDAO dao = new MatriculaDAO();
    private PlanoDAO planoDAO = new PlanoDAO();

    private ObjectProperty<LocalDate> dataInicio = new SimpleObjectProperty<>(LocalDate.now());
    private ObjectProperty<LocalDate> dataVencimento = new SimpleObjectProperty<>();
    private StringProperty status = new SimpleStringProperty("ATIVA");
    private StringProperty observacao = new SimpleStringProperty("");
    private ObjectProperty<Plano> planoSelecionado = new SimpleObjectProperty<>();

    private ObservableList<Matricula> matriculas = FXCollections.observableArrayList();
    private ObservableList<Plano> planos = FXCollections.observableArrayList();

    private Matricula matriculaSelecionada = null;

    public MatriculaController() {
        carregarPlanos();
        atualizarLista();
    }

    public void salvar() throws Exception {
        if (dataInicio.get() == null) {
            throw new Exception("Data de início é obrigatória.");
        }
        if (dataVencimento.get() == null) {
            throw new Exception("Data de vencimento é obrigatória.");
        }
        if (dataVencimento.get().isBefore(dataInicio.get())) {
            throw new Exception("Data de vencimento não pode ser anterior à data de início.");
        }
        if (planoSelecionado.get() == null) {
            throw new Exception("Selecione um plano.");
        }

        Matricula m = new Matricula();
        if (matriculaSelecionada != null) {
            m.setId(matriculaSelecionada.getId());
        }
        m.setDataInicio(Date.valueOf(dataInicio.get()));
        m.setDataVencimento(Date.valueOf(dataVencimento.get()));
        m.setStatus(status.get());
        m.setObservacao(observacao.get());
        m.setPlanoId(planoSelecionado.get().getId());
        m.setUsuarioId(Contexto.getUsuarioLogado().getId());

        if (m.getId() == 0) {
            dao.inserir(m);
        } else {
            dao.atualizar(m);
        }

        limparCampos();
        atualizarLista();
    }

    public void deletar() throws Exception {
        if (matriculaSelecionada == null) {
            throw new Exception("Selecione uma matrícula para excluir.");
        }
        dao.deletar(matriculaSelecionada.getId());
        limparCampos();
        atualizarLista();
    }

    public void selecionarMatricula(Matricula m) {
        if (m == null) return;
        matriculaSelecionada = m;
        dataInicio.set(m.getDataInicio().toLocalDate());
        dataVencimento.set(m.getDataVencimento().toLocalDate());
        status.set(m.getStatus());
        observacao.set(m.getObservacao() != null ? m.getObservacao() : "");

        for (Plano p : planos) {
            if (p.getId() == m.getPlanoId()) {
                planoSelecionado.set(p);
                break;
            }
        }
    }

    public void atualizarLista() {
        try {
            matriculas.clear();
            matriculas.addAll(dao.listarPorUsuario(Contexto.getUsuarioLogado().getId()));
        } catch (Exception e) {
            System.out.println("Erro ao carregar matrículas: " + e.getMessage());
        }
    }

    public void carregarPlanos() {
        try {
            planos.clear();
            planos.addAll(planoDAO.listar());
        } catch (Exception e) {
            System.out.println("Erro ao carregar planos: " + e.getMessage());
        }
    }

    public void limparCampos() {
        matriculaSelecionada = null;
        dataInicio.set(LocalDate.now());
        dataVencimento.set(null);
        status.set("ATIVA");
        observacao.set("");
        planoSelecionado.set(null);
    }

    public ObjectProperty<LocalDate> dataInicioProperty() { return dataInicio; }
    public ObjectProperty<LocalDate> dataVencimentoProperty() { return dataVencimento; }
    public StringProperty statusProperty() { return status; }
    public StringProperty observacaoProperty() { return observacao; }
    public ObjectProperty<Plano> planoSelecionadoProperty() { return planoSelecionado; }
    public ObservableList<Matricula> getMatriculas() { return matriculas; }
    public ObservableList<Plano> getPlanos() { return planos; }
}
