package edu.curso.DAO.CriaBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import edu.curso.DAO.Conexao;

public class CriaBanco {

    public static void criarBanco() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/";
        String user = "root";
        String pass = "root";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
                Statement stm = conn.createStatement()) {
            stm.executeUpdate("CREATE DATABASE IF NOT EXISTS academia");
        }
    }

    public static void criarTabelas() throws SQLException {
        try (Connection conn = Conexao.getConnection();
                Statement stm = conn.createStatement()) {

            stm.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS usuario (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            nome VARCHAR(100) NOT NULL,
                            cpf VARCHAR(14) NOT NULL UNIQUE,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            senha VARCHAR(100) NOT NULL,
                            perfil VARCHAR(10) NOT NULL
                        ) AUTO_INCREMENT = 100
                    """);

            stm.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS plano (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            nome VARCHAR(100) NOT NULL,
                            descricao TEXT,
                            duracao INT NOT NULL,
                            preco DECIMAL(10,2) NOT NULL,
                            modalidade VARCHAR(50) NOT NULL,
                            status BOOLEAN NOT NULL DEFAULT TRUE
                        ) AUTO_INCREMENT = 100
                    """);

            stm.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS matricula (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            data_inicio DATE NOT NULL,
                            data_vencimento DATE NOT NULL,
                            status VARCHAR(20) NOT NULL DEFAULT 'ATIVA',
                            observacao TEXT,
                            id_plano INT NOT NULL,
                            id_usuario INT NOT NULL,
                            FOREIGN KEY (id_plano) REFERENCES plano(id),
                            FOREIGN KEY (id_usuario) REFERENCES usuario(id)
                        ) AUTO_INCREMENT = 100
                    """);

            stm.executeUpdate("""
                        INSERT INTO usuario (nome, cpf, email, senha, perfil)
                        SELECT 'USER_ADMIN', '00000000000', 'admin@academia.com', 'admin123', 'ADM'
                        WHERE NOT EXISTS (
                            SELECT 1 FROM usuario WHERE email = 'admin@academia.com'
                        )
                    """);

            stm.executeUpdate("""
                        INSERT INTO usuario (nome, cpf, email, senha, perfil)
                        SELECT 'USER', '10101010101', 'user@email.com', 'senha123', 'USUARIO'
                        WHERE NOT EXISTS (
                            SELECT 1 FROM usuario WHERE email = 'user@email.com'
                        )
                    """);
        }
    }
}