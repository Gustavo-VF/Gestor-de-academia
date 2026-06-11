package edu.curso.Fronteira;

import edu.curso.Contexto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TelaAdminView extends VBox {

    public TelaAdminView() {
        setSpacing(15);
        setPadding(new Insets(40));
        setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Painel do Administrador");
        lblTitulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label lblBemVindo = new Label("Bem-vindo, " + Contexto.getUsuarioLogado().getNome());
        lblBemVindo.setStyle("-fx-font-size: 13;");

        Button btnPlanos = new Button("Gerenciar Planos");
        btnPlanos.setMaxWidth(250);
        btnPlanos.setOnAction(e -> Contexto.setTela(new CadPlanoView()));

        Button btnUsuarios = new Button("Gerenciar Usuários");
        btnUsuarios.setMaxWidth(250);
        btnUsuarios.setOnAction(e -> Contexto.setTela(new GerenciarUsuariosView()));

        Button btnSair = new Button("Sair");
        btnSair.setMaxWidth(250);
        btnSair.setOnAction(e -> Contexto.sair());

        getChildren().addAll(lblTitulo, lblBemVindo, btnPlanos, btnUsuarios, btnSair);
    }
}