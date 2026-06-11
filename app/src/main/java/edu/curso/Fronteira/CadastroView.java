package edu.curso.Fronteira;

import edu.curso.Contexto;
import edu.curso.Controller.CadastroController;
import edu.curso.Entidade.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CadastroView extends VBox {
    CadastroController cc = new CadastroController();

    public CadastroView() {
        setAlignment(Pos.CENTER);
        setSpacing(12);
        setPadding(new Insets(40));

        Label lblTitulo = new Label("Nova Conta");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        TextField txtNome = new TextField();
        txtNome.setPromptText("Nome");
        txtNome.setMaxWidth(300);

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("E-mail");
        txtEmail.setMaxWidth(300);

        TextField txtCPF = new TextField();
        txtCPF.setPromptText("CPF");
        txtCPF.setMaxWidth(300);

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        txtSenha.setMaxWidth(300);

        PasswordField txtConfirmarSenha = new PasswordField();
        txtConfirmarSenha.setPromptText("Confirmar Senha");
        txtConfirmarSenha.setMaxWidth(300);

        Label mensagem = new Label("");

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setMaxWidth(300);

        Button btnVoltar = new Button("Cancelar");
        btnVoltar.setMaxWidth(300);

        btnCadastrar.setOnAction(event -> {
            try {
                if (!txtSenha.getText().equals(txtConfirmarSenha.getText())) {
                    mensagem.setText("Senhas não conferem.");
                    return;
                }
                Usuario u = new Usuario();
                u.setNome(txtNome.getText());
                u.setEmail(txtEmail.getText());
                u.setCpf(txtCPF.getText());
                u.setSenha(txtSenha.getText());
                cc.cadastrar(u);
                mensagem.setText("Conta criada com sucesso!");
                Contexto.setTela(new LoginView());
            } catch (Exception e) {
                mensagem.setText(e.getMessage());
            }
        });

        btnVoltar.setOnAction(e -> Contexto.setTela(new LoginView()));

        getChildren().addAll(lblTitulo, txtNome, txtEmail, txtCPF,
                txtSenha, txtConfirmarSenha, mensagem, btnCadastrar, btnVoltar);
    }
}