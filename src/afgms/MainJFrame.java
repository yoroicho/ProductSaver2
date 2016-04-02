/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afgms;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import localIO.ConfigurationXML;


/**
 *
 * @author 00499
 */
public class MainJFrame extends javax.swing.JFrame {

    private String configFileName; // 設定ファイルの名前
    private Configuration conf; // 設定ファイルのインスタンス（旧）
    private Configuration configuration; // 設定ファイルのインスタンス（新）

    private boolean isShowedSysPanel = false;
    DefaultListModel appTitlesModel = new DefaultListModel(); // appInfo で使用するリストのモデル

    //static final String URL = "jdbc:mysql://localhost:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";
    private String lineSeparator;
    private String userDir;
    private String fileSeparator;

    //static final String URL = "jdbc:mysql://49.212.131.91:3306/test_swing_jdbc?zeroDateTimeBehavior=convertToNull";
    //static final String USERNAME = "root";
    //static final String PASSWORD = "pass";
    static String URL; // = conf.getProperty("dbUrl");
    static String USERNAME; // = conf.getProperty("dbUsername");
    static String PASSWORD; // = conf.getProperty("dbPassword");

    static final int SHIP_CODE_LENGTH = 4;

    /**
     * Creates new form NewJFrame
     *
     * @param configFileName せってい
     */
    public MainJFrame(String configFileName) {

        initComponents();

        // 表示を初期化
        this.buttonGroupCat1.add(this.jRadioButtonSaveCat1Paste);
        this.buttonGroupCat1.add(this.jRadioButtonSaveCat1Fork);
        this.buttonGroupCat2.add(this.jRadioButtonSaveCat2Paste);
        this.buttonGroupCat2.add(this.jRadioButtonSaveCat2Fork);
        this.buttonGroupCat3.add(this.jRadioButtonSaveCat3Paste);
        this.buttonGroupCat3.add(this.jRadioButtonSaveCat3Fork);
        this.jRadioButtonSaveCat1Fork.setSelected(true); // Cat1は基本世代管理
        this.jRadioButtonSaveCat2Paste.setSelected(true); // Cat2は基本上書き
        this.jRadioButtonSaveCat3Paste.setSelected(true); // Cat3は基本上書き

        this.jButtonShipDelete.setEnabled(false); // Shipコード削除ボタンを無効化

        try { // OS依存情報取得
            this.lineSeparator = System.getProperty("line.separator"); // 改行コード
            this.userDir = System.getProperty("user.dir"); // 自分自身のパス
            this.fileSeparator = System.getProperty("file.separator"); // Windowsでは円マーク
            // System.out.println("プログラム起動ディレクトリは " + this.lineSeparator + " " + this.userDir + " " + this.fileSeparator + " です。");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "OS依存情報が取得できません、終了いたします。");
            System.exit(0);
        }

        // this.configFileDir = userDir + fileSeparator + configFileName+".xml"; // 設定ファイルの場所をセット
        this.configFileName = configFileName;
        configuration = new Configuration(configFileName); // 設定ファイルとインスタンスをバインド

        URL = configuration.getProperty("dbUrl");
        USERNAME = configuration.getProperty("dbUsername");
        PASSWORD = configuration.getProperty("dbPassword");
        System.out.println(URL + USERNAME + PASSWORD);

    }

    private boolean basicSauceFileCopy(String sourceDir, String copyTarget, int copyMode) { // このシステムで基本とするソースファイルのコピー
        try {
            CopyFiles copyFiles = new CopyFiles();
            copyFiles.setMainJFrame(this);
            copyFiles.copySibling(sourceDir, copyTarget, copyMode);

        } catch (IOException ex) {
            Logger.getLogger(MainJFrame.class
                    .getName()).log(Level.SEVERE, null, ex);

            this.setMessagejTextAreaRedirectErrorStream(ex.toString());
        }
        return true;
    }

    public void printInputStreamSystemInvoke(InputStream isSystemInvoke) throws IOException {
        BufferedReader brSystemInvoke = new BufferedReader(new InputStreamReader(isSystemInvoke));
        try {
            for (;;) {
                String line = brSystemInvoke.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
                jTextAreaRedirectErrorStream.append(line + userDir);
            }
        } finally {
            brSystemInvoke.close();
        }
    }

