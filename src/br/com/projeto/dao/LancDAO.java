/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Lanc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JAVA
 */
public class LancDAO {

    private Connection con;

    public LancDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    // Metodo Cadastrar Cliente
    //usado
    public void cadastrarLanc(Lanc obj) {

        try {
            // 1 criar o comando sql
            String sql = "insert into tbl_lancamentos (data_lanc,descricao,status_,classificacao,documentacao_suporte,tipo,valor,banco)"
                    + "values(?,?,?,?,?,?,?,?)";
            try ( // 2 conectar o banco sql e organizar comando sql
                    PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, obj.getData());
                stmt.setString(2, obj.getDescricao());
                stmt.setString(3, obj.getStatus_());
                stmt.setString(4, obj.getClassificacao());
                stmt.setString(5, obj.getDocumentacao_suporte());
                stmt.setString(6, obj.getTipo());
                stmt.setDouble(7, obj.getValor());
                stmt.setString(8, obj.getBanco());

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

    public void alterarLanc(Lanc obj) {
        try {
            // 1 criar o comando sql
            String sql = "update tbl_lancamentos set data_lanc =?,descricao =?,status_ =?,classificacao =?,documentacao_suporte =?,tipo =?,valor =?,banco =? where id =?";

            try ( // 2 conectar o banco sql e organizar comando sql

                    PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, obj.getData());
                stmt.setString(2, obj.getDescricao());
                stmt.setString(3, obj.getStatus_());
                stmt.setString(4, obj.getClassificacao());
                stmt.setString(5, obj.getDocumentacao_suporte());
                stmt.setString(6, obj.getTipo());
                stmt.setDouble(7, obj.getValor());
                stmt.setString(8, obj.getBanco());

                stmt.setInt(9, obj.getId());

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

    public void excluirLanc(Lanc obj) {
        try {
            // 1 criar o comando sql
            String sql = "delete from tbl_lancamentos where id = ?";
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

    public List<Lanc> listarLancPorPeriodo(LocalDate data_inicio, LocalDate data_fim, String tipo) {

        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , date_format(data_lanc,'%d/%m/%Y') as data_formatada , l.descricao,l.status_,l.classificacao,l.documentacao_suporte,l.tipo,l.valor,l.banco  "
                    + "from tbl_lancamentos as l  where l.data_lanc BETWEEN ? AND ? and  l.tipo like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());
            stmt.setString(3, tipo);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Lanc obj = new Lanc();

                obj.setId(rs.getInt("l.id"));
                obj.setData(rs.getString("data_formatada"));
                obj.setDescricao(rs.getString("l.descricao"));
                obj.setStatus_(rs.getString("l.status_"));
                obj.setClassificacao(rs.getString("l.classificacao"));
                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));
                obj.setTipo(rs.getString("l.tipo"));
                obj.setValor(rs.getDouble("l.valor"));
                obj.setBanco(rs.getString("l.banco"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
//usado

    public List<Lanc> listarLanc() {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , date_format(data_lanc,'%d/%m/%Y') as data_formatada , l.descricao,l.status_,l.classificacao,l.documentacao_suporte,l.tipo,l.valor,l.banco  "
                    + "from tbl_lancamentos as l  ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setId(rs.getInt("l.id"));
                obj.setData(rs.getString("data_formatada"));
                obj.setDescricao(rs.getString("l.descricao"));
                obj.setStatus_(rs.getString("l.status_"));
                obj.setClassificacao(rs.getString("l.classificacao"));
                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));
                obj.setTipo(rs.getString("l.tipo"));
                obj.setValor(rs.getDouble("l.valor"));
                obj.setBanco(rs.getString("l.banco"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
//usado

    public List<Lanc> listarReceita() {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select  sum(valor) as total from tbl_lancamentos where tipo = 'CREDITO' ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setValor(rs.getDouble("total"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Lanc> listarRecData(LocalDate data_inicioRec, LocalDate data_fimRec) {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select  sum(valor) as total from tbl_lancamentos where tipo = 'CREDITO'and data_lanc BETWEEN ? AND ?  ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicioRec.toString());
            stmt.setString(2, data_fimRec.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setValor(rs.getDouble("total"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Lanc> listarDespesa() {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select  sum(valor) as total from tbl_lancamentos where tipo = 'DEBITO' ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setValor(rs.getDouble("total"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Lanc> listarDesData(LocalDate data_inicioDes, LocalDate data_fimDes) {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select  sum(valor) as total from tbl_lancamentos where tipo = 'DEBITO'and data_lanc BETWEEN ? AND ?  ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicioDes.toString());
            stmt.setString(2, data_fimDes.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setValor(rs.getDouble("total"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    public List<Lanc> listarSoma() {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select (select sum(valor) as total from tbl_lancamentos where tipo = 'CREDITO') "
                    + "- "
                    + "(select sum(valor) as total from tbl_lancamentos where tipo = 'DEBITO')as saldo";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setValor(rs.getDouble("saldo"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //--------------------------------------------
    public List<Lanc> listarLancTotal(LocalDate data_inicioRec, LocalDate data_fimRec, LocalDate data_inicioDes, LocalDate data_fimDes) {

        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = " Select   (SELECT sum(valor) FROM tbl_lancamentos where tipo = 'Credito'  and data_lanc BETWEEN ? AND ?  ) as receita ,"
                    + "         (select sum(valor) FROM tbl_lancamentos where tipo = 'Debito' and data_lanc BETWEEN ? AND ?   ) as  despesa ,"
                    + "         (select receita - despesa ) as saldo  ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicioRec.toString());
            stmt.setString(2, data_fimRec.toString());
            stmt.setString(3, data_inicioDes.toString());
            stmt.setString(4, data_fimDes.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Lanc obj = new Lanc();

                obj.setSaldo(rs.getDouble("saldo"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //---------------------------------------
    //usado
    public List<Lanc> buscaLAncPorTipo(String nome) {
        try {

            //1 passo criar a lista
            List<Lanc> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select l.id , date_format(data_lanc,'%d/%m/%Y') as data_formatada , l.descricao,l.status_,l.classificacao,l.documentacao_suporte,l.tipo,l.valor,l.banco  "
                    + "from tbl_lancamentos as l  where l.tipo like ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Lanc obj = new Lanc();

                obj.setId(rs.getInt("l.id"));
                obj.setData(rs.getString("data_formatada"));
                obj.setDescricao(rs.getString("l.descricao"));
                obj.setStatus_(rs.getString("l.status_"));
                obj.setClassificacao(rs.getString("l.classificacao"));
                obj.setDocumentacao_suporte(rs.getString("l.documentacao_suporte"));
                obj.setTipo(rs.getString("l.tipo"));
                obj.setValor(rs.getDouble("l.valor"));
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
