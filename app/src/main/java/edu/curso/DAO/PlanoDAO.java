package edu.curso.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.curso.Entidade.Plano;

public class PlanoDAO {

    public void inserir(Plano plano) throws Exception {
        String sql = "INSERT INTO plano (nome, descricao, duracao, preco, modalidade, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plano.getNome());
            ps.setString(2, plano.getDescricao());
            ps.setInt(3, plano.getDuracao());
            ps.setDouble(4, plano.getPreco());
            ps.setString(5, plano.getModalidade());
            ps.setBoolean(6, plano.isStatus());
            ps.executeUpdate();
        }
    }

    public void atualizar(Plano plano) throws Exception {
        String sql = "UPDATE plano SET nome=?, descricao=?, duracao=?, preco=?, modalidade=?, status=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plano.getNome());
            ps.setString(2, plano.getDescricao());
            ps.setInt(3, plano.getDuracao());
            ps.setDouble(4, plano.getPreco());
            ps.setString(5, plano.getModalidade());
            ps.setBoolean(6, plano.isStatus());
            ps.setInt(7, plano.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM plano WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Plano> listar() throws Exception {
        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT * FROM plano";
        try (Connection conn = Conexao.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setDuracao(rs.getInt("duracao"));
                p.setPreco(rs.getDouble("preco"));
                p.setModalidade(rs.getString("modalidade"));
                p.setStatus(rs.getBoolean("status"));
                lista.add(p);
            }
        }
        return lista;
    }

    public Plano pesquisar(int id) throws Exception {
        String sql = "SELECT * FROM plano WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Plano p = new Plano();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setDuracao(rs.getInt("duracao"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setModalidade(rs.getString("modalidade"));
                    p.setStatus(rs.getBoolean("status"));
                    return p;
                }
            }
        }
        return null;
    }
}