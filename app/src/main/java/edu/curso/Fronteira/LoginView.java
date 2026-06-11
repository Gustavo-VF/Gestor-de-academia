package edu.curso.Fronteira;

import edu.curso.Contexto;
import edu.curso.Controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    LoginController lc = new LoginController();

    public LoginView() {
        setAlignment(Pos.CENTER);
        setSpacing(12);
        setPadding(new Insets(40));

        Label lblTitulo = new Label("Academia - Login");
        lblTitulo.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("E-mail");
        txtEmail.setMaxWidth(300);

        PasswordField txtSenha = new PasswordField();
        txtSenha.setPromptText("Senha");
        txtSenha.setMaxWidth(300);

        Label mensagem = new Label("");

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setDefaultButton(true);
        btnEntrar.setMaxWidth(300);

        Hyperlink lnkCadastro = new Hyperlink("Criar conta");

        txtEmail.textProperty().bindBidirectional(lc.getEmail());
        txtSenha.textProperty().bindBidirectional(lc.getSenha());
        mensagem.textProperty().bind(lc.getMensagem());

        btnEntrar.setOnAction(event -> lc.Logar());
        lnkCadastro.setOnAction(event -> Contexto.setTela(new CadastroView()));

        getChildren().addAll(lblTitulo, txtEmail, txtSenha, mensagem, btnEntrar, lnkCadastro);
    }
}