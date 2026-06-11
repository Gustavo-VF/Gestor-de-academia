package edu.curso.Controller;

import java.util.List;

import edu.curso.DAO.UsuarioDAO;
import edu.curso.Entidade.Usuario;

public class UsuarioController {

    public void salvar(Usuario usuario) throws Exception {
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
        if (usuario.getPerfil() == null || usuario.getPerfil().trim().isEmpty()) {
            throw new Exception("Perfil é obrigatório.");
        }

        if (usuario.getId() == 0) {
            UsuarioDAO.Inserir(usuario);
        } else {
            UsuarioDAO.Atualizar(usuario);
        }
    }

    public void deletar(Usuario usuario) throws Exception {
        UsuarioDAO.Remover(usuario);
    }

    public List<Usuario> listar() throws Exception {
        return UsuarioDAO.getUsuarios();
    }

}
