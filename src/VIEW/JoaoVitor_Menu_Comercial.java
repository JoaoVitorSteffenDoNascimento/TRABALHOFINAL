package VIEW;

import java.io.IOException;
import javax.swing.JOptionPane;

public class JoaoVitor_Menu_Comercial extends javax.swing.JFrame {

    public JoaoVitor_Menu_Comercial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDPPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemClientes = new javax.swing.JMenuItem();
        itemProdutos = new javax.swing.JMenuItem();
        menuVendas = new javax.swing.JMenu();
        itemVendasConsumidor = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        itemCalculadora = new javax.swing.JMenuItem();
        itemSobre = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();
        itemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle Comercial");

        javax.swing.GroupLayout jDPPrincipalLayout = new javax.swing.GroupLayout(jDPPrincipal);
        jDPPrincipal.setLayout(jDPPrincipalLayout);
        jDPPrincipalLayout.setHorizontalGroup(
            jDPPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jDPPrincipalLayout.setVerticalGroup(
            jDPPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        menuCadastro.setText("Cadastro");

        itemClientes.setText("Clientes");
        itemClientes.addActionListener(this::itemClientesActionPerformed);
        menuCadastro.add(itemClientes);

        itemProdutos.setText("Produtos");
        itemProdutos.addActionListener(this::itemProdutosActionPerformed);
        menuCadastro.add(itemProdutos);

        jMenuBar1.add(menuCadastro);

        menuVendas.setText("Vendas");

        itemVendasConsumidor.setText("Vendas ao Consumidor");
        itemVendasConsumidor.addActionListener(this::itemVendasConsumidorActionPerformed);
        menuVendas.add(itemVendasConsumidor);

        jMenuBar1.add(menuVendas);

        menuAjuda.setText("Ajuda");

        itemCalculadora.setText("Calculadora");
        itemCalculadora.addActionListener(this::itemCalculadoraActionPerformed);
        menuAjuda.add(itemCalculadora);

        itemSobre.setText("Sobre");
        itemSobre.addActionListener(this::itemSobreActionPerformed);
        menuAjuda.add(itemSobre);

        jMenuBar1.add(menuAjuda);

        menuSair.setText("Sair");

        itemSair.setText("Sair");
        itemSair.addActionListener(this::itemSairActionPerformed);
        menuSair.add(itemSair);

        jMenuBar1.add(menuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDPPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDPPrincipal)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientesActionPerformed
        JoaoVitor_Cad_clientes obj = new JoaoVitor_Cad_clientes();
        jDPPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_itemClientesActionPerformed

    private void itemProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProdutosActionPerformed
        JoaoVitor_Cad_produtos obj = new JoaoVitor_Cad_produtos();
        jDPPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_itemProdutosActionPerformed

    private void itemVendasConsumidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVendasConsumidorActionPerformed
        JoaoVitor_Cad_vendas obj = new JoaoVitor_Cad_vendas();
        jDPPrincipal.add(obj);
        obj.setVisible(true);
    }//GEN-LAST:event_itemVendasConsumidorActionPerformed

    private void itemCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCalculadoraActionPerformed
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível abrir a calculadora: " + ex.getMessage());
        }
    }//GEN-LAST:event_itemCalculadoraActionPerformed

    private void itemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSobreActionPerformed
        JOptionPane.showMessageDialog(null, "Sistema Controle Comercial - 2º Semestre\nDireitos Reservados IFSUL Campus Sapiranga\nINF3M - João Vítor");
    }//GEN-LAST:event_itemSobreActionPerformed

    private void itemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSairActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JoaoVitor_Menu_Comercial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JoaoVitor_Menu_Comercial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemCalculadora;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemProdutos;
    private javax.swing.JMenuItem itemSair;
    private javax.swing.JMenuItem itemSobre;
    private javax.swing.JMenuItem itemVendasConsumidor;
    private javax.swing.JDesktopPane jDPPrincipal;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVendas;
    // End of variables declaration//GEN-END:variables
}
