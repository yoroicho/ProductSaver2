package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InvokerTest extends JFrame {
	MainPanel mp;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvokerTest frame = new InvokerTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InvokerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnInvokemain = new JButton("InvokeMain");
		btnInvokemain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp = new MainPanel();
				mp.invoke();
				//mp.createAndShowGUI();
				//mp.countSet("100");
				//mp.countDown(mp);
			}
		});
		contentPane.add(btnInvokemain, BorderLayout.NORTH);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.countSet("100");
				mp.countDown();
			}
		});
		contentPane.add(btnStart, BorderLayout.SOUTH);
	}

}
