package edu.curso.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.curso.Entidade.Matricula;

public class MatriculaDAO {

    public void inserir(Matricula matricula) throws Exception {
        String sql = "INSERT INTO matricula (data_inicio, data_vencimento, status, observacao, id_plano, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, matricula.getDataInicio());
            ps.setDate(2, matricula.getDataVencimento());
            ps.setString(3, matricula.getStatus());
            ps.setString(4, matricula.getObservacao());
            ps.setInt(5, matricula.getPlanoId());
            ps.setInt(6, matricula.getUsuarioId());
            ps.executeUpdate();
        }
    }

    public void atualizar(Matricula matricula) throws Exception {
        String sql = "UPDATE matricula SET data_inicio=?, data_vencimento=?, status=?, observacao=?, id_plano=?, id_usuario=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, matricula.getDataInicio());
            ps.setDate(2, matricula.getDataVencimento());
            ps.setString(3, matricula.getStatus());
            ps.setString(4, matricula.getObservacao());
            ps.setInt(5, matricula.getPlanoId());
            ps.setInt(6, matricula.getUsuarioId());
            ps.setInt(7, matricula.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM matricula WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Matricula> listar() throws Exception {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula";
        try (Connection conn = Conexao.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Matricula m = new Matricula();
                m.setId(rs.getInt("id"));
                m.setDataInicio(rs.getDate("data_inicio"));
                m.setDataVencimento(rs.getDate("data_vencimento"));
                m.setStatus(rs.getString("status"));
                m.setObservacao(rs.getString("observacao"));
                m.setPlanoId(rs.getInt("id_plano"));
                m.setUsuarioId(rs.getInt("id_usuario"));
                lista.add(m);
            }
        }
        return lista;
    }

    public List<Matricula> listarPorUsuario(int usuarioId) throws Exception {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE id_usuario=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Matricula m = new Matricula();
                    m.setId(rs.getInt("id"));
                    m.setDataInicio(rs.getDate("data_inicio"));
                    m.setDataVencimento(rs.getDate("data_vencimento"));
                    m.setStatus(rs.getString("status"));
                    m.setObservacao(rs.getString("observacao"));
                    m.setPlanoId(rs.getInt("id_plano"));
                    m.setUsuarioId(rs.getInt("id_usuario"));
                    lista.add(m);
                }
            }
        }
        return lista;
    }

    public Matricula pesquisar(int id) throws Exception {
        String sql = "SELECT * FROM matricula WHERE id=?";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Matricula m = new Matricula();
                    m.setId(rs.getInt("id"));
                    m.setDataInicio(rs.getDate("data_inicio"));
                    m.setDataVencimento(rs.getDate("data_vencimento"));
                    m.setStatus(rs.getString("status"));
                    m.setObservacao(rs.getString("observacao"));
                    m.setPlanoId(rs.getInt("id_plano"));
                    m.setUsuarioId(rs.getInt("id_usuario"));
                    return m;
                }
            }
        }
        return null;
    }
}