package Dao;

import Conexao.Conexao;
import Entidades.ClienteMaiorVolume;
import Entidades.Pedido;
import Entidades.QuantidadeEstado;

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

    public ArrayList<QuantidadeEstado> mostrarEstadoPedidosPendente(){
        String sql = """
                SELECT\s
                	c.estado,
                    COUNT(p.id) AS quantidade_estado
                    FROM pedido p\s
                    JOIN cliente c ON p.cliente_id = c.id
                    WHERE p.status = 'PENDENTE'
                    GROUP BY c.estado
                    ORDER BY quantidade_estado DESC;
                """;
        try(Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<QuantidadeEstado> listaQuantidade = new ArrayList<>();
            while (rs.next()){
                listaQuantidade.add(new QuantidadeEstado(rs.getString("estado"), rs.getInt("quantidade_estado")));
            }
            return listaQuantidade;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Pedido> buscarPorCpfCnpj(String cpf_cnpj){
        String sql = """
                SELECT
                	p.id,
                	p.cliente_id,
                	p.data_pedido,
                    p.volume_m3,
                    p.peso_kg,
                    p.status
                    FROM pedido p
                    JOIN cliente c ON p.cliente_id = c.id
                    WHERE c.cpf_cnpj = ?;
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf_cnpj);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Pedido> listaPedido = new ArrayList<>();
            while (rs.next()){
                listaPedido.add(new Pedido(rs.getInt("id"), rs.getInt("cliente_id"), rs.getDate("data_pedido").toLocalDate(),
                        rs.getDouble("volume_m3"), rs.getDouble("peso_kg"), rs.getString("status")));
            }
            return listaPedido;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
