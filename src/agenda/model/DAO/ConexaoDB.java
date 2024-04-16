package agenda.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDB {
    public Connection dbConnection() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL de conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3306/agendadb?user=root&password=Mysqlp@ssword2024";

            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException error) {
            // Trata exceções de driver não encontrado e erros de SQL
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + error.getMessage());
        }
        return conn;
    }
}
