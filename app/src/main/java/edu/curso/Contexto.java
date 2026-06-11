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

    public static List<Plano> getPromocoes() throws Exception {
        promocoes.clear();

        List<Plano> promocoesBD = new ArrayList<>();
        for (Plano p : promocoes) {
            promocoesBD.add(p);
        }
        return promocoesBD;
    }

    public static List<String> getNomePromocoes() throws Exception {

        List<String> promocoesBD = new ArrayList<>();
        for (Plano p : promocoes) {
            promocoesBD.add(p.getNome());
        }
        return promocoesBD;
    }

    public static void setPromocoes(List<Plano> promocoes) {
        Contexto.promocoes = promocoes;
    }

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
