package Dao;

import Conexao.Conexao;
import Entidades.ClienteMaiorVolume;
import Entidades.Entrega;
import Entidades.EntregaClienteMotorista;

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
    public void atualizarStatusEntrega(String status, int id){
        String sql = """
                UPDATE entrega
                SET status = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Status atualizado com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<EntregaClienteMotorista> listarTodasEntregasClienteMotorista() {
        // SQL que faz o caminho: Entrega -> Motorista E Entrega -> Pedido -> Cliente
        String sql = """
            SELECT 
                e.id, 
                m.nome AS nome_motorista, 
                c.nome AS nome_cliente, 
                e.status
            FROM entrega e
            INNER JOIN motorista m ON e.motorista_id = m.id
            INNER JOIN pedido p ON e.pedido_id = p.id
            INNER JOIN cliente c ON p.cliente_id = c.id
            """;

        ArrayList<EntregaClienteMotorista> lista = new ArrayList<>();

        try (Connection conn = Conexao.Conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new EntregaClienteMotorista(
                        rs.getInt("id"),
                        rs.getString("nome_motorista"),
                        rs.getString("nome_cliente"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro no relatório de entregas: " + e.getMessage());
        }
        return lista;
    }

}
