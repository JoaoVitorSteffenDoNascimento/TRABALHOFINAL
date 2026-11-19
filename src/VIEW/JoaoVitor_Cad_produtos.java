package VIEW;

import BEAN.Produtos;
import CONNECT.Banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JoaoVitor_Cad_produtos extends javax.swing.JInternalFrame {

    public String recebe_id;
    public String recebe_descricao;
    public String recebe_marca;
    public double recebe_preco;
    public String recebe_unidade;
    public String recebe_validade;

    public JoaoVitor_Cad_produtos() {
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
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(95);
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
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            String sqlConsulta;
            if (optAlfa.isSelected()) {
                sqlConsulta = "SELECT * FROM produtos ORDER BY descricao";
            } else {
                sqlConsulta = "SELECT * FROM produtos ORDER BY idproduto";
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                recebe_id = rs.getString("idproduto");
                recebe_descricao = rs.getString("descricao");
                recebe_marca = rs.getString("marca");
                recebe_preco = rs.getDouble("preco");
                recebe_unidade = rs.getString("unidade");
                recebe_validade = rs.getString("data_validade");
                DefaultTableModel grid = (DefaultTableModel) jTable1.getModel();
                grid.addRow(new String[]{recebe_id, recebe_descricao, recebe_marca, df.format(recebe_preco), recebe_unidade, recebe_validade});
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
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            String sqlConsulta = "SELECT * FROM produtos WHERE idproduto = " + cod2;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                txt_jv_cod.setText(rs.getString("idproduto"));
                txt_jv_descricao.setText(rs.getString("descricao"));
                txt_jv_marca.setText(rs.getString("marca"));
                txt_jv_preco.setText(df.format(rs.getDouble("preco")));
                String unidade = rs.getString("unidade");
                if (unidade != null) {
                    cmb_jv_unidade.setSelectedItem(unidade);
                }
                txt_jv_validade.setText(rs.getString("data_validade"));
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
        txt_jv_descricao.setText("");
        txt_jv_marca.setText("");
        txt_jv_preco.setText("");
        cmb_jv_unidade.setSelectedIndex(0);
        txt_jv_validade.setValue(null);
        txt_jv_validade.setText("");
        txt_jv_descricao.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_jv_data_cadastro = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        txt_jv_cod = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        txt_jv_descricao = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        txt_jv_marca = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();
        txt_jv_preco = new javax.swing.JTextField();
        lblUnidade = new javax.swing.JLabel();
        cmb_jv_unidade = new javax.swing.JComboBox<>();
        lblValidade = new javax.swing.JLabel();
        txt_jv_validade = new javax.swing.JFormattedTextField();
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
        setTitle("Cadastro de Produtos");

        lbl_jv_data_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lbl_jv_data_cadastro.setForeground(new java.awt.Color(0, 51, 102));
        lbl_jv_data_cadastro.setText("Data do Cadastro:");

        lblCod.setText("Código");

        txt_jv_cod.setEditable(false);
        txt_jv_cod.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_cod.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_cod.setToolTipText("Código gerado automaticamente pelo banco de dados");

        lblDescricao.setText("Descrição");

        txt_jv_descricao.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_descricao.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_descricao.setToolTipText("Informe a descrição do produto");

        lblMarca.setText("Marca");

        txt_jv_marca.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_marca.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_marca.setToolTipText("Informe a marca do produto");

        lblPreco.setText("Preço");

        txt_jv_preco.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_preco.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_preco.setToolTipText("Informe o preço do produto (ex: 19,90)");

        lblUnidade.setText("Unidade");

        cmb_jv_unidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "um", "kg", "cm", "mt" }));
        cmb_jv_unidade.setToolTipText("Selecione a unidade de medida do produto");

        lblValidade.setText("Data de Validade");

        txt_jv_validade.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_validade.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txt_jv_validade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_jv_validade.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txt_jv_validade.setToolTipText("Informe a data de validade no formato dd/mm/aaaa");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Marca", "Preço", "Unidade", "Validade"
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
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_preco, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 750, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmb_jv_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 750, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 740, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 750, Short.MAX_VALUE))
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
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 365, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(txt_jv_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 339, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 312, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(txt_jv_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 286, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 259, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(txt_jv_preco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 233, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(lblUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 206, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(cmb_jv_unidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 180, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(lblValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 153, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(txt_jv_validade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        recebe_descricao = txt_jv_descricao.getText().trim();
        recebe_marca = txt_jv_marca.getText().trim();
        recebe_unidade = cmb_jv_unidade.getSelectedItem().toString();
        recebe_validade = txt_jv_validade.getText().trim();

        if (recebe_descricao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a descrição do produto.");
            txt_jv_descricao.requestFocus();
            return;
        }

        try {
            String precoTexto = txt_jv_preco.getText().trim().replace(",", ".");
            recebe_preco = Double.parseDouble(precoTexto);
            if (recebe_preco < 0) {
                JOptionPane.showMessageDialog(null, "Informe um preço válido.");
                txt_jv_preco.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Preço inválido. Informe um valor numérico (ex: 19,90).");
            txt_jv_preco.requestFocus();
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
            Produtos cad = new Produtos();
            cad.setDescricao(recebe_descricao);
            cad.setMarca(recebe_marca);
            cad.setPreco(recebe_preco);
            cad.setUnidade(recebe_unidade);
            cad.setData_validade(recebe_validade);
            cad.incluir(conn);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao gravar produto: " + ex.getMessage());
        }
    }//GEN-LAST:event_btGravarActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao consultar produtos: " + ex.getMessage());
        }
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para alterar.");
            return;
        }

        recebe_descricao = txt_jv_descricao.getText().trim();
        recebe_marca = txt_jv_marca.getText().trim();
        recebe_unidade = cmb_jv_unidade.getSelectedItem().toString();
        recebe_validade = txt_jv_validade.getText().trim();

        if (recebe_descricao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a descrição do produto.");
            txt_jv_descricao.requestFocus();
            return;
        }

        try {
            String precoTexto = txt_jv_preco.getText().trim().replace(",", ".");
            recebe_preco = Double.parseDouble(precoTexto);
            if (recebe_preco < 0) {
                JOptionPane.showMessageDialog(null, "Informe um preço válido.");
                txt_jv_preco.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Preço inválido. Informe um valor numérico (ex: 19,90).");
            txt_jv_preco.requestFocus();
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
            Produtos cad = new Produtos();
            cad.setDescricao(recebe_descricao);
            cad.setMarca(recebe_marca);
            cad.setPreco(recebe_preco);
            cad.setUnidade(recebe_unidade);
            cad.setData_validade(recebe_validade);
            int cod_id = Integer.parseInt(recebe_id);
            cad.alterar(conn, cod_id);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Código inválido para alteração.");
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto: " + ex.getMessage());
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para excluir.");
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
                Produtos cad = new Produtos();
                int cod_id = Integer.parseInt(recebe_id);
                cad.excluir(conn, cod_id);
                conn.close();
                LimparTela();
                CarregaGrid();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Código inválido para exclusão.");
            } catch (SQLException ex) {
                Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + ex.getMessage());
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
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar produto: " + ex.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void optAlfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAlfaActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optAlfaActionPerformed

    private void optCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCodigoActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_produtos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btGravar;
    private javax.swing.JButton btSair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmb_jv_unidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblUnidade;
    private javax.swing.JLabel lblValidade;
    private javax.swing.JLabel lbl_jv_data_cadastro;
    private javax.swing.JRadioButton optAlfa;
    private javax.swing.JRadioButton optCodigo;
    private javax.swing.JTextField txt_jv_cod;
    private javax.swing.JTextField txt_jv_descricao;
    private javax.swing.JTextField txt_jv_marca;
    private javax.swing.JTextField txt_jv_preco;
    private javax.swing.JFormattedTextField txt_jv_validade;
    // End of variables declaration//GEN-END:variables
}
