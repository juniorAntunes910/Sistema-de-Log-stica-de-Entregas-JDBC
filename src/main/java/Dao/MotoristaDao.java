package Dao;

import Conexao.Conexao;
import Entidades.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoristaDao {
    public void inserirMotorista(Motorista motorista){
        String sql = """
                INSERT INTO motorista
                (nome, cnh, veiculo, cidade_base)
                values
                (?,?,?,?)
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidade_base());
            stmt.executeUpdate();
            System.out.println("Motorista " + motorista.getNome() + " Foi criado com sucesso!");
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
