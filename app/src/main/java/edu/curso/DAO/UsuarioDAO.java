package edu.curso.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import edu.curso.Entidade.Usuario;

public class UsuarioDAO {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void Inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario( nome, cpf, email, senha, perfil) VALUES ( ?, ?, ?, ?,? )";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getPerfil());

            stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao criar Usuario no banco: " + e.getMessage());
        }

    }

    public static void Atualizar(Usuario usuario) {

        String sql = "UPDATE usuario SET nome = ?, cpf = ? , email = ?, senha = ? , perfil = ? WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getPerfil());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao atualizar usuario no banco: " + e.getMessage());
        }

    }

    public static void Remover(Usuario usuario) {

        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("erro ao REMOVER usuario no banco: " + e.getMessage());
        }

    }

    public static Usuario Pesquisar(Usuario usuario) {
        String sql = "SELECT * FROM usuario where id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());

            ResultSet pesquisado = stmt.executeQuery();

            if (pesquisado.next()) {
                System.out.println(pesquisado.getString("nome"));

                Usuario pUsuario = new Usuario();

                pUsuario.setId(pesquisado.getInt("id"));

                pUsuario.setNome(pesquisado.getString("nome"));

                pUsuario.setCpf(pesquisado.getString("cpf"));

                pUsuario.setEmail(pesquisado.getString("email"));

                pUsuario.setSenha(pesquisado.getString("senha"));

                pUsuario.setPerfil(pesquisado.getString("perfil"));

                return pUsuario;
            }

            pesquisado.close();
            stmt.close();
            con.close();

            return null;

        } catch (Exception e) {
            System.out.println("erro ao PESQUISAR usuario no banco" + e.getMessage());
        }

        return null;
    }

    public static List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement tsmt = con.prepareStatement(sql);

            ResultSet usuarioList = tsmt.executeQuery();

            usuarios.clear();

            while (usuarioList.next()) {
                Usuario pUsuario = new Usuario();

                pUsuario.setId(usuarioList.getInt("id"));

                pUsuario.setNome(usuarioList.getString("nome"));

                pUsuario.setCpf(usuarioList.getString("cpf"));

                pUsuario.setEmail(usuarioList.getString("email"));

                pUsuario.setSenha(usuarioList.getString("senha"));

                pUsuario.setPerfil(usuarioList.getString("perfil"));

                usuarios.add(pUsuario);
            }

            usuarioList.close();
            tsmt.close();
            con.close();

            return usuarios;

        } catch (Exception e) {
            System.out.println("erro ao carregar lista de usuario do banco" + e.getMessage());

        }

        return usuarios;
    }

}
