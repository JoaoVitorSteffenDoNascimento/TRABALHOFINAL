package BEAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Produtos {

    private String descricao;
    private String marca;
    private double preco;
    private String unidade;
    private String data_validade;

    public Produtos() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getData_validade() {
        return data_validade;
    }

    public void setData_validade(String data_validade) {
        this.data_validade = data_validade;
    }

    public void incluir(Connection conn) {
        String sqlInsert = "INSERT INTO produtos (descricao, marca, preco, unidade, data_validade) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlInsert);
            stm.setString(1, getDescricao());
            stm.setString(2, getMarca());
            stm.setDouble(3, getPreco());
            stm.setString(4, getUnidade());
            stm.setString(5, getData_validade());
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Problemas ao desfazer operação: " + e1.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Problemas na inclusão: " + e.getMessage());
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar comando: " + e1.getMessage());
                }
            }
        }
    }

    public void alterar(Connection conn, Integer cod) {
        String sqlAltera = "UPDATE produtos SET descricao = ?, marca = ?, preco = ?, unidade = ?, data_validade = ? WHERE idproduto = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlAltera);
            stm.setString(1, getDescricao());
            stm.setString(2, getMarca());
            stm.setDouble(3, getPreco());
            stm.setString(4, getUnidade());
            stm.setString(5, getData_validade());
            stm.setInt(6, cod);
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Problemas ao desfazer operação: " + e1.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Problemas na atualização: " + e.getMessage());
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar comando: " + e1.getMessage());
                }
            }
        }
    }

    public void excluir(Connection conn, int cod) {
        String sqlExcluir = "DELETE FROM produtos WHERE idproduto = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlExcluir);
            stm.setInt(1, cod);
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Registro Excluído com Sucesso");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Problemas ao desfazer operação: " + e1.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Problemas na exclusão: " + e.getMessage());
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Erro ao fechar comando: " + e1.getMessage());
                }
            }
        }
    }
}
