/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.awt.event.FocusAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import static testMaking.ConnectionClass.URL;

/**
 *
 * @author 00499
 */
public class ConnectionRegsys extends javax.swing.JFrame {

    static final String URL = "jdbc:mysql://localhost:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";
    static final String USERNAME = "root";
    static final String PASSWORD = "pass";

    /**
     * Creates new form ConnectionRegsys
     */
    public ConnectionRegsys() {
        initComponents();
    }

    private void updateJComboBoxItems() {
        // コネクションの作成
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {

            connection.setAutoCommit(false);

            Statement stm = connection.createStatement();
            String sql_select = "select * from regsys";
            ResultSet rs = stm.executeQuery(sql_select);
            this.jComboBoxTitle.removeAllItems();
            while (rs.next()) {
                String titleget = rs.getString("title");
                jComboBoxTitle.addItem(titleget);
                this.jTextFieldSysdir.setText(rs.getString("sysdir"));
                this.jTextFieldExtension.setText(rs.getString("extension"));
                this.jTextAreaRemark.setText(rs.getString("remark"));
                //String sysDir = rs.getString(rs.getString("sysdir"));
                //System.out.println("取得結果 -> " + titleget + ":" + sysDir);
                
            }

        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
             JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
            e.printStackTrace();

        }

    }

    public void updateRegsys(String title, String sysdir, String extension, String remark) {
        String sql = "INSERT INTO  regsys VALUES (?, ?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql);) {

            connection.setAutoCommit(false);

            statement.setString(1, this.jComboBoxTitle.getSelectedItem().toString());
            statement.setString(2, this.jTextFieldSysdir.getText());
            statement.setString(3, this.jTextFieldExtension.getText());
            statement.setString(4, this.jTextAreaRemark.getText());
            statement.addBatch();
            System.out.println(statement.toString());

            int[] result = statement.executeBatch();
            System.out.println("登録：" + result.length + "件");

            try {

                connection.commit();
                System.out.println("登録成功");

            } catch (SQLException e) {

                connection.rollback();
                System.out.println("登録失敗：ロールバック実行");

                e.printStackTrace();

            }

            Statement stm = connection.createStatement();
            String sql_select = "select * from regsys";
            ResultSet rs = stm.executeQuery(sql_select);
            while (rs.next()) {
                String titleget = rs.getString("title");
                String sysDir = rs.getString("sysdir");
                System.out.println("取得結果 -> " + titleget + ":" + sysDir);
            }

        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            e.printStackTrace();

        }

    }

    public void deleteRegsys(String title) {
        String sql = "DELETE FROM regsys WHERE title = (?);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql);) {

            connection.setAutoCommit(false);

            statement.setString(1, title);
            statement.addBatch();
            System.out.println(statement.toString());

            int[] result = statement.executeBatch();
            System.out.println("削除：" + result.length + "件");

            try {

                connection.commit();
                System.out.println("削除成功");

            } catch (SQLException e) {

                connection.rollback();
                System.out.println("削除失敗：ロールバック実行");

                e.printStackTrace();

            }

            Statement stm = connection.createStatement();
            String sql_select = "select * from regsys";
            ResultSet rs = stm.executeQuery(sql_select);
            while (rs.next()) {
                String titleget = rs.getString("title");
                String sysDir = rs.getString("sysdir");
                System.out.println("取得結果 -> " + titleget + ":" + sysDir);
            }

        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            e.printStackTrace();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("AutomaticFileGenerationManagementSystemPU").createEntityManager();
        jButtonEntry = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jTextFieldSysdir = new javax.swing.JTextField();
        jTextFieldExtension = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRemark = new javax.swing.JTextArea();
        jComboBoxTitle = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonEntry.setText("登録");
        jButtonEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntryActionPerformed(evt);
            }
        });

        jButtonDelete.setText("削除");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jTextFieldSysdir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSysdirFocusGained(evt);
            }
        });

        jTextAreaRemark.setColumns(20);
        jTextAreaRemark.setRows(5);
        jScrollPane1.setViewportView(jTextAreaRemark);

        jComboBoxTitle.setEditable(true);
        jComboBoxTitle.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxTitlePopupMenuWillBecomeVisible(evt);
            }
        });
        jComboBoxTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTitleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jTextFieldSysdir)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldExtension, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonEntry)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelete)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBoxTitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSysdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEntry)
                    .addComponent(jButtonDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntryActionPerformed
        updateRegsys(null, null, null, null);
    }//GEN-LAST:event_jButtonEntryActionPerformed

    private void jTextFieldSysdirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSysdirFocusGained

    }//GEN-LAST:event_jTextFieldSysdirFocusGained

    private void jComboBoxTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTitleActionPerformed

    }//GEN-LAST:event_jComboBoxTitleActionPerformed

    private void jComboBoxTitlePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxTitlePopupMenuWillBecomeVisible
        // ComboBoxを編集可能にしているとこのイベントでしか拾えないらしい。
        // http://d.hatena.ne.jp/wakamori/touch/20070518/p1
        updateJComboBoxItems();
    }//GEN-LAST:event_jComboBoxTitlePopupMenuWillBecomeVisible

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteRegsys(this.jComboBoxTitle.getSelectedItem().toString());
        updateJComboBoxItems();
        this.jComboBoxTitle.setSelectedItem(null);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ConnectionRegsys.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionRegsys.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionRegsys.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionRegsys.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionRegsys().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEntry;
    private javax.swing.JComboBox jComboBoxTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRemark;
    private javax.swing.JTextField jTextFieldExtension;
    private javax.swing.JTextField jTextFieldSysdir;
    // End of variables declaration//GEN-END:variables
}
