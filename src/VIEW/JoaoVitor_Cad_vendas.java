package VIEW;

import BEAN.Vendas;
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

public class JoaoVitor_Cad_vendas extends javax.swing.JInternalFrame {

    public String recebe_id;
    public String recebe_data;
    public int recebe_id_cliente;
    public int recebe_id_produto;
    public int recebe_quantidade;
    public double recebe_valor_unitario;
    public double recebe_valor_total;
    public String recebe_condicoes;

    public JoaoVitor_Cad_vendas() {
        initComponents();
        mostrarDataAtual();
        configurarColunasTabela();
    }

    private void mostrarDataAtual() {
        Date hoje = new Date();
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataHoje = formatador.format(hoje);
        lbl_jv_data_cadastro.setText("Data do Cadastro: " + dataHoje);
        txt_jv_data_venda.setText(dataHoje);
    }

    private void configurarColunasTabela() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(90);
    }

    private void calcularTotal() {
        String qtdTexto = txt_jv_quantidade.getText().trim();
        String vlrTexto = txt_jv_valor_unitario.getText().trim().replace(",", ".");
        if (qtdTexto.isEmpty() || vlrTexto.isEmpty()) {
            return;
        }
        try {
            int qtd = Integer.parseInt(qtdTexto);
            double vlrUnit = Double.parseDouble(vlrTexto);
            double total = qtd * vlrUnit;
            DecimalFormat df = new DecimalFormat("0.00");
            txt_jv_valor_total.setText(df.format(total));
        } catch (NumberFormatException ex) {
            txt_jv_valor_total.setText("");
        }
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
            if (optData.isSelected()) {
                sqlConsulta = "SELECT * FROM vendas ORDER BY data_venda";
            } else {
                sqlConsulta = "SELECT * FROM vendas ORDER BY idvenda";
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                recebe_id = rs.getString("idvenda");
                recebe_data = rs.getString("data_venda");
                recebe_id_cliente = rs.getInt("id_cliente");
                recebe_id_produto = rs.getInt("id_produto");
                recebe_quantidade = rs.getInt("quantidade");
                recebe_valor_unitario = rs.getDouble("valor_unitario");
                recebe_valor_total = rs.getDouble("valor_total");
                recebe_condicoes = rs.getString("condicoes_pagamento");
                DefaultTableModel grid = (DefaultTableModel) jTable1.getModel();
                grid.addRow(new String[]{
                    recebe_id,
                    recebe_data,
                    String.valueOf(recebe_id_cliente),
                    String.valueOf(recebe_id_produto),
                    String.valueOf(recebe_quantidade),
                    df.format(recebe_valor_unitario),
                    df.format(recebe_valor_total),
                    recebe_condicoes
                });
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
            String sqlConsulta = "SELECT * FROM vendas WHERE idvenda = " + cod2;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlConsulta);
            while (rs.next()) {
                txt_jv_cod.setText(rs.getString("idvenda"));
                txt_jv_data_venda.setText(rs.getString("data_venda"));
                txt_jv_id_cliente.setText(String.valueOf(rs.getInt("id_cliente")));
                txt_jv_id_produto.setText(String.valueOf(rs.getInt("id_produto")));
                txt_jv_quantidade.setText(String.valueOf(rs.getInt("quantidade")));
                txt_jv_valor_unitario.setText(df.format(rs.getDouble("valor_unitario")));
                txt_jv_valor_total.setText(df.format(rs.getDouble("valor_total")));
                String cond = rs.getString("condicoes_pagamento");
                if (cond != null) {
                    cmb_jv_condicoes.setSelectedItem(cond);
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
        Date hoje = new Date();
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        txt_jv_data_venda.setText(formatador.format(hoje));
        txt_jv_id_cliente.setText("");
        txt_jv_id_produto.setText("");
        txt_jv_quantidade.setText("");
        txt_jv_valor_unitario.setText("");
        txt_jv_valor_total.setText("");
        cmb_jv_condicoes.setSelectedIndex(0);
        txt_jv_id_cliente.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbl_jv_data_cadastro = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        txt_jv_cod = new javax.swing.JTextField();
        lblDataVenda = new javax.swing.JLabel();
        txt_jv_data_venda = new javax.swing.JFormattedTextField();
        lblIdCliente = new javax.swing.JLabel();
        txt_jv_id_cliente = new javax.swing.JTextField();
        lblIdProduto = new javax.swing.JLabel();
        txt_jv_id_produto = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        txt_jv_quantidade = new javax.swing.JTextField();
        lblValorUnitario = new javax.swing.JLabel();
        txt_jv_valor_unitario = new javax.swing.JTextField();
        lblValorTotal = new javax.swing.JLabel();
        txt_jv_valor_total = new javax.swing.JTextField();
        lblCondicoes = new javax.swing.JLabel();
        cmb_jv_condicoes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btGravar = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        optData = new javax.swing.JRadioButton();
        optCodigo = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Vendas");

        lbl_jv_data_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lbl_jv_data_cadastro.setForeground(new java.awt.Color(0, 51, 102));
        lbl_jv_data_cadastro.setText("Data do Cadastro:");

        lblCod.setText("Código");

        txt_jv_cod.setEditable(false);
        txt_jv_cod.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_cod.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_cod.setToolTipText("Código gerado automaticamente pelo banco de dados");

        lblDataVenda.setText("Data da Venda");

        txt_jv_data_venda.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_data_venda.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txt_jv_data_venda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_jv_data_venda.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txt_jv_data_venda.setToolTipText("Informe a data da venda no formato dd/mm/aaaa");

        lblIdCliente.setText("ID Cliente");

        txt_jv_id_cliente.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_id_cliente.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_id_cliente.setToolTipText("Informe o código (ID) numérico do cliente");

        lblIdProduto.setText("ID Produto");

        txt_jv_id_produto.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_id_produto.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_id_produto.setToolTipText("Informe o código (ID) numérico do produto");

        lblQuantidade.setText("Quantidade");

        txt_jv_quantidade.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_quantidade.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_quantidade.setToolTipText("Informe a quantidade de itens vendidos");
        txt_jv_quantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_jv_quantidadeFocusLost(evt);
            }
        });

        lblValorUnitario.setText("Valor Unitário");

        txt_jv_valor_unitario.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_valor_unitario.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_valor_unitario.setToolTipText("Informe o valor unitário do produto (ex: 25,50)");
        txt_jv_valor_unitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_jv_valor_unitarioFocusLost(evt);
            }
        });

        lblValorTotal.setText("Valor Total");

        txt_jv_valor_total.setEditable(false);
        txt_jv_valor_total.setBackground(new java.awt.Color(232, 240, 254));
        txt_jv_valor_total.setForeground(new java.awt.Color(0, 51, 102));
        txt_jv_valor_total.setToolTipText("Valor total calculado automaticamente (quantidade x valor unitário)");

        lblCondicoes.setText("Condições de Pagamento");

        cmb_jv_condicoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "a vista", "2 vezes", "3 vezes", "4 vezes", "5 vezes" }));
        cmb_jv_condicoes.setToolTipText("Selecione a condição de pagamento da venda");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Cliente", "Produto", "Qtd", "Vlr Unit.", "Vlr Total", "Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        buttonGroup1.add(optData);
        optData.setText("Ordem Data");
        optData.addActionListener(this::optDataActionPerformed);

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
                .addGap(0, 640, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 830, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 820, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(lblDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 690, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(txt_jv_data_venda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 680, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 810, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 810, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(lblIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 700, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(txt_jv_id_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 680, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 810, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 810, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(lblValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 690, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(txt_jv_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 680, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_jv_valor_total, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 680, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCondicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 720, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmb_jv_condicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 680, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 685, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 685, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 560, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(optData, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 390, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(545, 545, 545)
                .addComponent(optCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 235, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbl_jv_data_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 446, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 415, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(txt_jv_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 389, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 415, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(txt_jv_data_venda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 389, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lblIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 355, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(txt_jv_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 329, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(lblIdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 355, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(txt_jv_id_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 329, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lblQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 295, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(txt_jv_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 269, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lblValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 295, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(txt_jv_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 269, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(lblValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 235, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(txt_jv_valor_total, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 209, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(lblCondicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 175, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(cmb_jv_condicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 149, Short.MAX_VALUE))
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
                .addComponent(optData, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(398, 398, 398)
                .addComponent(optCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_jv_quantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_jv_quantidadeFocusLost
        calcularTotal();
    }//GEN-LAST:event_txt_jv_quantidadeFocusLost

    private void txt_jv_valor_unitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_jv_valor_unitarioFocusLost
        calcularTotal();
    }//GEN-LAST:event_txt_jv_valor_unitarioFocusLost

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed
        recebe_data = txt_jv_data_venda.getText().trim();
        recebe_condicoes = cmb_jv_condicoes.getSelectedItem().toString();

        try {
            recebe_id_cliente = Integer.parseInt(txt_jv_id_cliente.getText().trim());
            recebe_id_produto = Integer.parseInt(txt_jv_id_produto.getText().trim());
            recebe_quantidade = Integer.parseInt(txt_jv_quantidade.getText().trim());
            recebe_valor_unitario = Double.parseDouble(txt_jv_valor_unitario.getText().trim().replace(",", "."));
            if (recebe_quantidade <= 0 || recebe_valor_unitario < 0) {
                JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero e valor unitário não pode ser negativo.");
                return;
            }
            recebe_valor_total = recebe_quantidade * recebe_valor_unitario;
            DecimalFormat df = new DecimalFormat("0.00");
            txt_jv_valor_total.setText(df.format(recebe_valor_total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique os campos numéricos (ID Cliente, ID Produto, Quantidade e Valor Unitário).");
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
            Vendas cad = new Vendas();
            cad.setData_venda(recebe_data);
            cad.setId_cliente(recebe_id_cliente);
            cad.setId_produto(recebe_id_produto);
            cad.setQuantidade(recebe_quantidade);
            cad.setValor_unitario(recebe_valor_unitario);
            cad.setValor_total(recebe_valor_total);
            cad.setCondicoes_pagamento(recebe_condicoes);
            cad.incluir(conn);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao gravar venda: " + ex.getMessage());
        }
    }//GEN-LAST:event_btGravarActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao consultar vendas: " + ex.getMessage());
        }
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma venda na tabela para alterar.");
            return;
        }

        recebe_data = txt_jv_data_venda.getText().trim();
        recebe_condicoes = cmb_jv_condicoes.getSelectedItem().toString();

        try {
            recebe_id_cliente = Integer.parseInt(txt_jv_id_cliente.getText().trim());
            recebe_id_produto = Integer.parseInt(txt_jv_id_produto.getText().trim());
            recebe_quantidade = Integer.parseInt(txt_jv_quantidade.getText().trim());
            recebe_valor_unitario = Double.parseDouble(txt_jv_valor_unitario.getText().trim().replace(",", "."));
            if (recebe_quantidade <= 0 || recebe_valor_unitario < 0) {
                JOptionPane.showMessageDialog(null, "Quantidade deve ser maior que zero e valor unitário não pode ser negativo.");
                return;
            }
            recebe_valor_total = recebe_quantidade * recebe_valor_unitario;
            DecimalFormat df = new DecimalFormat("0.00");
            txt_jv_valor_total.setText(df.format(recebe_valor_total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Verifique os campos numéricos (ID Cliente, ID Produto, Quantidade e Valor Unitário).");
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
            Vendas cad = new Vendas();
            cad.setData_venda(recebe_data);
            cad.setId_cliente(recebe_id_cliente);
            cad.setId_produto(recebe_id_produto);
            cad.setQuantidade(recebe_quantidade);
            cad.setValor_unitario(recebe_valor_unitario);
            cad.setValor_total(recebe_valor_total);
            cad.setCondicoes_pagamento(recebe_condicoes);
            int cod_id = Integer.parseInt(recebe_id);
            cad.alterar(conn, cod_id);
            conn.close();
            LimparTela();
            CarregaGrid();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Código inválido para alteração.");
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar venda: " + ex.getMessage());
        }
    }//GEN-LAST:event_btAlterarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        recebe_id = txt_jv_cod.getText().trim();
        if (recebe_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione uma venda na tabela para excluir.");
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
                Vendas cad = new Vendas();
                int cod_id = Integer.parseInt(recebe_id);
                cad.excluir(conn, cod_id);
                conn.close();
                LimparTela();
                CarregaGrid();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Código inválido para exclusão.");
            } catch (SQLException ex) {
                Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao excluir venda: " + ex.getMessage());
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
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar venda: " + ex.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void optDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optDataActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optDataActionPerformed

    private void optCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCodigoActionPerformed
        try {
            CarregaGrid();
        } catch (SQLException ex) {
            Logger.getLogger(JoaoVitor_Cad_vendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_optCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btGravar;
    private javax.swing.JButton btSair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmb_jv_condicoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblCondicoes;
    private javax.swing.JLabel lblDataVenda;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblIdProduto;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblValorUnitario;
    private javax.swing.JLabel lbl_jv_data_cadastro;
    private javax.swing.JRadioButton optCodigo;
    private javax.swing.JRadioButton optData;
    private javax.swing.JTextField txt_jv_cod;
    private javax.swing.JFormattedTextField txt_jv_data_venda;
    private javax.swing.JTextField txt_jv_id_cliente;
    private javax.swing.JTextField txt_jv_id_produto;
    private javax.swing.JTextField txt_jv_quantidade;
    private javax.swing.JTextField txt_jv_valor_total;
    private javax.swing.JTextField txt_jv_valor_unitario;
    // End of variables declaration//GEN-END:variables
}
