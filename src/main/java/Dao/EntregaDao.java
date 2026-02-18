package Dao;

import Conexao.Conexao;
import Entidades.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntregaDao {
    public void inserirEntrega(Entrega entrega){
        String sql = """
                INSERT INTO entrega 
                (pedido_id, motorista_id, data_saida,data_entrega, status)
                VALUES(?,?,?,?,?)
                """;
        try(Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, entrega.getPedidoId());
            stmt.setInt(2, entrega.getMotoristaId());
            stmt.setDate(3, java.sql.Date.valueOf(entrega.getDataSaida()));
            stmt.setDate(4, java.sql.Date.valueOf(entrega.getDataEntrega()));
            stmt.setString(5, entrega.getStatus());
            stmt.executeUpdate();
            System.out.println("Entrega inserida com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<Entrega> mostrarTodasEntregas(){
        String sql = """
                SELECT id, pedido_id, motorista_id, data_saida, data_entrega, status
                FROM entrega
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Entrega> listaEntrega = new ArrayList<>();
            while(rs.next()){
                listaEntrega.add(new Entrega(rs.getInt("id"), rs.getInt("pedido_id"),
                        rs.getInt("motorista_id"), rs.getDate("data_saida").toLocalDate(), rs.getDate("data_entrega").toLocalDate(),
                        rs.getString("status")));
            }
            return  listaEntrega;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
