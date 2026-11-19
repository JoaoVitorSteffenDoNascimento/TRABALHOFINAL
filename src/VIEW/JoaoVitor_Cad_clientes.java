package VIEW;

import BEAN.Clientes;
import CONNECT.Banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JoaoVitor_Cad_clientes extends javax.swing.JInternalFrame {

    public String recebe_id;
    public String recebe_nome;
    public String recebe_email;
    public String recebe_fone;
    public String recebe_cpf;
    public String recebe_cartao;

    public JoaoVitor_Cad_clientes() {
        initComponents();
        mostrarDataAtual();
        configurarColunasTabela();
    }

    private void mostrarDataAtual() {
        Date hoje = new Date();
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        lbl_jv_data_cadastro.setText("Data do Cadastro: " + formatador.format(hoje));
    }

    private void configurarColunasTabela() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(115);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(85);
    }

    public void CarregaGrid() throws SQLException {
        DefaultTableModel grid0 = (DefaultTableModel) jTable1.getModel();
        grid0.setNumRows(0);
        Connection conn = null;
        Banco bd = new Banco();
        conn = bd.obtemConexao();
        if (conn == null) {
            return;
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sqlConsulta;
            if (optAlfa.isSelected()) {
                sqlConsulta = "SELECT * FROM clientes ORDER BY nome";
            } else {
                sqlConsulta = "SELECT * FROM clientes ORDER BY idcliente";
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                recebe_id = rs.getString("idcliente");
                recebe_nome = rs.getString("nome");
                recebe_email = rs.getString("email");
                recebe_fone = rs.getString("telefone");
                recebe_cpf = rs.getString("cpf");
                recebe_cartao = rs.getString("cartao");
                DefaultTableModel grid = (DefaultTableModel) jTable1.getModel();
                grid.addRow(new String[]{recebe_id, recebe_nome, recebe_email, recebe_fone, recebe_cpf, recebe_cartao});
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    public void CarregaTela(int cod2) throws SQLException {
        Connection conn = null;
        Banco bd = new Banco();
        conn = bd.obtemConexao();
        if (conn == null) {
            return;
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sqlConsulta = "SELECT * FROM clientes WHERE idcliente = " + cod2;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                txt_jv_cod.setText(rs.getString("idcliente"));
                txt_jv_nome.setText(rs.getString("nome"));
                txt_jv_email.setText(rs.getString("email"));
                txt_jv_telefone.setText(rs.getString("telefone"));
                txt_jv_cpf.setText(rs.getString("cpf"));
                String cartao = rs.getString("cartao");
                if (cartao != null) {
                    cmb_jv_cartao.setSelectedItem(cartao);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            conn.close();
        }
    }

    public void LimparTela() {
        txt_jv_cod.setText("");
        txt_jv_nome.setText("");
        txt_jv_email.setText("");
        txt_jv_telefone.setValue(null);
        txt_jv_telefone.setText("");
        txt_jv_cpf.setValue(null);
        txt_jv_cpf.setText("");
        cmb_jv_cartao.setSelectedIndex(0);
        txt_jv_nome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_jv_data_cadastro = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        txt_jv_cod = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txt_jv_nome = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txt_jv_email = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txt_jv_telefone = new javax.swing.JFormattedTextField();
        lblCpf = new javax.swing.JLabel();
        txt_jv_cpf = new javax.swing.JFormattedTextField();
        lblCartao = new javax.swing.JLabel();
        cmb_jv_cartao = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btGravar = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        optAlfa = new javax.swing.JRadioButton();
        optCodigo = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Clientes");

        lbl_jv_data_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lbl_jv_data_cadastro.setForeground(new java.awt.Color(0, 51, 102));
        lbl_jv_data_cadastro.setText("Data do Cadastro:");

        lblCod.setText("Código");

        txt_jv_cod.setEditable(false);
        txt_jv_cod.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_cod.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_cod.setToolTipText("Código gerado automaticamente pelo banco de dados");

        lblNome.setText("Nome");

        txt_jv_nome.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_nome.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_nome.setToolTipText("Informe o nome completo do cliente");

        lblEmail.setText("E-mail");

        txt_jv_email.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_email.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_email.setToolTipText("Informe o endereço de e-mail do cliente");

        lblTelefone.setText("Telefone");

        txt_jv_telefone.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_telefone.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txt_jv_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_jv_telefone.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txt_jv_telefone.setToolTipText("Informe o telefone com DDD no formato (99) 99999-9999");

        lblCpf.setText("CPF");

        txt_jv_cpf.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_cpf.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txt_jv_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_jv_cpf.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txt_jv_cpf.setToolTipText("Informe o CPF no formato 999.999.999-99");

        lblCartao.setText("Cartão");

        cmb_jv_cartao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visa", "Mastercard", "Elo", "Hipercard", "Nenhum" }));
        cmb_jv_cartao.setToolTipText("Selecione a bandeira do cartão do cliente");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "E-mail", "Telefone", "CPF", "Cartão"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btGravar.setText("Gravar");
        btGravar.addActionListener(this::btGravarActionPerformed);

        btConsultar.setText("Consultar");
        btConsultar.addActionListener(this::btConsultarActionPerformed);

        btAlterar.setText("Alterar");
        btAlterar.addActionListener(this::btAlterarActionPerformed);

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(this::btExcluirActionPerformed);

        btSair.setText("Sair");
        btSair.addActionListener(this::btSairActionPerformed);

        buttonGroup1.add(optAlfa);
        optAlfa.setText("Ordem Alfabética");
        optAlfa.addActionListener(this::optAlfaActionPerformed);

        buttonGroup1.add(optCodigo);
        optCodigo.setSelected(true);
        optCodigo.setText("Ordem Código");
        optCodigo.addActionListener(this::optCodigoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_jv_data_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 620, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 810, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_email, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmb_jv_cartao, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 780, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 665, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 780, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 665, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 520, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent(optAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 330, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(585, 585, 585)
                .addComponent(optCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbl_jv_data_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 446, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 418, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(txt_jv_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 392, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 365, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(txt_jv_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 339, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 312, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(txt_jv_email, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 286, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(lblTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 259, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(txt_jv_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 233, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 206, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(txt_jv_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 180, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(lblCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 153, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(cmb_jv_cartao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 127, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 108, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(btGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(425, 425, 425)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(optAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(optCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed
        recebe_nome = txt_jv_nome.getText().trim();
        recebe_email = txt_jv_email.getText().trim();
        recebe_fone = txt_jv_telefone.getText().trim();
        recebe_cpf = txt_jv_cpf.getText().trim();
        recebe_cartao = cmb_jv_cartao.getSelectedItem().toString();

        if (recebe_nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o nome do cliente.");
            txt_jv_nome.requestFocus();
            return;
        }

        Connection conn = null;
        Banco bd = new Banco();
        conn = bd.obtemConexao();
        if (conn == null) {
            return;
        }
        try {
            conn.setAutoCommit(false);
            Clientes cad = new Clientes();
            cad.setNome(recebe_nome);
            cad.setTelefone(recebe_fone);
            cad.setEmail(recebe_email);
            cad.setCpf(recebe_cpf);
            cad.setCartao(recebe_cartao);
            cad.incluir(conn);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao gravar cliente: " + ex.getMessage());
        }
    }//GEN-LAST:event_btGravarActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao consultar clientes: " + ex.getMessage());
        }
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela para alterar.");
            return;
        }

        recebe_nome = txt_jv_nome.getText().trim();
        recebe_email = txt_jv_email.getText().trim();
        recebe_fone = txt_jv_telefone.getText().trim();
        recebe_cpf = txt_jv_cpf.getText().trim();
        recebe_cartao = cmb_jv_cartao.getSelectedItem().toString();

        if (recebe_nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o nome do cliente.");
            txt_jv_nome.requestFocus();
            return;
        }

        Connection conn = null;
        Banco bd = new Banco();
        conn = bd.obtemConexao();
        if (conn == null) {
            return;
        }
        try {
            conn.setAutoCommit(false);
            Clientes cad = new Clientes();
            cad.setNome(recebe_nome);
            cad.setTelefone(recebe_fone);
            cad.setEmail(recebe_email);
            cad.setCpf(recebe_cpf);
            cad.setCartao(recebe_cartao);
            int cod_id = Integer.parseInt(recebe_id);
            cad.alterar(conn, cod_id);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Código inválido para alteração.");
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar cliente: " + ex.getMessage());
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela para excluir.");
            return;
        }

        int dialogButton = JOptionPane.showConfirmDialog(null, "Confirma Exclusão?", "Sistema", JOptionPane.YES_NO_OPTION);
        if (dialogButton == JOptionPane.YES_OPTION) {
            Connection conn = null;
            Banco bd = new Banco();
            conn = bd.obtemConexao();
            if (conn == null) {
                return;
            }
            try {
                conn.setAutoCommit(false);
                Clientes cad = new Clientes();
                int cod_id = Integer.parseInt(recebe_id);
                cad.excluir(conn, cod_id);
                conn.close();
                LimparTela();
                CarregaGrid();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Código inválido para exclusão.");
            } catch (SQLException ex) {
                Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int var_Lin = jTable1.getSelectedRow();
        if (var_Lin < 0) {
            return;
        }
        String cod = jTable1.getValueAt(var_Lin, 0).toString();
        try {
            int codid = Integer.parseInt(cod);
            CarregaTela(codid);
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar cliente: " + ex.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void optAlfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAlfaActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optAlfaActionPerformed

    private void optCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCodigoActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btGravar;
    private javax.swing.JButton btSair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmb_jv_cartao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCartao;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lbl_jv_data_cadastro;
    private javax.swing.JRadioButton optAlfa;
    private javax.swing.JRadioButton optCodigo;
    private javax.swing.JTextField txt_jv_cod;
    private javax.swing.JFormattedTextField txt_jv_cpf;
    private javax.swing.JTextField txt_jv_email;
    private javax.swing.JTextField txt_jv_nome;
    private javax.swing.JFormattedTextField txt_jv_telefone;
    // End of variables declaration//GEN-END:variables
}
