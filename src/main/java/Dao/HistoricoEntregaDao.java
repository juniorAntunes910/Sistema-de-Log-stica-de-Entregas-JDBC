package Dao;

import Conexao.Conexao;
import Entidades.HistoricoEntrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricoEntregaDao {
    public void inserirHistoricoEntrega(HistoricoEntrega historicoEntrega){
        String sql = """
                INSERT INTO historico_entrega
                (entrega_id, data_evento, descricao)
                VALUES 
                (?,?,?)
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, historicoEntrega.getEntrega_id());
            stmt.setDate(2, java.sql.Date.valueOf(historicoEntrega.getDataEvento()));
            stmt.setString(3, historicoEntrega.getDescricao());
            stmt.executeUpdate();
            System.out.println("Historico inserido com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
