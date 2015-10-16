package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MiniTests extends JDialog {

	private Properties conf;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_value;
	private final JButton btnNewButton = new JButton("Write");
	private JTextField textField_key;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MiniTests dialog = new MiniTests();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MiniTests() {
		
		//プロパティーファイルの初期化
		conf = new Properties();
		
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textField_value = new JTextField();
			contentPanel.add(textField_value, BorderLayout.CENTER);
			textField_value.setColumns(10);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conf.setProperty(textField_key.getText(), textField_value.getText());
			}
		});
		contentPanel.add(btnNewButton, BorderLayout.EAST);
		{
			textField_key = new JTextField();
			contentPanel.add(textField_key, BorderLayout.WEST);
			textField_key.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRead = new JButton("read");
				btnRead.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField_value.setText(conf.getProperty(textField_key.getText()));
					}
				});
				buttonPane.add(btnRead);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ProcessBuilder pb = new ProcessBuilder();
						
						pb.command(textField_key.getText(), textField_value.getText());
						
						try {
							Process process = pb.start();
							System.out.println("起動中");
							process.waitFor();
							System.out.println("Destry");
						} catch (IOException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						}
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
