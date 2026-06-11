package edu.curso.Controller;

import edu.curso.DAO.UsuarioDAO;
import edu.curso.Entidade.Usuario;

public class CadastroController {

    private UsuarioDAO dao = new UsuarioDAO();

    public void cadastrar(Usuario usuario) throws Exception {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new Exception("Nome é obrigatório.");
        }
        if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
            throw new Exception("CPF é obrigatório.");
        }
        if (usuario.getCpf().replaceAll("[^0-9]", "").length() != 11) {
            throw new Exception("CPF deve ter 11 dígitos.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new Exception("E-mail é obrigatório.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new Exception("E-mail inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new Exception("Senha é obrigatória.");
        }
        if (usuario.getSenha().length() < 4) {
            throw new Exception("Senha deve ter ao menos 4 caracteres.");
        }

        usuario.setPerfil("USUARIO");
        dao.Inserir(usuario);
    }
}
