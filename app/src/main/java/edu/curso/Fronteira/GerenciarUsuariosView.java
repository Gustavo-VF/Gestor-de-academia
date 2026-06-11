package edu.curso.Fronteira;

import edu.curso.Contexto;
import edu.curso.Controller.UsuarioController;
import edu.curso.Entidade.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GerenciarUsuariosView extends VBox {

    private UsuarioController controller = new UsuarioController();
    private ObservableList<Usuario> dados = FXCollections.observableArrayList();
    private Usuario usuarioSelecionado = null;

    private TextField txtNome = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtSenha = new TextField();
    private ComboBox<String> cmbPerfil = new ComboBox<>();
    private Label lblMensagem = new Label();
    private TableView<Usuario> tabela = new TableView<>();

    public GerenciarUsuariosView() {
        setSpacing(8);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Gerenciar Usuários");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        txtNome.setPromptText("Nome completo");
        txtNome.setMaxWidth(300);

        txtCpf.setPromptText("CPF (somente números)");
        txtCpf.setMaxWidth(300);

        txtEmail.setPromptText("E-mail");
        txtEmail.setMaxWidth(300);

        txtSenha.setPromptText("Senha (mín. 4 caracteres)");
        txtSenha.setMaxWidth(300);

        cmbPerfil.setItems(FXCollections.observableArrayList("USUARIO", "ADM"));
        cmbPerfil.setPromptText("Perfil");
        cmbPerfil.setMaxWidth(300);

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

        TableColumn<Usuario, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(40);

        TableColumn<Usuario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNome.setPrefWidth(150);

        TableColumn<Usuario, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colCpf.setPrefWidth(110);

        TableColumn<Usuario, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(160);

        TableColumn<Usuario, String> colPerfil = new TableColumn<>("Perfil");
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colPerfil.setPrefWidth(70);

        TableColumn<Usuario, Void> colAcoes = new TableColumn<>("Ações");
        colAcoes.setCellFactory(col -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            {
                btnEditar.setOnAction(e -> {
                    Usuario u = getTableView().getItems().get(getIndex());
                    usuarioSelecionado = u;
                    txtNome.setText(u.getNome());
                    txtCpf.setText(u.getCpf());
                    txtEmail.setText(u.getEmail());
                    txtSenha.setText(u.getSenha());
                    cmbPerfil.setValue(u.getPerfil());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnEditar);
            }
        });
        colAcoes.setPrefWidth(60);

        tabela.getColumns().addAll(colId, colNome, colCpf, colEmail, colPerfil, colAcoes);
        tabela.setItems(dados);
        tabela.setMaxHeight(200);

        getChildren().addAll(
                lblTitulo,
                txtNome, txtCpf, txtEmail, txtSenha,
                cmbPerfil,
                lblMensagem,
                botoes,
                new Label("Usuários cadastrados:"),
                tabela);

        carregarTabela();
    }

    private void salvar() {
        try {
            Usuario u = new Usuario();
            if (usuarioSelecionado != null)
                u.setId(usuarioSelecionado.getId());
            u.setNome(txtNome.getText());
            u.setCpf(txtCpf.getText());
            u.setEmail(txtEmail.getText());
            u.setSenha(txtSenha.getText());
            u.setPerfil(cmbPerfil.getValue());
            controller.salvar(u);
            lblMensagem.setText("Usuário salvo com sucesso!");
            limpar();
            carregarTabela();
        } catch (Exception e) {
            lblMensagem.setText(e.getMessage());
        }
    }

    private void excluir() {
        if (usuarioSelecionado == null) {
            lblMensagem.setText("Clique em Editar para selecionar um usuário.");
            return;
        }
        try {
            controller.deletar(usuarioSelecionado);
            lblMensagem.setText("Usuário excluído com sucesso!");
            limpar();
            carregarTabela();
        } catch (Exception e) {
            lblMensagem.setText("Erro ao excluir: " + e.getMessage());
        }
    }

    private void carregarTabela() {
        try {
            dados.setAll(controller.listar());
        } catch (Exception e) {
            lblMensagem.setText("Erro ao carregar: " + e.getMessage());
        }
    }

    private void limpar() {
        usuarioSelecionado = null;
        txtNome.clear();
        txtCpf.clear();
        txtEmail.clear();
        txtSenha.clear();
        cmbPerfil.setValue(null);
        lblMensagem.setText("");
    }
}
