package edu.curso.Fronteira;

import edu.curso.Contexto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TelaUsuarioView extends VBox {

    public TelaUsuarioView() {
        setSpacing(15);
        setPadding(new Insets(40));
        setAlignment(Pos.TOP_CENTER);

        Label lblTitulo = new Label("Painel do Aluno");
        lblTitulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        Label lblBemVindo = new Label("Bem-vindo, " + Contexto.getUsuarioLogado().getNome());
        lblBemVindo.setStyle("-fx-font-size: 13;");

        Button btnMatriculas = new Button("Minhas Matrículas");
        btnMatriculas.setMaxWidth(250);
        btnMatriculas.setOnAction(e -> Contexto.setTela(new CadMatriculaView()));

        Button btnSair = new Button("Sair");
        btnSair.setMaxWidth(250);
        btnSair.setOnAction(e -> Contexto.sair());

        getChildren().addAll(lblTitulo, lblBemVindo, btnMatriculas, btnSair);
    }
}
