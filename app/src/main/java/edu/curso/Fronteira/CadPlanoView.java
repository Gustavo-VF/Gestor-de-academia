package edu.curso.Fronteira;

import edu.curso.Contexto;
import edu.curso.Controller.PlanoController;
import edu.curso.Entidade.Plano;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CadPlanoView extends VBox {

    private PlanoController controller = new PlanoController();
    private ObservableList<Plano> dados = FXCollections.observableArrayList();
    private Plano planoSelecionado = null;

    private TextField txtNome = new TextField();
    private TextField txtDescricao = new TextField();
    private TextField txtDuracao = new TextField();
    private TextField txtPreco = new TextField();
    private ComboBox<String> cmbModalidade = new ComboBox<>();
    private CheckBox chkStatus = new CheckBox("Plano Ativo");
    private Label lblMensagem = new Label();
    private TableView<Plano> tabela = new TableView<>();

    public CadPlanoView() {
        setSpacing(8);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Cadastro de Planos");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        txtNome.setPromptText("Nome do plano");
        txtNome.setMaxWidth(300);
        txtDescricao.setPromptText("Descrição");
        txtDescricao.setMaxWidth(300);
        txtDuracao.setPromptText("Duração em dias (ex: 30)");
        txtDuracao.setMaxWidth(300);
        txtPreco.setPromptText("Preço (ex: 99.90)");
        txtPreco.setMaxWidth(300);

        cmbModalidade.setItems(FXCollections.observableArrayList(
                "levantamento de peso", "Natação", "corrida", "Pilates", "Yoga", "luta"));
        cmbModalidade.setPromptText("Modalidade");
        cmbModalidade.setMaxWidth(300);
        chkStatus.setSelected(true);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> salvar());

        Button btnNovo = new Button("Novo");
        btnNovo.setOnAction(e -> limpar());

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> excluir());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> Contexto.setTela(new TelaAdminView()));

        HBox botoes = new HBox(8, btnSalvar, btnNovo, btnExcluir, btnVoltar);
        botoes.setAlignment(Pos.CENTER);

        // colunas da tabela
        TableColumn<Plano, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(40);

        TableColumn<Plano, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.setPrefWidth(120);

        TableColumn<Plano, String> colModalidade = new TableColumn<>("Modalidade");
        colModalidade.setCellValueFactory(new PropertyValueFactory<>("modalidade"));
        colModalidade.setPrefWidth(100);

        TableColumn<Plano, Integer> colDuracao = new TableColumn<>("Dias");
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        colDuracao.setPrefWidth(50);

        TableColumn<Plano, Double> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colPreco.setPrefWidth(70);

        TableColumn<Plano, Boolean> colStatus = new TableColumn<>("Ativo");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item ? "Sim" : "Não");
            }
        });
        colStatus.setPrefWidth(50);

        TableColumn<Plano, Void> colAcoes = new TableColumn<>("selecionar");
        colAcoes.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            {
                btnEditar.setOnAction(e -> {
                    Plano p = getTableView().getItems().get(getIndex());
                    planoSelecionado = p;
                    txtNome.setText(p.getNome());
                    txtDescricao.setText(p.getDescricao());
                    txtDuracao.setText(String.valueOf(p.getDuracao()));
                    txtPreco.setText(String.valueOf(p.getPreco()));
                    cmbModalidade.setValue(p.getModalidade());
                    chkStatus.setSelected(p.isStatus());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEditar);
            }
        });
        colAcoes.setPrefWidth(60);

        tabela.getColumns().addAll(colId, colNome, colModalidade, colDuracao, colPreco, colStatus, colAcoes);
        tabela.setItems(dados);
        tabela.setMaxHeight(200);

        getChildren().addAll(
                lblTitulo,
                txtNome, txtDescricao, txtDuracao, txtPreco,
                cmbModalidade, chkStatus,
                lblMensagem,
                botoes,
                new Label("Planos cadastrados:"),
                tabela);

        carregarTabela();
    }

    private void salvar() {
        try {
            Plano p = new Plano();
            if (planoSelecionado != null)
                p.setId(planoSelecionado.getId());
            p.setNome(txtNome.getText());
            p.setDescricao(txtDescricao.getText());
            p.setDuracao(Integer.parseInt(txtDuracao.getText().trim()));
            p.setPreco(Double.parseDouble(txtPreco.getText().trim().replace(",", ".")));
            p.setModalidade(cmbModalidade.getValue());
            p.setStatus(chkStatus.isSelected());
            controller.salvar(p);
            lblMensagem.setTextFill(Color.GREEN);
            lblMensagem.setText("Plano salvo com sucesso!");
            limpar();
            carregarTabela();
        } catch (NumberFormatException e) {
            lblMensagem.setTextFill(Color.RED);
            lblMensagem.setText("Duração e preço devem ser números válidos.");
        } catch (Exception e) {
            lblMensagem.setTextFill(Color.RED);
            lblMensagem.setText(e.getMessage());
        }
    }

    private void excluir() {
        if (planoSelecionado == null) {
            lblMensagem.setTextFill(Color.RED);
            lblMensagem.setText("Clique em Editar para selecionar um plano.");
            return;
        }
        try {
            controller.deletar(planoSelecionado.getId());
            lblMensagem.setTextFill(Color.GREEN);
            lblMensagem.setText("Plano excluído com sucesso!");
            limpar();
            carregarTabela();
        } catch (Exception e) {
            lblMensagem.setTextFill(Color.RED);
            lblMensagem.setText("Erro ao excluir: " + e.getMessage());
        }
    }

    private void carregarTabela() {
        try {
            dados.setAll(controller.listar());
        } catch (Exception e) {
            lblMensagem.setTextFill(Color.RED);
            lblMensagem.setText("Erro ao carregar: " + e.getMessage());
        }
    }

    private void limpar() {
        planoSelecionado = null;
        txtNome.clear();
        txtDescricao.clear();
        txtDuracao.clear();
        txtPreco.clear();
        cmbModalidade.setValue(null);
        chkStatus.setSelected(true);
        lblMensagem.setText("");
    }
}