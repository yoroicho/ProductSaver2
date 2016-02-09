/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMaking;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 00499
 */
public class ConnectionRegsys extends javax.swing.JFrame {

    static final String URL = "jdbc:mysql://localhost:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";
    //static final String URL = "jdbc:mysql://49.212.131.91:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";

    static final String USERNAME = "root";
    static final String PASSWORD = "pass";

    /**
     * Creates new form ConnectionRegsys
     */
    public ConnectionRegsys() {
        initComponents();
        checkJCheckBoxNewState();
        updateJComboBoxItems();
    }

    private static Connection createConnection() { // コネクションを返す汎用メソッド
        // *** 各メソッドから呼ぶと使用時にエラーが出るので使えない ***
        // コネクションの作成
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
            return connection;
        } catch (SQLException e) {
            System.out.println("コネクション作成中にエラーが発生しました");
            JOptionPane.showMessageDialog(null, "コネクション作成中にエラーが発生しました");
            e.printStackTrace();
        }
        return null;
    }

    private void updateJComboBoxItems() {
        // コネクションの作成
        try (Connection connectionUpdateJComboBoxItems = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                PreparedStatement statement = connectionUpdateJComboBoxItems.prepareStatement("select * from regsys;");) {
            //statement.setString(1, "%");
            ResultSet resultSet = statement.executeQuery();

            this.jComboBoxTitles.removeAllItems();
            while (resultSet.next()) {
                String titleget = resultSet.getString("title");
                jComboBoxTitles.addItem(titleget);
                System.out.println("タイトル　" + titleget);
            }
            resultSet.close();

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

            statement.setString(1, title);
            statement.setString(2, sysdir);
            statement.setString(3, extension);
            statement.setString(4, remark);
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
                JOptionPane.showMessageDialog(null, "同じ名前で上書きはできません。");
                System.out.println("同じ名前で上書きはできません。");
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
            JOptionPane.showMessageDialog(null, "同じ名前で上書きはできません。");
        }

    }

    private ResultSet selectOneRegsys(String title) { // キーひとつでの検索
        try {
            //String sql = "SELECT * FROM regsys WHERE title = " + title;
            //Connection connectionSelectOneRegsys = createConnection();
            Connection connectionSelectOneRegsys = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connectionSelectOneRegsys.prepareStatement("select * from regsys where title = ?;");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            /*
             Connection connectionSelectOneRegsys = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stm = connectionSelectOneRegsys.createStatement();
             String sql_select = "select * from regsys where title = '" + title + "'";
             ResultSet resultSet = stm.executeQuery(sql_select);
             */
            resultSet.next();
            System.out.println("タイトルの索引" + resultSet.getString("title"));

            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionRegsys.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

        jButtonEntry = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jTextFieldSysdir = new javax.swing.JTextField();
        jTextFieldExtension = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRemark = new javax.swing.JTextArea();
        jComboBoxTitle = new javax.swing.JComboBox();
        jComboBoxTitles = new javax.swing.JComboBox();
        jCheckBoxNewState = new javax.swing.JCheckBox();
        jTextFieldTitle = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

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

        jComboBoxTitles.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTitlesItemStateChanged(evt);
            }
        });
        jComboBoxTitles.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxTitlesFocusGained(evt);
            }
        });

        jCheckBoxNewState.setText("新規作成");
        jCheckBoxNewState.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxNewStateStateChanged(evt);
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
                    .addComponent(jComboBoxTitles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBoxNewState)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldExtension, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonEntry)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelete)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldTitle))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxTitles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxNewState)
                    .addComponent(jComboBoxTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSysdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEntry)
                    .addComponent(jButtonDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntryActionPerformed
        jButtonEntry.setEnabled(false); // 二重登録を抑止、ただしチェックボックスにフォーカスが行くと再び登録ボタンが有効になる。
        updateRegsys(
                this.jTextFieldTitle.getText(),
                this.jTextFieldSysdir.getText(),
                this.jTextFieldExtension.getText(),
                this.jTextAreaRemark.getText()
        );

        //updateJComboBoxItems(); //登録後にコンボボックスを更新
        jCheckBoxNewState.setSelected(false); // 登録したら一旦非新規ﾓｰﾄﾞへ
        /**
         * this.jComboBoxTitles.setSelectedItem(null); // コンボボックスをクリア
         * this.jTextFieldTitle.setText(null); // 以下入力欄もクリア
         * this.jTextFieldSysdir.setText(null);
         * this.jTextFieldExtension.setText(null);
         * this.jTextAreaRemark.setText(null);
         */
    }//GEN-LAST:event_jButtonEntryActionPerformed

    private void jTextFieldSysdirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSysdirFocusGained

    }//GEN-LAST:event_jTextFieldSysdirFocusGained

    private void jComboBoxTitlePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxTitlePopupMenuWillBecomeVisible
        // ComboBoxを編集可能にしているとこのイベントでしか拾えないらしい。
        // http://d.hatena.ne.jp/wakamori/touch/20070518/p1
        //updateJComboBoxItems();
    }//GEN-LAST:event_jComboBoxTitlePopupMenuWillBecomeVisible

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteRegsys(this.jComboBoxTitles.getSelectedItem().toString()); // 削除の空振りを防ぐため登録が確定しているコンボボックスを参照
        //updateJComboBoxItems();
        this.jComboBoxTitles.setSelectedItem(null);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void checkJCheckBoxNewState() {
        if (jCheckBoxNewState.isSelected()) {
            this.jTextFieldTitle.setEditable(true);
            this.jTextFieldTitle.setBackground(Color.white); // Enable にすると分かりにくいので
            this.jTextFieldSysdir.setEditable(true);
            this.jTextFieldExtension.setEditable(true);
            this.jTextAreaRemark.setEditable(true);
            this.jButtonEntry.setEnabled(true);
            this.jButtonDelete.setEnabled(false); // 新規ﾓｰﾄﾞで削除不許可
        } else {
            this.jTextFieldTitle.setEditable(false);
            this.jTextFieldTitle.setBackground(Color.LIGHT_GRAY); // Enable にすると分かりにくいので
            this.jTextFieldSysdir.setEditable(false);
            this.jTextFieldExtension.setEditable(false);
            this.jTextAreaRemark.setEditable(false);
            this.jButtonEntry.setEnabled(false);
            this.jButtonDelete.setEnabled(true); // 非新規ﾓｰﾄﾞで削除可能へ
        }
    }

    private void jCheckBoxNewStateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxNewStateStateChanged

        checkJCheckBoxNewState();


    }//GEN-LAST:event_jCheckBoxNewStateStateChanged

    private void jComboBoxTitlesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxTitlesFocusGained
