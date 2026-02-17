package Dao;

import Conexao.Conexao;
import Entidades.Motorista;

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
}
