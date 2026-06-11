package edu.curso;

import java.util.ArrayList;
import java.util.List;

import edu.curso.Entidade.Plano;
import edu.curso.Entidade.Usuario;
import edu.curso.Fronteira.LoginView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Contexto {

    private static Stage stage;
    private static Scene cena;
    private static Usuario usuarioLogado;
    private static List<Plano> promocoes;

    public static void iniciar(Stage s) {
        stage = s;
    }

    public static void setTela(Pane tela) {
        if (cena == null) {
            cena = new Scene(tela, 720, 460);

            stage.setScene(cena);
            stage.show();
        } else {

            cena.setRoot(tela);
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario u) {
        usuarioLogado = u;
    }

    public static void sair() {
        // new Conexao().getConnection().close();
        usuarioLogado = null;
        setTela(new LoginView());
    }
}
