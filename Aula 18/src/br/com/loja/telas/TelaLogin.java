package br.com.loja.telas;
import br.com.loja.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {
    Connection conexao = null; //recebe status da conexão
    PreparedStatement pst = null; //prepara as consultas sql
    ResultSet rs = null; //recebe o resultado das consultas sql
    
    public void logar(){
     String sql = "SELECT * FROM usuarios WHERE login=? AND senha=?";
     try {
         pst = conexao.prepareStatement(sql);
         pst.setString(1, txtEmail.getText());
       //  pst.setString(2, txtSenha.getText());
       String captura_senha = new String(txtSenha.getPassword());
       pst.setString(2, captura_senha);
         
         rs = pst.executeQuery();
         
         if (rs.next()){
          //ir para a tela principal
          String perfil = rs.getString(6);
          //System.out.println("Perfil: " + perfil);
          if(perfil.equals("admin")){
              TelaPrincipal principal = new TelaPrincipal();
                principal.setVisible(true);
                TelaPrincipal.MenuRelatorio.setEnabled(true);
                TelaPrincipal.MenuUsuarios.setEnabled(true);
                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                this.dispose();
                conexao.close();
          }else{
               TelaPrincipal principal = new TelaPrincipal();
                principal.setVisible(true);
                TelaPrincipal.lblUsuario.setText(rs.getString(2));
                this.dispose();
                conexao.close();
                        
          }
         // JOptionPane.showMessageDialog(null, "LOGADO");
        // TelaPrincipal principal = new TelaPrincipal();
        // principal.setVisible(true);
        // this.dispose();
        // conexao.close();
          
         } else {JOptionPane.showMessageDialog(null, "Usuário ou Senha inválidos");
         
         
         }
         
     } catch (Exception e){ 
         JOptionPane.showMessageDialog(null, e);
     }
    
    }
    
    public TelaLogin() {
        initComponents();
        conexao = ModuloConexao.conector();
      //  System.out.println("Resultado: " + conexao);
      if (conexao != null){
      //conexao ok
     // lblStatus.setText("Conectado");
      lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/dbok.png")));
      }else {
      //desconectado
      //lblStatus.setText("Não conectado");
       lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/dberror.png")));
      }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setText("Email");

        jLabel2.setText("Senha");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblStatus)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnEntrar)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnEntrar)
                .addGap(38, 38, 38)
                .addComponent(lblStatus)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        logar();
    }//GEN-LAST:event_btnEntrarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
