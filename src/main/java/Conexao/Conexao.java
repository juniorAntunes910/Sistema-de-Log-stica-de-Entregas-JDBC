package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3308/e_commercer?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "mysqlPW";

    public static Connection Conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
