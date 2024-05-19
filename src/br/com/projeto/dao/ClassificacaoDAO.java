/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Classificacao;

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
public class ClassificacaoDAO {

    private Connection con;

    public ClassificacaoDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarLanc(Classificacao obj) {

        try {
            // 1 criar o comando sql
            String query = "insert into tbl_classificacao(classificacao)"
                    + "values(?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setString(1, obj.getClassificacao());

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

    public void alterarLanc(Classificacao obj) {
        try {
            // 1 criar o comando sql
            String sql = "update tbl_classificacao set classificacao =? where id=?";

            try ( // 2 conectar o banco sql e organizar comando sql

                    PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, obj.getClassificacao());

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

    public void excluirLanc(Classificacao obj) {
        try {
            // 1 criar o comando sql
            String sql = "delete from tbl_classificacao where id = ?";
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

    public List<Classificacao> listarLanc() {
        try {

            //1 passo criar a lista
            List<Classificacao> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.classificacao "
                    + "from tbl_classificacao as l  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao obj = new Classificacao();

                obj.setId(rs.getInt("l.id"));

                obj.setClassificacao(rs.getString("l.classificacao"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Classificacao> buscaLAncPorTipo(String nome) {
        try {

            //1 passo criar a lista
            List<Classificacao> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.classificacao "
                    + "from tbl_classificacao as l  where l.classificacao like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao obj = new Classificacao();

                obj.setId(rs.getInt("l.id"));

                obj.setClassificacao(rs.getString("l.classificacao"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }

    public List<Classificacao> listarLanc1() {
        try {

            //1 passo criar a lista
            List<Classificacao> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , l.classificacao "
                    + "from tbl_classificacao as l order by l.classificacao ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Classificacao obj = new Classificacao();

                obj.setId(rs.getInt("l.id"));

                obj.setClassificacao(rs.getString("l.classificacao"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
}
