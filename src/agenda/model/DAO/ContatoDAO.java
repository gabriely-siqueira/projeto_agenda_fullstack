package agenda.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agenda.model.Contato;

public class ContatoDAO {
    public boolean cadastrarContatoDAO(Contato contato){
        //Insert
        String sql = "INSERT INTO contato (nome, email, fone) VALUES (?, ?, ?)";
        // Alteração: movido o retorno true para depois de todas as operações
        ConexaoDB conexaoDB = new ConexaoDB();
        try (Connection connection = conexaoDB.dbConnection(); // Java 7 try-with-resources para fechar automaticamente a conexão
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setString(3, contato.getFone());
            
            // Executa a operação de inserção
            int rowsAffected = statement.executeUpdate();
            
            // Retorna true se pelo menos uma linha foi afetada
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

    public ResultSet buscarContatoDAO(int id) {
        ResultSet resultadoBusca = null;
        String sql = "";

        try {

            if (id == 0) {
                sql = "SELECT * FROM contato";
            } else {
                sql = "SELECT * FROM contato WHERE id = ?";
            }

            ConexaoDB conexaoDB = new ConexaoDB();
            Connection conn = conexaoDB.dbConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            resultadoBusca = statement.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return resultadoBusca;
    }

}
