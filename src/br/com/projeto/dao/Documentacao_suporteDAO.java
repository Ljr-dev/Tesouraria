/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Documentacao_suporte;

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
public class Documentacao_suporteDAO {

    private Connection con;

    public Documentacao_suporteDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarLanc(Documentacao_suporte obj) {

        try {
            // 1 criar o comando sql
            String query = "insert into tbl_documentacao_suporte(documentacao_suporte)"
                    + "values(?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setString(1, obj.getDocumentacao_suporte());

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

    public void alterarLanc(Documentacao_suporte obj) {
        try {
            // 1 criar o comando sql
            String sql = "update tbl_documentacao_suporte set documentacao_suporte =? where id=?";

            try ( // 2 conectar o banco sql e organizar comando sql

                    PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, obj.getDocumentacao_suporte());

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

    public void excluirLanc(Documentacao_suporte obj) {
        try {
            // 1 criar o comando sql
            String sql = "delete from tbl_documentacao_suporte where id = ?";
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

    public List<Documentacao_suporte> listarLanc() {
        try {

            //1 passo criar a lista
            List<Documentacao_suporte> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.documentacao_suporte "
                    + "from tbl_documentacao_suporte as l  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Documentacao_suporte obj = new Documentacao_suporte();

                obj.setId(rs.getInt("l.id"));

                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Documentacao_suporte> buscaLAncPorTipo(String nome) {
        try {

            //1 passo criar a lista
            List<Documentacao_suporte> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.documentacao_suporte "
                    + "from tbl_documentacao_suporte as l  where l.documentacao_suporte like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Documentacao_suporte obj = new Documentacao_suporte();

                obj.setId(rs.getInt("l.id"));

                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    public List<Documentacao_suporte> listarLanc1() {
        try {

            //1 passo criar a lista
            List<Documentacao_suporte> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.documentacao_suporte "
                    + "from tbl_documentacao_suporte as l order by l.documentacao_suporte ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Documentacao_suporte obj = new Documentacao_suporte();

                obj.setId(rs.getInt("l.id"));

                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
}
