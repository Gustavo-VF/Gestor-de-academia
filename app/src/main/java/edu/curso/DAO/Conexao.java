package edu.curso.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String url = "jdbc:mariadb://localhost:3306/academia";
    private static String user = "root";
    private static String pass = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}