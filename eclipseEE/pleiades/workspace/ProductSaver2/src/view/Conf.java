package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Conf extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtDisst;
	private JTextField textField_Dist1;
	private JTextField txtSys;
	private JTextField textField_SysPath;
	private JTextField txtFile;
	private JTextField textField_File;
	private JTextField txtMh;
	private JTextField textField_MaxHold;
	private JTextField textField_Title;

	private ButtonGroup buttonGroupSaveFileType; //
	private JRadioButton[] fileType ;
	private JCheckBox chckbxSaveBefore;
	private JCheckBox chckbxSaveAfter;
	private Properties divConf; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Conf dialog = new Conf();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Conf() {
		
		//プロパティーファイルの初期化
		divConf = new Properties();
		
		//InputStream is = this.getClass().getResourceAsStream("divConf.properties");
		FileInputStream is;
		try {
			is = new FileInputStream("divConf.properties");
			divConf.load(is);
		} catch (IOException e3) {
			// TODO 自動生成された catch ブロック
			e3.printStackTrace();
		}

		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setText("Title");
		txtTitle.setBounds(12, 10, 49, 19);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		final JComboBox<String> comboBox_Number = new JComboBox<String>();
		comboBox_Number.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try{
				textField_Title.setText(divConf.getProperty((String)comboBox_Number.getSelectedItem()+":Title"));
				textField_SysPath.setText(divConf.getProperty((String) comboBox_Number.getSelectedItem()+":SysPath"));
				textField_File.setText(divConf.getProperty((String) comboBox_Number.getSelectedItem()+":File"));
				textField_Dist1.setText((String)divConf.getProperty(comboBox_Number.getSelectedItem()+":Dist1"));
				fileType[Integer.valueOf(divConf.getProperty(comboBox_Number.getSelectedItem()+":FileType"))].setSelected(true);
				chckbxSaveBefore.setSelected(Boolean.valueOf(divConf.getProperty(comboBox_Number.getSelectedItem()+":SaveBefore")));
				chckbxSaveAfter.setSelected(Boolean.valueOf(divConf.getProperty(comboBox_Number.getSelectedItem()+":SaveAfter")));
				
				textField_MaxHold.setText((String)divConf.getProperty(comboBox_Number.getSelectedItem()+":MaxHold"));
				}catch(Exception eSelect){
					System.out.println("白紙選択");
				}

			}
		});
		
		comboBox_Number.setModel(new DefaultComboBoxModel(new String[] {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"}));
		comboBox_Number.setBounds(73, 10, 35, 19);
		contentPanel.add(comboBox_Number);
		
		txtDisst = new JTextField();
		txtDisst.setText("Dist1");
		txtDisst.setBounds(12, 127, 49, 19);
		contentPanel.add(txtDisst);
		txtDisst.setColumns(10);
		
		textField_Dist1 = new JTextField();
		textField_Dist1.setBounds(73, 127, 294, 19);
		contentPanel.add(textField_Dist1);
		textField_Dist1.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooserDist1 = new JFileChooser("C:");
				filechooserDist1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int selected = filechooserDist1.showOpenDialog(null);
			    textField_Dist1.setText(filechooserDist1.getSelectedFile().getPath());
			}
		});
		button.setBounds(378, 127, 44, 21);
		contentPanel.add(button);
		fileType = new JRadioButton[3];
		fileType[0] = new JRadioButton("Nomal");
		fileType[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		fileType[0].setBounds(70, 152, 60, 21);
		contentPanel.add(fileType[0]);
		
		fileType[1] = new JRadioButton("ZIP");
		fileType[1].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		fileType[1].setBounds(140, 152, 60, 21);
		contentPanel.add(fileType[1]);
		fileType[1].setSelected(true);
		
		fileType[2] = new JRadioButton("ENC-ZIP");
		fileType[2].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});
		fileType[2].setBounds(210, 152, 60, 21);
		contentPanel.add(fileType[2]);
		
		chckbxSaveBefore = new JCheckBox("SaveOnBefore");
		chckbxSaveBefore.setBounds(73, 175, 103, 21);
		contentPanel.add(chckbxSaveBefore);
		
		chckbxSaveAfter = new JCheckBox("SaveOnAfter");
		chckbxSaveAfter.setBounds(195, 175, 103, 21);
		contentPanel.add(chckbxSaveAfter);
		
		buttonGroupSaveFileType = new ButtonGroup();
		buttonGroupSaveFileType.add(fileType[0]);
		buttonGroupSaveFileType.add(fileType[1]);
		buttonGroupSaveFileType.add(fileType[2]);
		
		
		txtSys = new JTextField();
		txtSys.setText("SYS");
		txtSys.setColumns(10);
		txtSys.setBounds(12, 39, 49, 19);
		contentPanel.add(txtSys);
		
		textField_SysPath = new JTextField();
		textField_SysPath.setColumns(10);
		textField_SysPath.setBounds(73, 39, 294, 19);
		contentPanel.add(textField_SysPath);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    JFileChooser filechooserSys = new JFileChooser("C:");
			    int selected = filechooserSys.showOpenDialog(null);
			    textField_SysPath.setText(filechooserSys.getSelectedFile().getPath());
			}
		});
		button_1.setBounds(378, 39, 44, 21);
		contentPanel.add(button_1);
		
		txtFile = new JTextField();
		txtFile.setText("File");
		txtFile.setColumns(10);
		txtFile.setBounds(12, 68, 49, 19);
		contentPanel.add(txtFile);
		
		textField_File = new JTextField();
		textField_File.setColumns(10);
		textField_File.setBounds(73, 68, 294, 19);
		contentPanel.add(textField_File);
		
		JButton button_2 = new JButton("...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooserFile = new JFileChooser("C:");
			    int selected = filechooserFile.showOpenDialog(null);
			    textField_File.setText(filechooserFile.getSelectedFile().getPath());
			}
		});
		button_2.setBounds(378, 68, 44, 21);
		contentPanel.add(button_2);
		
		txtMh = new JTextField();
		txtMh.setText("M/H");
		txtMh.setBounds(293, 176, 32, 19);
		contentPanel.add(txtMh);
		txtMh.setColumns(10);
		
		textField_MaxHold = new JTextField();
		textField_MaxHold.setToolTipText("MAX HOLD (最大保持回数。空欄で無制限）");
		textField_MaxHold.setBounds(326, 176, 96, 19);
		contentPanel.add(textField_MaxHold);
		textField_MaxHold.setColumns(10);
		
		textField_Title = new JTextField();
		textField_Title.setBounds(120, 10, 302, 19);
		contentPanel.add(textField_Title);
		textField_Title.setColumns(10);
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					int selectedFileType;
					public void actionPerformed(ActionEvent e) {
						
						for(int i=0;i<3;i++){
							if(fileType[i].isSelected()){
								selectedFileType = i;
							}
						}
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":Title", textField_Title.getText());
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":SysPath", textField_SysPath.getText());
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":File", textField_File.getText());
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":Dist1", textField_Dist1.getText());
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":FileType", Integer.toString(selectedFileType));
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":SaveBefore", Boolean.toString(chckbxSaveBefore.isSelected()));
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":SaveAfter", Boolean.toString(chckbxSaveAfter.isSelected()));
						divConf.setProperty((String) comboBox_Number.getSelectedItem()+":MaxHold", textField_MaxHold.getText());
						
						FileOutputStream fos;
						try {
							fos = new FileOutputStream(new File("divConf.properties"));
							divConf.store(fos, "DivisionConfig");
							fos.close();
						} catch (IOException e2) {
							// TODO 自動生成された catch ブロック
							e2.printStackTrace();
						}
						dispose();
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
