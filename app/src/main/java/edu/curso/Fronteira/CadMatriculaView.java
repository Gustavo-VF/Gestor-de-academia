package edu.curso.Fronteira;

import edu.curso.Contexto;
import edu.curso.Controller.MatriculaController;
import edu.curso.Entidade.Matricula;
import edu.curso.Entidade.Plano;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.beans.binding.Bindings;

import java.sql.Date;

public class CadMatriculaView extends VBox {

    private MatriculaController mc = new MatriculaController();

    private DatePicker dpDataInicio = new DatePicker();
    private DatePicker dpDataVencimento = new DatePicker();
    private ComboBox<String> cmbStatus = new ComboBox<>();
    private TextField txtObservacao = new TextField();
    private ComboBox<Plano> cmbPlano = new ComboBox<>();
    private Label mensagem = new Label("");
    private TableView<Matricula> tabela = new TableView<>();

    public CadMatriculaView() {
        setAlignment(Pos.CENTER);
        setSpacing(12);
        setPadding(new Insets(40));

        Label lblTitulo = new Label("Minhas Matrículas");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        dpDataInicio.setMaxWidth(300);
        dpDataVencimento.setMaxWidth(300);
        dpDataVencimento.setPromptText("Data de Vencimento");

        cmbStatus.setItems(FXCollections.observableArrayList("ATIVA", "CANCELADA", "VENCIDA"));
        cmbStatus.setMaxWidth(300);

        txtObservacao.setPromptText("Observação (opcional)");
        txtObservacao.setMaxWidth(300);

        cmbPlano.setPromptText("Selecione um plano");
        cmbPlano.setMaxWidth(300);
        cmbPlano.setItems(mc.getPlanos());
        cmbPlano.setCellFactory(lv -> new ListCell<Plano>() {
            @Override
            protected void updateItem(Plano item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });
        cmbPlano.setButtonCell(new ListCell<Plano>() {
            @Override
            protected void updateItem(Plano item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNome());
            }
        });

        Bindings.bindBidirectional(dpDataInicio.valueProperty(), mc.dataInicioProperty());
        Bindings.bindBidirectional(dpDataVencimento.valueProperty(), mc.dataVencimentoProperty());
        Bindings.bindBidirectional(txtObservacao.textProperty(), mc.observacaoProperty());
        Bindings.bindBidirectional(cmbStatus.valueProperty(), mc.statusProperty());
        Bindings.bindBidirectional(cmbPlano.valueProperty(), mc.planoSelecionadoProperty());

        HBox linha1 = new HBox(8, dpDataInicio, dpDataVencimento, cmbStatus);
        linha1.setAlignment(Pos.CENTER);

        HBox linha2 = new HBox(8, txtObservacao, cmbPlano);
        linha2.setAlignment(Pos.CENTER);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            try {
                mc.salvar();
                mensagem.setText("Matrícula salva com sucesso!");
            } catch (Exception ex) {
                mensagem.setText(ex.getMessage());
            }
        });

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            try {
                mc.deletar();
                mensagem.setText("Matrícula excluída com sucesso!");
            } catch (Exception ex) {
                mensagem.setText(ex.getMessage());
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> Contexto.setTela(new TelaUsuarioView()));

        HBox botoes = new HBox(8, btnSalvar, btnExcluir, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        TableColumn<Matricula, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Matricula, Date> colInicio = new TableColumn<>("Início");
        colInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));

        TableColumn<Matricula, Date> colVencimento = new TableColumn<>("Vencimento");
        colVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));

        TableColumn<Matricula, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Matricula, String> colObservacao = new TableColumn<>("Observação");
        colObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        TableColumn<Matricula, Integer> colPlano = new TableColumn<>("Plano ID");
        colPlano.setCellValueFactory(new PropertyValueFactory<>("planoId"));

        TableColumn<Matricula, Void> colAcoes = new TableColumn<>("Ações");
        colAcoes.setCellFactory(col -> new TableCell<Matricula, Void>() {
            private final Button btnEditar = new Button("Editar");
            {
                btnEditar.setOnAction(e -> {
                    Matricula m = getTableView().getItems().get(getIndex());
                    mc.selecionarMatricula(m);
                    mensagem.setText("");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEditar);
            }
        });

        tabela.getColumns().addAll(colId, colInicio, colVencimento, colStatus, colObservacao, colPlano, colAcoes);
        tabela.setItems(mc.getMatriculas());

        getChildren().addAll(
                lblTitulo,
                linha1,
                linha2,
                mensagem,
                botoes,
                new Label("Matrículas:"),
                tabela);
    }
}
