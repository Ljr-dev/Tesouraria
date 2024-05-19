package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Banco;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JAVA
 */
public class BancoDAO {

    private Connection con;

    public BancoDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarLanc(Banco obj) {

        try {
            // 1 criar o comando sql
            String query = "insert into tbl_banco(banco)"
                    + "values(?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setString(1, obj.getBanco());

                // 3 executar o comando sql
                stmt.execute();
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Lançamento realizado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
//usado

    public void alterarLanc(Banco obj) {
        try {
            // 1 criar o comando sql
            String sql = "update tbl_banco set banco =? where id=?";

            try ( // 2 conectar o banco sql e organizar comando sql

                    PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, obj.getBanco());

                stmt.setInt(2, obj.getId());

                // 3 executar o comando sql
                stmt.execute();
                stmt.close();
            }

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
//usado

    public void excluirLanc(Banco obj) {
        try {
            // 1 criar o comando sql
            String sql = "delete from tbl_banco where id = ?";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getId());

                // 3 executar o comando sql
                stmt.execute();
                stmt.close();
            }
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }
//usado

    public List<Banco> listarLanc() {
        try {

            //1 passo criar a lista
            List<Banco> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.banco "
                    + "from tbl_banco as l  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Banco obj = new Banco();

                obj.setId(rs.getInt("l.id"));

                obj.setBanco(rs.getString("l.banco"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Banco> buscaLAncPorTipo(String nome) {
        try {

            //1 passo criar a lista
            List<Banco> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.banco "
                    + "from tbl_banco as l  where l.banco like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Banco obj = new Banco();

                obj.setId(rs.getInt("l.id"));

                obj.setBanco(rs.getString("l.classificacao"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    public List<Banco> listarLanc1() {
        try {

            //1 passo criar a lista
            List<Banco> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.banco "
                    + "from tbl_banco as l order by l.banco ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Banco obj = new Banco();

                obj.setId(rs.getInt("l.id"));

                obj.setBanco(rs.getString("l.banco"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
}