package br.com.loja.telas;
import java.sql.*;
import br.com.loja.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Alunos
 */
public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private String tipo;
    
    
    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

   private void pesquisar_cliente(){
       
       String sql = "SELECT idcli as id, nomecli as nome, fonecli as fone FROM clientes WHERE nomecli like ?";
       try {
           pst = conexao.prepareStatement(sql);
           pst.setString(1, txtCli.getText() + "%");
           rs = pst.executeQuery();
           tabelaClientes.setModel(DbUtils.resultSetToTableModel(rs));
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
   private void setar_campos(){
       int setar = tabelaClientes.getSelectedRow();
       txtId.setText(tabelaClientes.getModel().getValueAt(setar, 0).toString());
   }
       
   private void emitir_os(){
       String sql = "INSERT INTO ordem_servico(tipo,situacao,equipamento,defeito)" + "tecnico,valor,servico,idcli) VALUES(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, comboSit.getSelectedItem().toString());
            pst.setString(3, txtEquip.getText());
            pst.setString(4, txtDef.getText());
            pst.setString(5, txtSer.getText());
            pst.setString(6, txtTec.getText());
            pst.setString(7, txtTotal.getText());
            pst.setString(8, txtId.getText());
            if (txtId.getText().isEmpty() || txtEquip.getText().isEmpty() || txtDef.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if(adicionado>0){
                    JOptionPane.showMessageDialog(null,"Ordem de serviço emitida com sucesso!");
                    txtId.setText(null);
                    txtEquip.setText(null);
                    txtDef.setText(null);
                    txtTec.setText(null);
                    txtTotal.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
   private void pesquisar_os(){
       String num_os = JOptionPane.showInputDialog("Número da OS: ");
       String sql = "SELECT * FROM ordem_servico WHERE os = " + num_os;
       try {
           pst = conexao.prepareStatement(sql);
           rs = pst.executeQuery();
           if (rs.next()) {
               txtNOs.setText(rs.getString(1));
               txtData.setText(rs.getString(2));
               String radio_tipo = (rs.getString(3));
               if (radio_tipo.equals("OS")) {
                   rbtOS.setSelected(true);
                   tipo = "OS";}
           } else {
               radioOrc.setSelected(true);
               tipo = "Orçamento";
           }
           comboSit.setSelectedItem(rs.getString(4));
           txtEquip.setText(rs.getString(5));
           txtDef.setText(rs.getString(6));
           txtSer.setText(rs.getString(9));
           txtTec.setText(rs.getString(7));
           txtTotal.setText(rs.getString(8));
           txtId.setText(rs.getString(10));
           btnAdc.setEnabled(false);
           btnPesq.setEnabled(false);
           tabelaClientes.setVisible(false);
           } else {
               JOptionPane.showMessageDialog(null, "OS não cadastrada!");
               }
       } catch (Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
   
   
   
   
   
   
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selecao_orcam_servico = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNOS = new javax.swing.JTextField();
        radioOrc = new javax.swing.JRadioButton();
        radioOrd = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCli = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEquip = new javax.swing.JTextField();
        txtSer = new javax.swing.JTextField();
        txtDef = new javax.swing.JTextField();
        txtTec = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnPesq = new javax.swing.JButton();
        btnAdc = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setText("Nº OS");

        txtData.setEditable(false);

        jLabel2.setText("Data");

        txtNOS.setEditable(false);
        txtNOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOSActionPerformed(evt);
            }
        });

        selecao_orcam_servico.add(radioOrc);
        radioOrc.setText("Orçamento");
        radioOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOrcActionPerformed(evt);
            }
        });

        selecao_orcam_servico.add(radioOrd);
        radioOrd.setText("Ordem de Serviço");
        radioOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOrdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(radioOrc)
                    .addComponent(txtNOS, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioOrd)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioOrc)
                    .addComponent(radioOrd))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel4.setText("Situação");

        comboSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrega OK", "Orçamento reprovado", "Aguardando aprovação", "Aguardando peças", "Abandonado pelo cliente", "Na bancada", "Retornou", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/pesquisar.png"))); // NOI18N

        jLabel5.setText("Id");

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Fone"
            }
        ));
        tabelaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCli, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPesquisar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jLabel6.setText("Equipamento");

        jLabel7.setText("Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Técnico");

        jLabel10.setText("Valor total");

        btnPesq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/read.png"))); // NOI18N
        btnPesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesqActionPerformed(evt);
            }
        });

        btnAdc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/create.png"))); // NOI18N

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/update.png"))); // NOI18N

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/delete.png"))); // NOI18N

        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/print.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(comboSit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSer, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDef, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTec, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotal))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnAdc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdc)
                            .addComponent(btnPesq, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNOSActionPerformed

    private void tabelaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaClientesKeyReleased
       pesquisar_cliente();
    }//GEN-LAST:event_tabelaClientesKeyReleased

    private void radioOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOrcActionPerformed
      tipo = "Orçamento";
    }//GEN-LAST:event_radioOrcActionPerformed

    private void radioOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOrdActionPerformed
      tipo = "os";
    }//GEN-LAST:event_radioOrdActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnPesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesqActionPerformed
       pesquisar_os();
    }//GEN-LAST:event_btnPesqActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdc;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPesq;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> comboSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioOrc;
    private javax.swing.JRadioButton radioOrd;
    private javax.swing.ButtonGroup selecao_orcam_servico;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtCli;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDef;
    private javax.swing.JTextField txtEquip;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNOS;
    private javax.swing.JTextField txtSer;
    private javax.swing.JTextField txtTec;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
