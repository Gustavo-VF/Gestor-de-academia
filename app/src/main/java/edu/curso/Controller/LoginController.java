package edu.curso.Controller;

import edu.curso.DAO.UsuarioDAO;
import edu.curso.Contexto;
import edu.curso.Entidade.Usuario;
import edu.curso.Fronteira.TelaAdminView;
import edu.curso.Fronteira.TelaUsuarioView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginController {
    private StringProperty email = new SimpleStringProperty();
    private StringProperty senha = new SimpleStringProperty();
    private StringProperty mensagem = new SimpleStringProperty();

    public void Logar() {
        try {
            if (email.get() == null || email.get().isEmpty()) {
                mensagem.set("Preencha o e-mail.");
                return;
            }
            if (senha.get() == null || senha.get().isEmpty()) {
                mensagem.set("Preencha a senha.");
                return;
            }

            var listaUsers = UsuarioDAO.getUsuarios();
            for (Usuario usuario : listaUsers) {
                if (usuario.getEmail().equals(email.get()) &&
                        usuario.getSenha().equals(senha.get())) {
                    Contexto.setUsuarioLogado(usuario);
                    if (usuario.getPerfil().equals("ADM")) {
                        Contexto.setTela(new TelaAdminView()); // ✅
                    } else {
                        Contexto.setTela(new TelaUsuarioView()); // ✅
                    }
                    return;
                }
            }
            mensagem.set("E-mail ou senha inválidos.");
        } catch (Exception e) {
            mensagem.set("Erro ao acessar usuários.");
            e.printStackTrace();
        }
    }

    public StringProperty getEmail() {
        return email;
    }

    public StringProperty getSenha() {
        return senha;
    }

    public StringProperty getMensagem() {
        return mensagem;
    }
}