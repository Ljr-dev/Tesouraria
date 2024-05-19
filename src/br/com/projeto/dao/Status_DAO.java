/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Status_;

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
public class Status_DAO {
    private Connection con;

    public Status_DAO() {

        this.con = new ConnectionFactory().getConnection();
    }
    public void cadastrarLanc(Status_ obj) {

        try {
            // 1 criar o comando sql
            String query = "insert into tbl_status_(status_)"
                    + "values(?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setString(1, obj.getStatus_());
                

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

    public void alterarLanc(Status_ obj) {
        try {
            // 1 criar o comando sql
            String sql = "update tbl_status_ set status_ =? where id=?";

            try ( // 2 conectar o banco sql e organizar comando sql

                    PreparedStatement stmt = con.prepareStatement(sql)) {

                  stmt.setString(1, obj.getStatus_());
                

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

    public void excluirLanc(Status_ obj) {
        try {
            // 1 criar o comando sql
            String sql = "delete from tbl_status_ where id = ?";
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
    public List<Status_> listarLanc() {
        try {

            //1 passo criar a lista
            List<Status_> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.status_ "
                    + "from tbl_status_ as l  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Status_ obj = new Status_();

                obj.setId(rs.getInt("l.id"));
                
                obj.setStatus_(rs.getString("l.status_"));
                

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

public List<Status_> buscaLAncPorTipo(String nome) {
        try {

            //1 passo criar a lista
            List<Status_> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.status_ "
                    + "from tbl_status_ as l  where l.status_ like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Status_ obj = new Status_();

                obj.setId(rs.getInt("l.id"));
                
                obj.setStatus_(rs.getString("l.status_"));
                

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
public List<Status_> listarLanc1() {
        try {

            //1 passo criar a lista
            List<Status_> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.status_ "
                    + "from tbl_status_ as l order by l.status_ ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Status_ obj = new Status_();

                obj.setId(rs.getInt("l.id"));
                
                obj.setStatus_(rs.getString("l.status_"));
                

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
}
