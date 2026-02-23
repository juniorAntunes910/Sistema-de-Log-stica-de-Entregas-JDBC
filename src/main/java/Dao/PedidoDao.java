package Dao;

import Conexao.Conexao;
import Entidades.ClienteMaiorVolume;
import Entidades.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDao {
    public void inserirPedido(Pedido pedido){
        String sql = """
                INSERT INTO pedido 
                (cliente_id,data_pedido,volume_m3,peso_kg,status)
                VALUES
                (?,?,?,?,?)
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedido.getCliente_id());
            stmt.setDate(2, java.sql.Date.valueOf(pedido.getData_pedido()));
            stmt.setDouble(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<Pedido> mostrarTodosPedidos(){
        String sql = """
                SELECT id,cliente_id,data_pedido,volume_m3,peso_kg,status
                from pedido
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Pedido> listaPedidos = new ArrayList<>();
            while (rs.next()){
                listaPedidos.add(new Pedido(rs.getInt("id"), rs.getInt("cliente_id"), rs.getDate("data_pedido").toLocalDate(),
                        rs.getDouble("volume_m3"), rs.getDouble("peso_kg"), rs.getString("status")));
            }
            return listaPedidos;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
