package BEAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Clientes {

    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cartao;

    public Clientes() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public void incluir(Connection conn) {
        String sqlInsert = "INSERT INTO clientes (nome, telefone, email, cpf, cartao) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlInsert);
            stm.setString(1, getNome());
            stm.setString(2, getTelefone());
            stm.setString(3, getEmail());
            stm.setString(4, getCpf());
            stm.setString(5, getCartao());
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
        String sqlAltera = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, cpf = ?, cartao = ? WHERE idcliente = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlAltera);
            stm.setString(1, getNome());
            stm.setString(2, getTelefone());
            stm.setString(3, getEmail());
            stm.setString(4, getCpf());
            stm.setString(5, getCartao());
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
        String sqlExcluir = "DELETE FROM clientes WHERE idcliente = ?";
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