// フォームのイニシャライズ時と登録・更新のタイミングで更新。
        // フォーカスがあたったときの更新だと一度別のコンポーネントにフォーカスが当たってからになる
        // データベースから読み出すのに時間が掛かるせいもあり、このタイミングではやらないほうがよいかも。>なぜか削除のあと更新されないのでやはり入れる。
        updateJComboBoxItems();


    }//GEN-LAST:event_jComboBoxTitlesFocusGained

    private void jComboBoxTitlesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTitlesItemStateChanged
        try {
            if (!this.jComboBoxTitles.getSelectedItem().equals("")) { // NullPointerException が発生する。
                try {
                    // タイトルの選択が変わるに応じて内容を変化させる。
                    this.jTextFieldTitle.setText(
                            this.selectOneRegsys((this.jComboBoxTitles.getSelectedItem().toString())).getString("title")
                    );
                    this.jTextFieldSysdir.setText(
                            this.selectOneRegsys(this.jComboBoxTitles.getSelectedItem().toString()).getString("sysdir")
                    );
                    this.jTextFieldExtension.setText(
                            this.selectOneRegsys(this.jComboBoxTitles.getSelectedItem().toString()).getString("extension")
                    );
                    this.jTextAreaRemark.setText(
                            this.selectOneRegsys(this.jComboBoxTitles.getSelectedItem().toString()).getString("remark")
                    );

                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionRegsys.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (Exception e) {
            System.out.println("空欄");
        }
    }//GEN-LAST:event_jComboBoxTitlesItemStateChanged

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // コンボボックスを更新
        updateJComboBoxItems(); // かえって弊害がでるかも>大丈夫っぽい
    }//GEN-LAST:event_formFocusGained

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
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEntry;
    private javax.swing.JCheckBox jCheckBoxNewState;
    private javax.swing.JComboBox jComboBoxTitle;
    private javax.swing.JComboBox jComboBoxTitles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaRemark;
    private javax.swing.JTextField jTextFieldExtension;
    private javax.swing.JTextField jTextFieldSysdir;
    private javax.swing.JTextField jTextFieldTitle;
    // End of variables declaration//GEN-END:variables
}
