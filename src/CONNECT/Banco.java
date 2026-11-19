package CONNECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Banco {

    public boolean banco_ok = true;

    public Connection obtemConexao() {
        String base = "trabalho";
        String usuario = "root";
        String senha = "";
        try {
            banco_ok = true;
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + base, usuario, senha);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas com a Conexão do Banco de Dados: " + e.getMessage());
            banco_ok = false;
        }
        return null;
    }
}
