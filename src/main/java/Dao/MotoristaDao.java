package Dao;

import Conexao.Conexao;
import Entidades.Motorista;
import Entidades.MotoristaTotalEntregas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public ArrayList<Motorista> mostrarTodosMotoristas(){
        String sql = """
                SELECT id,nome,cnh,veiculo,cidade_base
                FROM motorista
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Motorista> listaMotorista = new ArrayList<>();
            while (rs.next()){
                listaMotorista.add(new Motorista(rs.getInt("id"), rs.getString("nome"),rs.getString("cnh"),
                        rs.getString("veiculo"), rs.getString("cidade_base")));
            }
            return listaMotorista;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<MotoristaTotalEntregas> contagemMotorista(){
        String sql = """
                SELECT m.nome, COUNT(e.id) AS total_entregas
                FROM motorista m
                LEFT JOIN entrega e ON m.id = e.motorista_id
                GROUP BY m.id, m.nome;
                """;
        try (Connection conn = Conexao.Conectar()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<MotoristaTotalEntregas> listaMotora = new ArrayList<>();
            while(rs.next()){
                listaMotora.add(new MotoristaTotalEntregas(rs.getString("nome"), rs.getInt("total_entregas")));
            }
            return listaMotora;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