    public void setMessagejTextAreaRedirectErrorStream(String msg) {
        jTextAreaRedirectErrorStream.append(msg + userDir);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCat1 = new javax.swing.ButtonGroup();
        buttonGroupCat2 = new javax.swing.ButtonGroup();
        buttonGroupCat3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldShipNameO = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaShipRemark = new javax.swing.JTextArea();
        jButtonShipEnter = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldShipNameY = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldShipCode = new javax.swing.JTextField();
        jButtonShipSelectWhereShipCode = new javax.swing.JButton();
        jButtonShipDelete = new javax.swing.JButton();
        jButtonShipCodeClera = new javax.swing.JButton();
        jLabelShipEntryCondition = new javax.swing.JLabel();
        jPanelConfig = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldDbUrl = new javax.swing.JTextField();
        jTextFieldDbUsername = new javax.swing.JTextField();
        jTextFieldDbPassword = new javax.swing.JTextField();
        jTextFieldPasteBuckupHome = new javax.swing.JTextField();
        jTextFieldMultigenerationalBackupHome = new javax.swing.JTextField();
        jButtonEnterConfig = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jRadioButtonSaveCat3Paste = new javax.swing.JRadioButton();
        jRadioButtonSaveCat3Fork = new javax.swing.JRadioButton();
        jButtonWriteCat3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldIssueDir = new javax.swing.JTextField();
        jButtonIssueDir = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonSystemInvoke = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBoxMakeCode = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCopyTarget2 = new javax.swing.JTextField();
        jButtonCatDir2 = new javax.swing.JButton();
        jButtonSystemDir = new javax.swing.JButton();
        jTextFieldSystemDir = new javax.swing.JTextField();
        jTextFieldSourceDir = new javax.swing.JTextField();
        jButtonSourceDir = new javax.swing.JButton();
        jTextFieldCopyTarget1 = new javax.swing.JTextField();
        jButtonCatDir1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaRedirectErrorStream = new javax.swing.JTextArea();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jRadioButtonSaveCat1Paste = new javax.swing.JRadioButton();
        jCheckBox6 = new javax.swing.JCheckBox();
        jRadioButtonSaveCat1Fork = new javax.swing.JRadioButton();
        jCheckBox7 = new javax.swing.JCheckBox();
        jRadioButtonSaveCat2Paste = new javax.swing.JRadioButton();
        jButtonEnterItems = new javax.swing.JButton();
        jRadioButtonSaveCat2Fork = new javax.swing.JRadioButton();
        jButtonDeleteItems = new javax.swing.JButton();
        jButtonWriteCat1 = new javax.swing.JButton();
        jButtonSetDefaultItems = new javax.swing.JButton();
        jButtonWriteCat2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldCopyTarget3 = new javax.swing.JTextField();
        jButtonCatDir3 = new javax.swing.JButton();
        jPanelAppInfo = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListTitles = new javax.swing.JList();
        jButtonDelete = new javax.swing.JButton();
        jButtonEntry = new javax.swing.JButton();
        jTextFieldTitle = new javax.swing.JTextField();
        jCheckBoxNewState = new javax.swing.JCheckBox();
        jTextFieldSysdir = new javax.swing.JTextField();
        jButtonSysdir = new javax.swing.JButton();
        jTextFieldExtension = new javax.swing.JTextField();
        jTextFieldPreText = new javax.swing.JTextField();
        jTextFieldSufText = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jComboBoxAutoSaveTime = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldAutoSaveKeyType = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldAutoSaveMouseX = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldAutoSaveMouseY = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaRemark = new javax.swing.JTextArea(25,80);
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanelExeSys = new javax.swing.JPanel();
        jLabelSeq1 = new javax.swing.JLabel();
        jButtonSystemDirSeq1 = new javax.swing.JButton();
        jTextFieldSystemDirSeq1 = new javax.swing.JTextField();
        jTextFieldSeq1 = new javax.swing.JTextField();
        jLabelSeq4 = new javax.swing.JLabel();
        jTextFieldSeq2 = new javax.swing.JTextField();
        jTextFieldSystemDirSeq2 = new javax.swing.JTextField();
        jTextFieldSeq3 = new javax.swing.JTextField();
        jLabelSeq5 = new javax.swing.JLabel();
        jTextFieldSystemDirSeq3 = new javax.swing.JTextField();
        jLabelSeq6 = new javax.swing.JLabel();
        jTextFieldSystemDirSeq4 = new javax.swing.JTextField();
        jTextFieldSeq4 = new javax.swing.JTextField();
        jLabelSeq7 = new javax.swing.JLabel();
        jTextFieldSystemDirSeq5 = new javax.swing.JTextField();
        jTextFieldSeq5 = new javax.swing.JTextField();
        jLabelSeq8 = new javax.swing.JLabel();
        jTextFieldSeq6 = new javax.swing.JTextField();
        jTextFieldSystemDirSeq6 = new javax.swing.JTextField();
        jLabelSeq9 = new javax.swing.JLabel();
        jTextFieldSystemDirSeq7 = new javax.swing.JTextField();
        jTextFieldSeq7 = new javax.swing.JTextField();
        jTextFieldSeq8 = new javax.swing.JTextField();
        jLabelSeq10 = new javax.swing.JLabel();
        jTextFieldSystemDirSeq8 = new javax.swing.JTextField();
        jLabelSeq11 = new javax.swing.JLabel();
        jTextFieldSeq9 = new javax.swing.JTextField();
        jTextFieldSystemDirSeq9 = new javax.swing.JTextField();
        jLabelSeq12 = new javax.swing.JLabel();
        jTextFieldSeq10 = new javax.swing.JTextField();
        jTextFieldSystemDirSeq10 = new javax.swing.JTextField();
        jButtonSystemDirSeq2 = new javax.swing.JButton();
        jButtonSystemDirSeq3 = new javax.swing.JButton();
        jButtonSystemDirSeq4 = new javax.swing.JButton();
        jButtonSystemDirSeq5 = new javax.swing.JButton();
        jButtonSystemDirSeq6 = new javax.swing.JButton();
        jButtonSystemDirSeq7 = new javax.swing.JButton();
        jButtonSystemDirSeq8 = new javax.swing.JButton();
        jButtonSystemDirSeq9 = new javax.swing.JButton();
        jButtonSystemDirSeq10 = new javax.swing.JButton();
        jButtonEnter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldShipAndVoy = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jLabel19.setText("Vessel");

        jTextAreaShipRemark.setColumns(20);
        jTextAreaShipRemark.setRows(5);
        jScrollPane2.setViewportView(jTextAreaShipRemark);

        jButtonShipEnter.setText("入力");
        jButtonShipEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShipEnterActionPerformed(evt);
            }
        });

        jLabel30.setText("よみ");

        jLabel31.setText("CODE");

        jTextFieldShipCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldShipCodeFocusLost(evt);
            }
        });

        jButtonShipSelectWhereShipCode.setText("照会");
        jButtonShipSelectWhereShipCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShipSelectWhereShipCodeActionPerformed(evt);
            }
        });

        jButtonShipDelete.setText("削除");
        jButtonShipDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShipDeleteActionPerformed(evt);
            }
        });

        jButtonShipCodeClera.setText("クリア");
        jButtonShipCodeClera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShipCodeCleraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonShipEnter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonShipDelete)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldShipNameO, javax.swing.GroupLayout.DEFAULT_SIZE, 1538, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldShipCode, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelShipEntryCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonShipCodeClera)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonShipSelectWhereShipCode))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldShipNameY, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextFieldShipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonShipSelectWhereShipCode)
                    .addComponent(jButtonShipCodeClera)
                    .addComponent(jLabelShipEntryCondition))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldShipNameO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldShipNameY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonShipEnter)
                    .addComponent(jButtonShipDelete))
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("本船情報", jPanel1);

        jPanelConfig.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelConfigComponentShown(evt);
            }
        });

        jLabel20.setText("データベース");

        jLabel21.setText("URL");

        jLabel23.setText("Username");

        jLabel24.setText("Password");

        jLabel26.setText("上書退避基点");

        jLabel27.setText("世代蓄積基点");

        jButtonEnterConfig.setText("適用");
        jButtonEnterConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConfigLayout = new javax.swing.GroupLayout(jPanelConfig);
        jPanelConfig.setLayout(jPanelConfigLayout);
        jPanelConfigLayout.setHorizontalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24))
                        .addGap(22, 22, 22)
                        .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDbUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                            .addComponent(jTextFieldDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDbUsername)))
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelConfigLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldMultigenerationalBackupHome))
                            .addGroup(jPanelConfigLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPasteBuckupHome, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelConfigLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonEnterConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(888, Short.MAX_VALUE))
        );
        jPanelConfigLayout.setVerticalGroup(
            jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConfigLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldDbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldDbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPasteBuckupHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldMultigenerationalBackupHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(jButtonEnterConfig)
                .addGap(90, 90, 90))
        );

        jTabbedPane1.addTab("環境設定", jPanelConfig);

        jRadioButtonSaveCat3Paste.setText("無世代");
        jRadioButtonSaveCat3Paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSaveCat3PasteActionPerformed(evt);
            }
        });

        jRadioButtonSaveCat3Fork.setText("世代増");

        jButtonWriteCat3.setText("手動書込");
        jButtonWriteCat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteCat3ActionPerformed(evt);
            }
        });

        jLabel15.setText("送出準備");

        jButtonIssueDir.setText("・・・");

        jTextField3.setText("jTextField3");

        jLabel16.setText("フォルダ名付加文");

        jButton1.setText("（終了後に？）送出分岐");

        jLabel17.setText("資源格納");

        jButton4.setText("構成保存");

        jTextField2.setText("jTextField2");

        jButton5.setText("構成削除");

        jButton2.setText("作成");

        jButtonSystemInvoke.setText("起　動");
        jButtonSystemInvoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemInvokeActionPerformed(evt);
            }
        });

        jButton3.setText("資源格納場所をクリップボードへコピー");

        jComboBoxMakeCode.setEditable(true);
        jComboBoxMakeCode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setText("...");

        jLabel1.setText("システム");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton7.setText("親フォルダをクリップボードにコピー");

        jLabel2.setText("ソース");

        jCheckBox1.setText("起動前保存");

        jLabel3.setText("退避　１");

        jLabel4.setText("退避　２");

        jButtonCatDir2.setText("参　照");

        jButtonSystemDir.setText("参　照");
        jButtonSystemDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirActionPerformed(evt);
            }
        });

        jTextFieldSourceDir.setText("フォルダーは存在しない場合はダイアログを出して作成するかどうかユーザに選択させる。");

        jButtonSourceDir.setText("参　照");
        jButtonSourceDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSourceDirActionPerformed(evt);
            }
        });

        jTextFieldCopyTarget1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCopyTarget1ActionPerformed(evt);
            }
        });

        jButtonCatDir1.setText("参　照");
        jButtonCatDir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCatDir1ActionPerformed(evt);
            }
        });

        jTextAreaRedirectErrorStream.setColumns(20);
        jTextAreaRedirectErrorStream.setRows(5);
        jScrollPane1.setViewportView(jTextAreaRedirectErrorStream);

        jCheckBox2.setText("起動後保存");

        jCheckBox3.setText("起動前保存");

        jCheckBox4.setText("起動後保存");

        jCheckBox5.setText("起動前保存");

        jRadioButtonSaveCat1Paste.setText("無世代");

        jCheckBox6.setText("起動後保存");

        jRadioButtonSaveCat1Fork.setText("世代増");
        jRadioButtonSaveCat1Fork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSaveCat1ForkActionPerformed(evt);
            }
        });

        jCheckBox7.setText("編集可否");

        jRadioButtonSaveCat2Paste.setText("無世代");
        jRadioButtonSaveCat2Paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSaveCat2PasteActionPerformed(evt);
            }
        });

        jButtonEnterItems.setText("ENT");

        jRadioButtonSaveCat2Fork.setText("世代増");

        jButtonDeleteItems.setText("削除");

        jButtonWriteCat1.setText("手動書込");
        jButtonWriteCat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteCat1ActionPerformed(evt);
            }
        });

        jButtonSetDefaultItems.setText("新規");

        jButtonWriteCat2.setText("手動書込");
        jButtonWriteCat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWriteCat2ActionPerformed(evt);
            }
        });

        jTextField4.setText("ABCD12345");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("間　　隔");

        jLabel6.setText("分");

        jLabel7.setText("クリック位置");

        jLabel18.setText("作成フォルダ名");

        jButton8.setText("Dir作成移動（不要？）");

        jLabel8.setText("横（X)");

        jTextField5.setText("jTextField5");

        jLabel9.setText("縦（Y)");

        jTextField6.setText("jTextField6");

        jLabel10.setText("打　鍵");

        jTextField7.setText("jTextField7");

        jLabel11.setText("定期実行");

        jLabel12.setText("前置パラメタ");

        jLabel13.setText("後置パラメタ");

        jTextField1.setText("jTextField1");

        jTextField8.setText("jTextField8");

        jLabel14.setText("退避　２");

        jButtonCatDir3.setText("参　照");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCopyTarget2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCopyTarget1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCopyTarget3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButtonCatDir3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSaveCat3Paste)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonSaveCat3Fork)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonWriteCat3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonCatDir1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonCatDir2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonSaveCat1Paste)
                                    .addComponent(jRadioButtonSaveCat2Paste))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonSaveCat2Fork)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonSaveCat1Fork)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonWriteCat1)
                                    .addComponent(jButtonWriteCat2, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jButtonSystemInvoke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4)
                                        .addGap(91, 91, 91)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldSystemDir)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldSourceDir, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButtonSourceDir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButtonSystemDir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldIssueDir, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonIssueDir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxMakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEnterItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDeleteItems)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSetDefaultItems)))
                        .addGap(236, 236, 236)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMakeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7)
                    .addComponent(jButtonEnterItems)
                    .addComponent(jButtonDeleteItems)
                    .addComponent(jButtonSetDefaultItems))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSystemDir)
                    .addComponent(jTextFieldSystemDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSourceDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSourceDir)
                    .addComponent(jLabel2)
                    .addComponent(jButton7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldIssueDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIssueDir)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCopyTarget1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButtonSaveCat1Paste)
                    .addComponent(jRadioButtonSaveCat1Fork)
                    .addComponent(jButtonCatDir1)
                    .addComponent(jButtonWriteCat1)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox3)
                        .addComponent(jCheckBox4))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldCopyTarget2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCatDir2)
                        .addComponent(jRadioButtonSaveCat2Paste)
                        .addComponent(jRadioButtonSaveCat2Fork)
                        .addComponent(jButtonWriteCat2)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonSaveCat3Paste)
                        .addComponent(jRadioButtonSaveCat3Fork))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jTextFieldCopyTarget3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCatDir3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox6))
                        .addComponent(jButtonWriteCat3)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)
                        .addComponent(jButton5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jButton2)
                            .addComponent(jButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSystemInvoke)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanelAppInfo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAppInfoComponentShown(evt);
            }
        });

        jListTitles.setModel(appTitlesModel);
        jListTitles.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jListTitlesFocusGained(evt);
            }
        });
        jListTitles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTitlesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListTitles);

        jButtonDelete.setText("　　削除　　");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonEntry.setText("設定の保存");
        jButtonEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntryActionPerformed(evt);
            }
        });

        jCheckBoxNewState.setText("新しい設定を作成する");
        jCheckBoxNewState.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxNewStateStateChanged(evt);
            }
        });

        jButtonSysdir.setText("…");
        jButtonSysdir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSysdirActionPerformed(evt);
            }
        });

        jLabel28.setText("自動上書スクリプト間隔");

        jComboBoxAutoSaveTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "25", "30", "45", "60" }));
        jComboBoxAutoSaveTime.setSelectedIndex(5);

        jLabel22.setText("自動スクリプト文字列");

        jLabel25.setText("マウスポイントX(横）");

        jLabel29.setText("マウスポイントY（縦）");

        jTextAreaRemark.setColumns(80);
        jTextAreaRemark.setLineWrap(true);
        jTextAreaRemark.setRows(5);
        jScrollPane4.setViewportView(jTextAreaRemark);

        jLabel32.setText("名称");

        jLabel33.setText("実行ファイル位置");

        jLabel34.setText("被実行ファイル拡張子");

        jLabel35.setText("前置文字列");

        jLabel36.setText("後置文字列");

        jLabel37.setText("保管遡上階層数。起動ファイルから何階層上から保存するか");

        javax.swing.GroupLayout jPanelAppInfoLayout = new javax.swing.GroupLayout(jPanelAppInfo);
        jPanelAppInfo.setLayout(jPanelAppInfoLayout);
        jPanelAppInfoLayout.setHorizontalGroup(
            jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                        .addComponent(jCheckBoxNewState, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAppInfoLayout.createSequentialGroup()
                                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPreText)
                                    .addComponent(jTextFieldSysdir))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSysdir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSufText, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(887, 887, 887))
                            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                .addComponent(jButtonEntry)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAppInfoLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxAutoSaveTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAutoSaveKeyType))
                            .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldAutoSaveMouseX, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldAutoSaveMouseY, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                .addGap(657, 657, 657)
                .addComponent(jLabel37)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelAppInfoLayout.setVerticalGroup(
            jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEntry))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jTextFieldAutoSaveKeyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAppInfoLayout.createSequentialGroup()
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(2, 2, 2)
                        .addComponent(jLabel37)
                        .addGap(3, 3, 3)
                        .addComponent(jCheckBoxNewState)
                        .addGap(11, 11, 11)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSysdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSysdir)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPreText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(14, 14, 14)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldExtension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSufText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jComboBoxAutoSaveTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAppInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldAutoSaveMouseX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextFieldAutoSaveMouseY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("配下起動", jPanelAppInfo);

        jPanelExeSys.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanelExeSysFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanelExeSysFocusLost(evt);
            }
        });
        jPanelExeSys.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelExeSysComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelExeSysComponentShown(evt);
            }
        });

        jLabelSeq1.setText("１");

        jButtonSystemDirSeq1.setText("参　照");
        jButtonSystemDirSeq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq1ActionPerformed(evt);
            }
        });

        jLabelSeq4.setText("２");

        jLabelSeq5.setText("３");

        jLabelSeq6.setText("４");

        jLabelSeq7.setText("５");

        jLabelSeq8.setText("６");

        jLabelSeq9.setText("７");

        jLabelSeq10.setText("８");

        jLabelSeq11.setText("９");

        jLabelSeq12.setText("10");

        jButtonSystemDirSeq2.setText("参　照");
        jButtonSystemDirSeq2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq2ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq3.setText("参　照");
        jButtonSystemDirSeq3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq3ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq4.setText("参　照");
        jButtonSystemDirSeq4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq4ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq5.setText("参　照");
        jButtonSystemDirSeq5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq5ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq6.setText("参　照");
        jButtonSystemDirSeq6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq6ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq7.setText("参　照");
        jButtonSystemDirSeq7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq7ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq8.setText("参　照");
        jButtonSystemDirSeq8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq8ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq9.setText("参　照");
        jButtonSystemDirSeq9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq9ActionPerformed(evt);
            }
        });

        jButtonSystemDirSeq10.setText("参　照");
        jButtonSystemDirSeq10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemDirSeq10ActionPerformed(evt);
            }
        });

        jButtonEnter.setText("適　　用");
        jButtonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExeSysLayout = new javax.swing.GroupLayout(jPanelExeSys);
        jPanelExeSys.setLayout(jPanelExeSysLayout);
        jPanelExeSysLayout.setHorizontalGroup(
            jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExeSysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonEnter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                        .addComponent(jLabelSeq4)
                        .addGap(12, 12, 12)
                        .addComponent(jTextFieldSeq2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSystemDirSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSystemDirSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExeSysLayout.createSequentialGroup()
                        .addComponent(jLabelSeq1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSeq1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSystemDirSeq1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSystemDirSeq1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelExeSysLayout.createSequentialGroup()
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                .addComponent(jLabelSeq5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldSeq3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldSystemDirSeq3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                        .addComponent(jLabelSeq6)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextFieldSeq4))
                                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                        .addComponent(jLabelSeq7)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextFieldSeq5))
                                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                        .addComponent(jLabelSeq8)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextFieldSeq6))
                                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                        .addComponent(jLabelSeq9)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextFieldSeq7))
                                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelSeq10)
                                            .addComponent(jLabelSeq11)
                                            .addComponent(jLabelSeq12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldSeq10)
                                            .addComponent(jTextFieldSeq8)
                                            .addComponent(jTextFieldSeq9))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSystemDirSeq4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSystemDirSeq10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSystemDirSeq4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSystemDirSeq10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(378, 378, 378))
        );
        jPanelExeSysLayout.setVerticalGroup(
            jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExeSysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeq1)
                    .addComponent(jButtonSystemDirSeq1)
                    .addComponent(jTextFieldSystemDirSeq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSeq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelExeSysLayout.createSequentialGroup()
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq2)
                            .addComponent(jTextFieldSystemDirSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq3)
                            .addComponent(jTextFieldSystemDirSeq3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq4)
                            .addComponent(jTextFieldSystemDirSeq4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq5)
                            .addComponent(jTextFieldSystemDirSeq5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq6)
                            .addComponent(jTextFieldSystemDirSeq6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq7)
                            .addComponent(jTextFieldSystemDirSeq7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq8)
                            .addComponent(jTextFieldSystemDirSeq8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq9)
                            .addComponent(jTextFieldSystemDirSeq9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeq9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSeq11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSystemDirSeq10)
                            .addComponent(jTextFieldSystemDirSeq10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelExeSysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSeq12)
                        .addComponent(jTextFieldSeq10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButtonEnter)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("試セーブ", jPanelExeSys);

        jTextFieldShipAndVoy.setText("ShipVoy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextFieldShipAndVoy, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 682, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTextFieldShipAndVoy, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonWriteCat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteCat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWriteCat2ActionPerformed

    private void jButtonWriteCat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteCat1ActionPerformed
        int copyMode = CopyFiles.COPY_FORK; // 世代分離のほうが安全そうなので
        if (this.jRadioButtonSaveCat1Paste.isSelected()) { // 上書きの場合
            copyMode = CopyFiles.COPY_PASTE;
        } else if (this.jRadioButtonSaveCat1Fork.isSelected()) { // 世代分離コピーの場合
            copyMode = CopyFiles.COPY_FORK;
        }
        this.basicSauceFileCopy(
                this.jTextFieldSourceDir.getText(), this.jTextFieldCopyTarget1.getText(), copyMode);
    }//GEN-LAST:event_jButtonWriteCat1ActionPerformed

    private void jRadioButtonSaveCat2PasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSaveCat2PasteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSaveCat2PasteActionPerformed

    private void jRadioButtonSaveCat1ForkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSaveCat1ForkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSaveCat1ForkActionPerformed

    private void jButtonCatDir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCatDir1ActionPerformed
        JFileChooser filechooserCatDir1 = new JFileChooser();
        filechooserCatDir1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int selected = filechooserCatDir1.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileCatDir1 = filechooserCatDir1.getSelectedFile();
            this.jTextFieldCopyTarget1.setText(fileCatDir1.getPath());
        }
    }//GEN-LAST:event_jButtonCatDir1ActionPerformed

    private void jTextFieldCopyTarget1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCopyTarget1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCopyTarget1ActionPerformed

    private void jButtonSourceDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSourceDirActionPerformed
        JFileChooser filechooserSourceDir = new JFileChooser();
        int selected = filechooserSourceDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSourceDir = filechooserSourceDir.getSelectedFile();
            this.jTextFieldSourceDir.setText(fileSourceDir.getPath());
        }
    }//GEN-LAST:event_jButtonSourceDirActionPerformed

    private void jButtonSystemDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDir = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDir.setText(fileSystemDir.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirActionPerformed

    private void jButtonSystemInvokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemInvokeActionPerformed
        SystemInvokeThread systemInvokeThread = new SystemInvokeThread();
        systemInvokeThread.setMainJFrame(this);
        systemInvokeThread.setSystemDir(this.jTextFieldSystemDir.getText());
        systemInvokeThread.setSourceDir(this.jTextFieldSourceDir.getText());
        systemInvokeThread.start();
    }//GEN-LAST:event_jButtonSystemInvokeActionPerformed

    private void jButtonWriteCat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWriteCat3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWriteCat3ActionPerformed

    private void jRadioButtonSaveCat3PasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSaveCat3PasteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSaveCat3PasteActionPerformed

    private void jButtonSystemDirSeq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq1ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq1 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq1.setText(fileSystemDirSeq1.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq1ActionPerformed

    private void jButtonSystemDirSeq2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq2ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq2 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq2.setText(fileSystemDirSeq2.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq2ActionPerformed

    private void jButtonSystemDirSeq3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq3ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq3 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq3.setText(fileSystemDirSeq3.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq3ActionPerformed

    private void jButtonSystemDirSeq4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq4ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq4 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq4.setText(fileSystemDirSeq4.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq4ActionPerformed

    private void jButtonSystemDirSeq5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq5ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq5 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq5.setText(fileSystemDirSeq5.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq5ActionPerformed

    private void jButtonSystemDirSeq6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq6ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq6 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq6.setText(fileSystemDirSeq6.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq6ActionPerformed

    private void jButtonSystemDirSeq7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq7ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq7 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq7.setText(fileSystemDirSeq7.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq7ActionPerformed

    private void jButtonSystemDirSeq8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq8ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq8 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq8.setText(fileSystemDirSeq8.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq8ActionPerformed

    private void jButtonSystemDirSeq9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq9ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq9 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq9.setText(fileSystemDirSeq9.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq9ActionPerformed

    private void jButtonSystemDirSeq10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemDirSeq10ActionPerformed
        JFileChooser filechooserSystemDir = new JFileChooser();
        int selected = filechooserSystemDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileSystemDirSeq10 = filechooserSystemDir.getSelectedFile();
            this.jTextFieldSystemDirSeq10.setText(fileSystemDirSeq10.getPath());
        }
    }//GEN-LAST:event_jButtonSystemDirSeq10ActionPerformed

    private void storeItems() {
        try {
            //JOptionPane.showMessageDialog(null, "これからオープン");
            ConfigurationXML conf = new ConfigurationXML(userDir + fileSeparator + "ExecutionSys.XML"); //ファイルの位置は再考する
            //JOptionPane.showMessageDialog(null, "オープンした");
            conf.upDateProperty("jTextFieldSeq1", this.jTextFieldSeq1.getText());
            conf.upDateProperty("jTextFieldSeq2", this.jTextFieldSeq2.getText());
            conf.upDateProperty("jTextFieldSeq3", this.jTextFieldSeq3.getText());
            conf.upDateProperty("jTextFieldSeq4", this.jTextFieldSeq4.getText());
            conf.upDateProperty("jTextFieldSeq5", this.jTextFieldSeq5.getText());
            conf.upDateProperty("jTextFieldSeq6", this.jTextFieldSeq6.getText());
            conf.upDateProperty("jTextFieldSeq7", this.jTextFieldSeq7.getText());
            conf.upDateProperty("jTextFieldSeq8", this.jTextFieldSeq8.getText());
            conf.upDateProperty("jTextFieldSeq9", this.jTextFieldSeq9.getText());
            conf.upDateProperty("jTextFieldSeq10", this.jTextFieldSeq10.getText());

            conf.upDateProperty("jTextFieldSystemDirSeq1", this.jTextFieldSystemDirSeq1.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq2", this.jTextFieldSystemDirSeq2.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq3", this.jTextFieldSystemDirSeq3.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq4", this.jTextFieldSystemDirSeq4.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq5", this.jTextFieldSystemDirSeq5.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq6", this.jTextFieldSystemDirSeq6.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq7", this.jTextFieldSystemDirSeq7.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq8", this.jTextFieldSystemDirSeq8.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq9", this.jTextFieldSystemDirSeq9.getText());
            conf.upDateProperty("jTextFieldSystemDirSeq10", this.jTextFieldSystemDirSeq10.getText());

            conf.storeToXML("ExecutionSys.XML", "成果物作成用の起動システム候補");

        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "オープンエラー" + e.toString());
        }
    }

    private void loadItems() {
        try {
            // JOptionPane.showMessageDialog(null, "これからオープン");
            ConfigurationXML conf = new ConfigurationXML(userDir + fileSeparator + "ExecutionSys.XML"); //ファイルの位置は再考する
            // JOptionPane.showMessageDialog(null, "オープンした");

            this.jTextFieldSeq1.setText(conf.getProperty("jTextFieldSeq1"));
            this.jTextFieldSeq2.setText(conf.getProperty("jTextFieldSeq2"));
            this.jTextFieldSeq3.setText(conf.getProperty("jTextFieldSeq3"));
            this.jTextFieldSeq4.setText(conf.getProperty("jTextFieldSeq4"));
            this.jTextFieldSeq5.setText(conf.getProperty("jTextFieldSeq5"));
            this.jTextFieldSeq6.setText(conf.getProperty("jTextFieldSeq6"));
            this.jTextFieldSeq7.setText(conf.getProperty("jTextFieldSeq7"));
            this.jTextFieldSeq8.setText(conf.getProperty("jTextFieldSeq8"));
            this.jTextFieldSeq9.setText(conf.getProperty("jTextFieldSeq9"));
            this.jTextFieldSeq10.setText(conf.getProperty("jTextFieldSeq10"));

            this.jTextFieldSystemDirSeq1.setText(conf.getProperty("jTextFieldSystemDirSeq1"));
            this.jTextFieldSystemDirSeq2.setText(conf.getProperty("jTextFieldSystemDirSeq2"));
            this.jTextFieldSystemDirSeq3.setText(conf.getProperty("jTextFieldSystemDirSeq3"));
            this.jTextFieldSystemDirSeq4.setText(conf.getProperty("jTextFieldSystemDirSeq4"));
            this.jTextFieldSystemDirSeq5.setText(conf.getProperty("jTextFieldSystemDirSeq5"));
            this.jTextFieldSystemDirSeq6.setText(conf.getProperty("jTextFieldSystemDirSeq6"));
            this.jTextFieldSystemDirSeq7.setText(conf.getProperty("jTextFieldSystemDirSeq7"));
            this.jTextFieldSystemDirSeq8.setText(conf.getProperty("jTextFieldSystemDirSeq8"));
            this.jTextFieldSystemDirSeq9.setText(conf.getProperty("jTextFieldSystemDirSeq9"));
            this.jTextFieldSystemDirSeq10.setText(conf.getProperty("jTextFieldSystemDirSeq10"));
        } catch (Exception e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "オープンエラー" + e.toString());
        }
    }

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        System.out.println("Changed");
        System.out.println(jTabbedPane1.getSelectedIndex());
        if (jTabbedPane1.getSelectedIndex() == 4) { // 成果物の起動用システムのタブが選択された

        } else { // 成果物の起動用システムのタブ以外が選択されたら強制保存するべきだが方法不明
            //storeItems();
            // 上を行うと初期状態の何も表示されていないデータでファイルが上書きされてしまう。
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jPanelExeSysFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanelExeSysFocusGained

    }//GEN-LAST:event_jPanelExeSysFocusGained

    private void jPanelExeSysFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanelExeSysFocusLost

    }//GEN-LAST:event_jPanelExeSysFocusLost

    private void jPanelExeSysComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelExeSysComponentShown
        loadItems();
        this.isShowedSysPanel = true; // 一度でも表示されたか
    }//GEN-LAST:event_jPanelExeSysComponentShown

    private void jPanelExeSysComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelExeSysComponentHidden
        if (this.isShowedSysPanel == true) {
            storeItems(); // 一度でも表示されていればロードされている。
        }

    }//GEN-LAST:event_jPanelExeSysComponentHidden

    private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterActionPerformed
        storeItems();
    }//GEN-LAST:event_jButtonEnterActionPerformed

    /*
     ここからAppInfoの設定
    
     */
    public void deleteRegsys(String title) {
        String sql = "DELETE FROM regsys WHERE title = ?;";
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

    public void updateRegsys(
            String title,
            String sysdir,
            String extension,
            String remark,
            String preText,
            String sufText,
            String saveTime,
            String autoSaveMouseX,
            String autoSaveMouseY,
            String autoSaveKeyType) {
        String sql = "INSERT INTO  regsys VALUES (?,?,?,?,?,?,?,?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql);) {

            connection.setAutoCommit(false);

            statement.setString(1, title);
            statement.setString(2, sysdir);
            statement.setString(3, extension);
            statement.setString(4, remark);
            statement.setString(5, preText);
            statement.setString(6, sufText);
            statement.setString(7, saveTime);
            statement.setString(8, autoSaveMouseX);
            statement.setString(9, autoSaveMouseY);
            statement.setString(10, autoSaveKeyType);
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

    private void checkJCheckBoxNewState() {
        if (jCheckBoxNewState.isSelected()) {
            this.jTextFieldTitle.setEditable(true);
            this.jTextFieldTitle.setBackground(Color.white); // Enable にすると分かりにくいので
            this.jTextFieldSysdir.setEditable(true);
            this.jTextFieldExtension.setEditable(true);
            this.jTextAreaRemark.setEditable(true);
            this.jButtonEntry.setEnabled(true);
            this.jButtonDelete.setEnabled(false); // 新規ﾓｰﾄﾞで削除不許可
            this.jTextFieldPreText.setEditable(true);
            this.jTextFieldSufText.setEditable(true);
            this.jComboBoxAutoSaveTime.setEnabled(true);
            this.jTextFieldAutoSaveMouseX.setEditable(true);
            this.jTextFieldAutoSaveMouseY.setEditable(true);
            this.jTextFieldAutoSaveKeyType.setEditable(true);
        } else {
            this.jTextFieldTitle.setEditable(false);
            this.jTextFieldTitle.setBackground(Color.LIGHT_GRAY); // Enable にすると分かりにくいので
            this.jTextFieldSysdir.setEditable(false);
            this.jTextFieldExtension.setEditable(false);
            this.jTextAreaRemark.setEditable(false);
            this.jButtonEntry.setEnabled(false);
            this.jButtonDelete.setEnabled(true); // 非新規ﾓｰﾄﾞで削除可能へ
            this.jTextFieldPreText.setEditable(false);
            this.jTextFieldSufText.setEditable(false);
            this.jComboBoxAutoSaveTime.setEnabled(false);
            this.jTextFieldAutoSaveMouseX.setEditable(false);
            this.jTextFieldAutoSaveMouseY.setEditable(false);
            this.jTextFieldAutoSaveKeyType.setEditable(false);
        }
    }


    private void jButtonSysdirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSysdirActionPerformed
        // 各起動アプリの実行ファイルのディレクトリを抽出
        JFileChooser filechooserAppDir = new JFileChooser();
        filechooserAppDir.setDialogTitle("自動起動するアプリの実行ファイルを選択");
        int selected = filechooserAppDir.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File fileAppDir = filechooserAppDir.getSelectedFile();
            this.jTextFieldSysdir.setText(fileAppDir.getPath());
        }
    }//GEN-LAST:event_jButtonSysdirActionPerformed

    private ResultSet selectOneRegsys(String title) { // キーひとつでの検索
        try {
            //String sql = "SELECT * FROM regsys WHERE title = " + title;
            //Connection connectionSelectOneRegsys = createConnection();
            Connection connectionSelectOneRegsys = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connectionSelectOneRegsys.prepareStatement("select * from regsys where title = (?);"); // なぜか初期作動時に title = (?) になっていた。
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
            Logger.getLogger(ConnectionRegsys.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void updateJComboBoxItems() {
        // コネクションの作成
        try (Connection connectionUpdateJComboBoxItems = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                PreparedStatement statement = connectionUpdateJComboBoxItems.prepareStatement("select * from regsys;");) {
            //statement.setString(1, "%");
            ResultSet resultSet = statement.executeQuery();

            this.jListTitles.removeAll();
            appTitlesModel.removeAllElements();
            while (resultSet.next()) {
                String titleget = resultSet.getString("title");
                appTitlesModel.addElement(titleget);

                System.out.println("タイトル　" + titleget);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
            e.printStackTrace();
        }
    }

    private void jListTitlesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTitlesValueChanged
        // アイテムの選択が変更された場合の処理
        try {
            if (!this.jListTitles.getSelectedValue().toString().equals("")) { // NullPointerException が発生する。
                try {
                    // タイトルの選択が変わるに応じて内容を変化させる。
                    this.jTextFieldTitle.setText(
                            this.selectOneRegsys((this.jListTitles.getSelectedValue().toString())).getString("title")
                    );
                    this.jTextFieldSysdir.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("sysdir")
                    );
                    this.jTextFieldExtension.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("extension")
                    );
                    this.jTextAreaRemark.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("remark")
                    );
                    this.jTextFieldPreText.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("preText")
                    );
                    this.jTextFieldSufText.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("sufText")
                    );
                    this.jComboBoxAutoSaveTime.setSelectedItem(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("autosavetime")
                    );
                    this.jTextFieldAutoSaveMouseX.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("autosavemousex")
                    );
                    this.jTextFieldAutoSaveMouseY.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("autosavemousey")
                    );
                    this.jTextFieldAutoSaveKeyType.setText(
                            this.selectOneRegsys(this.jListTitles.getSelectedValue().toString()).getString("autosavekeytype")
                    );

                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionRegsys.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (Exception e) {
            System.out.println("空欄");
        }
    }//GEN-LAST:event_jListTitlesValueChanged

    private void jListTitlesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jListTitlesFocusGained
        // フォーカスが来たらリストを更新
        updateJComboBoxItems();
    }//GEN-LAST:event_jListTitlesFocusGained

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteRegsys(this.jListTitles.getSelectedValue().toString()); // 削除の空振りを防ぐため登録が確定しているコンボボックスを参照
        updateJComboBoxItems();

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jCheckBoxNewStateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxNewStateStateChanged
        checkJCheckBoxNewState();
    }//GEN-LAST:event_jCheckBoxNewStateStateChanged

    private void jButtonEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntryActionPerformed
        jButtonEntry.setEnabled(false); // 二重登録を抑止、ただしチェックボックスにフォーカスが行くと再び登録ボタンが有効になる。
        updateRegsys(
                this.jTextFieldTitle.getText(),
                this.jTextFieldSysdir.getText(),
                this.jTextFieldExtension.getText(),
                this.jTextAreaRemark.getText(),
                this.jTextFieldPreText.getText(),
                this.jTextFieldSufText.getText(),
                this.jComboBoxAutoSaveTime.getSelectedItem().toString(),
                this.jTextFieldAutoSaveMouseX.getText(),
                this.jTextFieldAutoSaveMouseY.getText(),
                this.jTextFieldAutoSaveKeyType.getText()
        );
        jCheckBoxNewState.setSelected(false); // 登録したら一旦非新規ﾓｰﾄﾞへ
        updateJComboBoxItems(); //登録後にコンボボックスを更新
    }//GEN-LAST:event_jButtonEntryActionPerformed

    private void jPanelAppInfoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAppInfoComponentShown
        // タブが表示されたらリストを更新
        updateJComboBoxItems();
        checkJCheckBoxNewState();
    }//GEN-LAST:event_jPanelAppInfoComponentShown

    private void jButtonEnterConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterConfigActionPerformed
        // Configファイルに内容を記録

        configuration.setProperty("dbUrl", this.jTextFieldDbUrl.getText());
        configuration.setProperty("dbUsername", this.jTextFieldDbUsername.getText());
        configuration.setProperty("dbPassword", this.jTextFieldDbPassword.getText());
        configuration.setProperty("pasteBuckupHome", this.jTextFieldPasteBuckupHome.getText());
        configuration.setProperty("multigenerationalBackupHome",
                this.jTextFieldMultigenerationalBackupHome.getText());
        /*
         configuration.setProperty("autoSaveTime", this.jComboBoxAutoSaveTime.getSelectedItem().toString());
         configuration.setProperty("autoSaveScript", this.jTextFieldAutoSaveScript.getText());
         configuration.setProperty("autoSaveMouseX", this.jTextFieldAutoSaveMouseX.getText());
         configuration.setProperty("autoSaveMouseY", this.jTextFieldAutoSaveMouseY.getText());
         */
        configuration.storeToXML(configFileName, new Date().toString()); // 即時ファイルに書込み
    }//GEN-LAST:event_jButtonEnterConfigActionPerformed

    private void jPanelConfigComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelConfigComponentShown
        // パネルが表示されるごとにConfig内容をファイルから反映
        // Configファイルから内容を表示
        this.jTextFieldDbUrl.setText(configuration.getProperty("dbUrl"));
        this.jTextFieldDbUsername.setText(configuration.getProperty("dbUsername"));
        this.jTextFieldDbPassword.setText(configuration.getProperty("dbPassword"));
        this.jTextFieldPasteBuckupHome.setText(configuration.getProperty("pasteBuckupHome"));
        this.jTextFieldMultigenerationalBackupHome.setText(configuration.getProperty("multigenerationalBackupHome"));
    }//GEN-LAST:event_jPanelConfigComponentShown

    private void jButtonShipEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShipEnterActionPerformed
        // 事前処理（SHIPコードをすべて半角の大文字へ）
        jTextFieldShipCode.setText(jTextFieldShipCode.getText().toUpperCase());

        // 入力値検査（将来もっと厳格にする、ただし一部はDBに委託）
        if (this.jTextFieldShipCode.getText().trim().length() != SHIP_CODE_LENGTH) {
            JOptionPane.showMessageDialog(rootPane, "SHIPコードの桁数が不正です" + this.lineSeparator + "（修正後はコード競合に注意が必要です）");
            jTextFieldShipCode.requestFocusInWindow();
            return;
        }
        
        // Shipテーブルに書き込みもしくは上書き
        try (Connection connectionShip = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                PreparedStatement statementShip = connectionShip.prepareStatement("select * from ship where  code = ?;");) {
            statementShip.setString(1, this.jTextFieldShipCode.getText().trim());
            statementShip.addBatch();
            ResultSet resultSetShip = statementShip.executeQuery();
            resultSetShip.last();
            int resultSetShipCount = resultSetShip.getRow();
            resultSetShip.close();
            int flg;
            if (resultSetShipCount > 0) { // すでにCODEキーが存在（更新）する場合
                flg = JOptionPane.showConfirmDialog(null, "すでにある内容を更新しますか。", "更新の確認", JOptionPane.OK_CANCEL_OPTION);
                if (flg == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "キャンセルしました。"); // キャンセル
                } else { // 非キャンセル
                    // 更新処理
                    try (Connection connectionShipUpdate = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                            PreparedStatement statementShipUpdate = connectionShipUpdate.prepareStatement(
                                    "UPDATE ship SET code = ?, nameo = ?, namey = ?, remark = ? where  code = ?;");) {
                        connectionShipUpdate.setAutoCommit(false);
                        statementShipUpdate.setString(1, this.jTextFieldShipCode.getText().trim());
                        statementShipUpdate.setString(2, this.jTextFieldShipNameO.getText().trim());
                        statementShipUpdate.setString(3, this.jTextFieldShipNameY.getText().trim());
                        statementShipUpdate.setString(4, this.jTextAreaShipRemark.getText());
                        statementShipUpdate.setString(5, this.jTextFieldShipCode.getText().trim()); // where 条件
                        statementShipUpdate.addBatch();
                        int[] result = statementShipUpdate.executeBatch();
                        System.out.println("登録：" + result.length + "件");
                        try {
                            connectionShipUpdate.commit();
                            System.out.println("登録成功");
                            jLabelShipEntryCondition.setText(null);
                            jTextFieldShipCode.setEditable(true); // コードを変更できるようにロック解除
                            jTextFieldShipCode.setBackground(Color.WHITE); // 色も変える
                            this.jTextFieldShipCode.setText(null);
                            this.jTextFieldShipNameO.setText(null);
                            this.jTextFieldShipNameY.setText(null);
                            this.jTextAreaShipRemark.setText(null);
                            this.jButtonShipDelete.setEnabled(false); // 削除ボタンを無効化
                            this.jButtonShipSelectWhereShipCode.setEnabled(true); // 照会ボタンを有効化
                        } catch (SQLException e) {
                            connectionShipUpdate.rollback();
                            System.out.println("登録失敗：ロールバック実行");
                            e.printStackTrace();
                        }
                    }
                }
            } else { // CODEキーが存在しない（新規）の場合
                flg = JOptionPane.showConfirmDialog(null, "新規で登録しますか。", "新規登録の確認", JOptionPane.OK_CANCEL_OPTION);
                if (flg == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "キャンセルしました。");
                } else {
                    //新規（挿入）処理
                    try (Connection connectionShipInsert = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                            PreparedStatement statementShipInsert = connectionShipInsert.prepareStatement("INSERT INTO ship VALUES (?,?,?,?);");) {
                        connectionShipInsert.setAutoCommit(false);
                        statementShipInsert.setString(1, this.jTextFieldShipCode.getText().trim());
                        statementShipInsert.setString(2, this.jTextFieldShipNameO.getText().trim());
                        statementShipInsert.setString(3, this.jTextFieldShipNameY.getText().trim());
                        statementShipInsert.setString(4, this.jTextAreaShipRemark.getText());
                        statementShipInsert.addBatch();
                        int[] result = statementShipInsert.executeBatch();
                        System.out.println("登録：" + result.length + "件");
                        try {
                            connectionShipInsert.commit();
                            System.out.println("登録成功");
                            jLabelShipEntryCondition.setText(null);
                            jTextFieldShipCode.setEditable(true); // コードを変更できるようにロック解除
                            jTextFieldShipCode.setBackground(Color.WHITE); // 色も変える
                            this.jTextFieldShipCode.setText(null);
                            this.jTextFieldShipNameO.setText(null);
                            this.jTextFieldShipNameY.setText(null);
                            this.jTextAreaShipRemark.setText(null);
                            this.jButtonShipDelete.setEnabled(false); // 削除ボタンを無効化
                            this.jButtonShipSelectWhereShipCode.setEnabled(true); // 照会ボタンを有効化
                        } catch (SQLException e) {
                            connectionShipInsert.rollback();
                            System.out.println("登録失敗：ロールバック実行");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonShipEnterActionPerformed

    private void jTextFieldShipCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldShipCodeFocusLost
        if (this.jTextFieldShipCode.getText().trim().length() != SHIP_CODE_LENGTH) {
            //this.jLabelShipEntryCondition.setText("形式不正");
            //jTextFieldShipCode.requestFocusInWindow();
            return; // SHIP_CODE_LENGTH以外の文字数での検索処理を回避
        }
        // 登録がある場合は内容を呼び出す
        try (Connection connectionShip = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statementShip = connectionShip.prepareStatement(
                        "select * from ship where  code = ?;");) { // 大文字小文字を区別
            statementShip.setString(1, this.jTextFieldShipCode.getText().trim());
            statementShip.addBatch();
            ResultSet resultSetShip = statementShip.executeQuery();
            resultSetShip.last();
            int resultSetShipCount = resultSetShip.getRow();
            if (resultSetShipCount == 0) { // 登録がなかった場合
                resultSetShip.close();
                //JOptionPane.showMessageDialog(null, "コードの実績がありません。", "新規", JOptionPane.INFORMATION_MESSAGE);
                jLabelShipEntryCondition.setText("新規");
                jTextFieldShipCode.setEditable(false); // コードを変更できないようにロック
                jTextFieldShipCode.setBackground(Color.LIGHT_GRAY);
                this.jTextFieldShipNameO.setText(null);
                this.jTextFieldShipNameY.setText(null);
                this.jTextAreaShipRemark.setText(null);
                this.jButtonShipDelete.setEnabled(false); // 削除ボタンを無効化
                this.jButtonShipSelectWhereShipCode.setEnabled(false); // 照会ボタンを無効化
            } else { // 登録があった場合
                //JOptionPane.showMessageDialog(null, "変更入力です。", "変更", JOptionPane.PLAIN_MESSAGE);
                jLabelShipEntryCondition.setText("既存変更");
                jTextFieldShipCode.setEditable(false); // コードを変更できないようにロック
                jTextFieldShipCode.setBackground(Color.LIGHT_GRAY);
                resultSetShip.first();
                this.jTextFieldShipNameO.setText(resultSetShip.getString("nameo"));
                this.jTextFieldShipNameY.setText(resultSetShip.getString("namey"));
                this.jTextAreaShipRemark.setText(resultSetShip.getString("remark"));
                this.jButtonShipDelete.setEnabled(true); // 削除ボタンを有効化
                this.jButtonShipSelectWhereShipCode.setEnabled(false); // 照会ボタンを無効化
                resultSetShip.close();
            }
        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
            e.printStackTrace();
        }

    }//GEN-LAST:event_jTextFieldShipCodeFocusLost

    private void jButtonShipSelectWhereShipCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShipSelectWhereShipCodeActionPerformed
        // 中間一致・複数条件での検索　暫定実装
        // ミスタイプを防ぐため、バーコードが印刷された紙の台帳を基準とする為、
        // 検索の利便性は慎重に考える。
        try (Connection connectionShip = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statementShip = connectionShip.prepareStatement(
                        "select * from ship where  "
                        + "nameo LIKE CONCAT('%',?,'%')"
                        + "AND namey LIKE CONCAT('%',?,'%')"
                        + "AND remark LIKE CONCAT('%',?,'%');");) {
            statementShip.setString(1, this.jTextFieldShipNameO.getText().trim());
            statementShip.setString(2, this.jTextFieldShipNameY.getText().trim());
            statementShip.setString(3, this.jTextAreaShipRemark.getText().trim());
            statementShip.addBatch();
            ResultSet resultSetShip = statementShip.executeQuery();
            resultSetShip.last();
            int resultSetShipCount = resultSetShip.getRow();
            if (resultSetShipCount == 0) { // 登録がなかった場合
                resultSetShip.close();
                JOptionPane.showMessageDialog(null, "なにも見つけられませんでした。", "検索結果（０件）", JOptionPane.INFORMATION_MESSAGE);
                //this.jTextFieldShipCode.setText(null); 
                //this.jTextFieldShipNameO.setText(null);
                //this.jTextFieldShipNameY.setText(null);
                //this.jTextAreaShipRemark.setText(null);
            } else if (resultSetShipCount == 1) { // 登録が一件だけあった場合
                JOptionPane.showMessageDialog(null, "特定しました。", "検索結果（１件）", JOptionPane.PLAIN_MESSAGE);
                this.jLabelShipEntryCondition.setText("既存変更（検索経由）");
                jTextFieldShipCode.setEditable(false); // コードを変更できないようにロック
                jButtonShipSelectWhereShipCode.setEnabled(false); // 行き当たった場合はもう検索させない。
                jButtonShipDelete.setEnabled(true); // 削除を可能とさせる。
                jTextFieldShipCode.setBackground(Color.LIGHT_GRAY);
                resultSetShip.first();
                this.jTextFieldShipCode.setText(resultSetShip.getString("code"));
                this.jTextFieldShipNameO.setText(resultSetShip.getString("nameo"));
                this.jTextFieldShipNameY.setText(resultSetShip.getString("namey"));
                this.jTextAreaShipRemark.setText(resultSetShip.getString("remark"));
                resultSetShip.close();

            } else if (resultSetShipCount > 1) { // 複数件の登録があった場合
                JOptionPane.showMessageDialog(null, "絞り込むことができませんでした、検索条件を見なおしてください。", "検索結果（複数件）", JOptionPane.PLAIN_MESSAGE);
                //this.jTextFieldShipCode.setText(null); 
                //this.jTextFieldShipNameO.setText(null);
                //this.jTextFieldShipNameY.setText(null);
                //this.jTextAreaShipRemark.setText(null);
            }
        } catch (SQLException e) {
            System.out.println("エラーが発生しました");
            JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonShipSelectWhereShipCodeActionPerformed

    private void jButtonShipCodeCleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShipCodeCleraActionPerformed
        // シップコードをクリアし入力可能状態とする
        jLabelShipEntryCondition.setText(null);
        jTextFieldShipCode.setEditable(true); // コードを変更できるようにロック解除
        jTextFieldShipCode.setBackground(Color.WHITE); // 色も変える
        this.jTextFieldShipCode.setText(null);
        this.jTextFieldShipNameO.setText(null);
        this.jTextFieldShipNameY.setText(null);
        this.jTextAreaShipRemark.setText(null);
        this.jButtonShipDelete.setEnabled(false); // 削除ボタンを無効化
        this.jButtonShipSelectWhereShipCode.setEnabled(true); // 照会ボタンを有効化

    }//GEN-LAST:event_jButtonShipCodeCleraActionPerformed

    private void jButtonShipDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShipDeleteActionPerformed
        if (JOptionPane.showConfirmDialog(
                null,
                this.jTextFieldShipCode.getText().trim() + "を削除しますか。",
                "警告",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE)
                == JOptionPane.YES_OPTION) {
            if (JOptionPane.showConfirmDialog(
                    null,
                    "本当に" + this.jTextFieldShipCode.getText().trim() + "を削除しますか。",
                    "最終警告",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                // 削除処理
                try (Connection connectionShipUpdate = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connection.setAutoCommit(false);
                        PreparedStatement statementShipUpdate = connectionShipUpdate.prepareStatement(
                                "DELETE FROM ship WHERE  code = ?;");) {
                    connectionShipUpdate.setAutoCommit(false);
                    statementShipUpdate.setString(1, this.jTextFieldShipCode.getText().trim()); // 削除条件
                    statementShipUpdate.addBatch();
                    int[] result = statementShipUpdate.executeBatch();
                    System.out.println("削除：" + result.length + "件");
                    try {
                        connectionShipUpdate.commit();
                        System.out.println("削除成功");
                        jLabelShipEntryCondition.setText(null);
                        jTextFieldShipCode.setEditable(true); // コードを変更できるようにロック解除
                        jTextFieldShipCode.setBackground(Color.WHITE); // 色も変える
                        this.jTextFieldShipCode.setText(null);
                        this.jTextFieldShipNameO.setText(null);
                        this.jTextFieldShipNameY.setText(null);
                        this.jTextAreaShipRemark.setText(null);
                        this.jButtonShipDelete.setEnabled(false); // 削除ボタンを無効化
                        this.jButtonShipSelectWhereShipCode.setEnabled(true); // 照会ボタンを有効化
                    } catch (SQLException e) {
                        connectionShipUpdate.rollback();
                        System.out.println("登録失敗：ロールバック実行");
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    System.out.println("エラーが発生しました");
                    JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました");
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButtonShipDeleteActionPerformed

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1ComponentShown

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
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        try { // 
            if (args[0].isEmpty() || args[0].length() == 0) {
                JOptionPane.showMessageDialog(null, "args[0](設定ファイル名)が空です。");
                System.exit(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "不正な起動です、起動スクリプトを確認して下さい。");
            System.exit(0);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame(args[0]).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCat1;
    private javax.swing.ButtonGroup buttonGroupCat2;
    private javax.swing.ButtonGroup buttonGroupCat3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonCatDir1;
    private javax.swing.JButton jButtonCatDir2;
    private javax.swing.JButton jButtonCatDir3;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeleteItems;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JButton jButtonEnterConfig;
    private javax.swing.JButton jButtonEnterItems;
    private javax.swing.JButton jButtonEntry;
    private javax.swing.JButton jButtonIssueDir;
    private javax.swing.JButton jButtonSetDefaultItems;
    private javax.swing.JButton jButtonShipCodeClera;
    private javax.swing.JButton jButtonShipDelete;
    private javax.swing.JButton jButtonShipEnter;
    private javax.swing.JButton jButtonShipSelectWhereShipCode;
    private javax.swing.JButton jButtonSourceDir;
    private javax.swing.JButton jButtonSysdir;
    private javax.swing.JButton jButtonSystemDir;
    private javax.swing.JButton jButtonSystemDirSeq1;
    private javax.swing.JButton jButtonSystemDirSeq10;
    private javax.swing.JButton jButtonSystemDirSeq2;
    private javax.swing.JButton jButtonSystemDirSeq3;
    private javax.swing.JButton jButtonSystemDirSeq4;
    private javax.swing.JButton jButtonSystemDirSeq5;
    private javax.swing.JButton jButtonSystemDirSeq6;
    private javax.swing.JButton jButtonSystemDirSeq7;
    private javax.swing.JButton jButtonSystemDirSeq8;
    private javax.swing.JButton jButtonSystemDirSeq9;
    private javax.swing.JButton jButtonSystemInvoke;
    private javax.swing.JButton jButtonWriteCat1;
    private javax.swing.JButton jButtonWriteCat2;
    private javax.swing.JButton jButtonWriteCat3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBoxNewState;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBoxAutoSaveTime;
    private javax.swing.JComboBox jComboBoxMakeCode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelSeq1;
    private javax.swing.JLabel jLabelSeq10;
    private javax.swing.JLabel jLabelSeq11;
    private javax.swing.JLabel jLabelSeq12;
    private javax.swing.JLabel jLabelSeq4;
    private javax.swing.JLabel jLabelSeq5;
    private javax.swing.JLabel jLabelSeq6;
    private javax.swing.JLabel jLabelSeq7;
    private javax.swing.JLabel jLabelSeq8;
    private javax.swing.JLabel jLabelSeq9;
    private javax.swing.JLabel jLabelShipEntryCondition;
    private javax.swing.JList jListTitles;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAppInfo;
    private javax.swing.JPanel jPanelConfig;
    private javax.swing.JPanel jPanelExeSys;
    private javax.swing.JRadioButton jRadioButtonSaveCat1Fork;
    private javax.swing.JRadioButton jRadioButtonSaveCat1Paste;
    private javax.swing.JRadioButton jRadioButtonSaveCat2Fork;
    private javax.swing.JRadioButton jRadioButtonSaveCat2Paste;
    private javax.swing.JRadioButton jRadioButtonSaveCat3Fork;
    private javax.swing.JRadioButton jRadioButtonSaveCat3Paste;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaRedirectErrorStream;
    private javax.swing.JTextArea jTextAreaRemark;
    private javax.swing.JTextArea jTextAreaShipRemark;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextFieldAutoSaveKeyType;
    private javax.swing.JTextField jTextFieldAutoSaveMouseX;
    private javax.swing.JTextField jTextFieldAutoSaveMouseY;
    private javax.swing.JTextField jTextFieldCopyTarget1;
    private javax.swing.JTextField jTextFieldCopyTarget2;
    private javax.swing.JTextField jTextFieldCopyTarget3;
    private javax.swing.JTextField jTextFieldDbPassword;
    private javax.swing.JTextField jTextFieldDbUrl;
    private javax.swing.JTextField jTextFieldDbUsername;
    private javax.swing.JTextField jTextFieldExtension;
    private javax.swing.JTextField jTextFieldIssueDir;
    private javax.swing.JTextField jTextFieldMultigenerationalBackupHome;
    private javax.swing.JTextField jTextFieldPasteBuckupHome;
    private javax.swing.JTextField jTextFieldPreText;
    private javax.swing.JTextField jTextFieldSeq1;
    private javax.swing.JTextField jTextFieldSeq10;
    private javax.swing.JTextField jTextFieldSeq2;
    private javax.swing.JTextField jTextFieldSeq3;
    private javax.swing.JTextField jTextFieldSeq4;
    private javax.swing.JTextField jTextFieldSeq5;
    private javax.swing.JTextField jTextFieldSeq6;
    private javax.swing.JTextField jTextFieldSeq7;
    private javax.swing.JTextField jTextFieldSeq8;
    private javax.swing.JTextField jTextFieldSeq9;
    private javax.swing.JTextField jTextFieldShipAndVoy;
    private javax.swing.JTextField jTextFieldShipCode;
    private javax.swing.JTextField jTextFieldShipNameO;
    private javax.swing.JTextField jTextFieldShipNameY;
    private javax.swing.JTextField jTextFieldSourceDir;
    private javax.swing.JTextField jTextFieldSufText;
    private javax.swing.JTextField jTextFieldSysdir;
    private javax.swing.JTextField jTextFieldSystemDir;
    private javax.swing.JTextField jTextFieldSystemDirSeq1;
    private javax.swing.JTextField jTextFieldSystemDirSeq10;
    private javax.swing.JTextField jTextFieldSystemDirSeq2;
    private javax.swing.JTextField jTextFieldSystemDirSeq3;
    private javax.swing.JTextField jTextFieldSystemDirSeq4;
    private javax.swing.JTextField jTextFieldSystemDirSeq5;
    private javax.swing.JTextField jTextFieldSystemDirSeq6;
    private javax.swing.JTextField jTextFieldSystemDirSeq7;
    private javax.swing.JTextField jTextFieldSystemDirSeq8;
    private javax.swing.JTextField jTextFieldSystemDirSeq9;
    private javax.swing.JTextField jTextFieldTitle;
    // End of variables declaration//GEN-END:variables

}
