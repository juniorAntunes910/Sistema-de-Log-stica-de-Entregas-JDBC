package Dao;

import Conexao.Conexao;
import Entidades.Cliente;
import Entidades.ClienteMaiorVolume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDao {
    public void inserirCliente(Cliente cliente){
        String sql = """
                INSERT INTO cliente
                (nome, cpf_cnpj, endereco, cidade, estado)
                VALUES
                (?,?,?,?,?)
                """;
        try(Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();
            System.out.println("Cliente " + cliente.getNome() + " Criado com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<Cliente> mostrarTodosClientes(){
        String sql = """
                SELECT id, nome, cpf_cnpj, endereco, cidade, estado
                FROM cliente
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            while(rs.next()){
                listaClientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf_cnpj"),
                        rs.getString("endereco"), rs.getString("cidade"), rs.getString("estado")));
            }
            return listaClientes;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ClienteMaiorVolume> listarTodosClientesMaiorEntrega(){
        String sql = """
                SELECT
                c.id,
                c.nome,
                SUM(p.volume_m3) AS total_volume
                FROM cliente c
                JOIN pedido p ON p.cliente_id = c.id
                WHERE p.status = "ENTREGUE"
                GROUP BY c.id, c.nome
                ORDER BY total_volume DESC;
                """;
        try(Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<ClienteMaiorVolume> listaClienteM = new ArrayList<>();
            while(rs.next()){
                listaClienteM.add(new ClienteMaiorVolume(rs.getInt("id"), rs.getString("nome"), rs.getDouble("total_volume")));
            }
            return listaClienteM;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
