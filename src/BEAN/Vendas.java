package BEAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Vendas {

    private String data_venda;
    private int id_cliente;
    private int id_produto;
    private int quantidade;
    private double valor_unitario;
    private double valor_total;
    private String condicoes_pagamento;

    public Vendas() {
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public String getCondicoes_pagamento() {
        return condicoes_pagamento;
    }

    public void setCondicoes_pagamento(String condicoes_pagamento) {
        this.condicoes_pagamento = condicoes_pagamento;
    }

    public void incluir(Connection conn) {
        String sqlInsert = "INSERT INTO vendas (data_venda, id_cliente, id_produto, quantidade, valor_unitario, valor_total, condicoes_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlInsert);
            stm.setString(1, getData_venda());
            stm.setInt(2, getId_cliente());
            stm.setInt(3, getId_produto());
            stm.setInt(4, getQuantidade());
            stm.setDouble(5, getValor_unitario());
            stm.setDouble(6, getValor_total());
            stm.setString(7, getCondicoes_pagamento());
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Venda Gravada com Sucesso");
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
        String sqlAltera = "UPDATE vendas SET data_venda = ?, id_cliente = ?, id_produto = ?, quantidade = ?, valor_unitario = ?, valor_total = ?, condicoes_pagamento = ? WHERE idvenda = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlAltera);
            stm.setString(1, getData_venda());
            stm.setInt(2, getId_cliente());
            stm.setInt(3, getId_produto());
            stm.setInt(4, getQuantidade());
            stm.setDouble(5, getValor_unitario());
            stm.setDouble(6, getValor_total());
            stm.setString(7, getCondicoes_pagamento());
            stm.setInt(8, cod);
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Venda Alterada com Sucesso");
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
        String sqlExcluir = "DELETE FROM vendas WHERE idvenda = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlExcluir);
            stm.setInt(1, cod);
            stm.execute();
            conn.commit();
            JOptionPane.showMessageDialog(null, "Venda Excluída com Sucesso");
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
