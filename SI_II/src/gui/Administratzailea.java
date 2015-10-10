package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Administratzailea extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow.adm.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Administratzailea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIrten = new JButton("Irten");
		btnIrten.setBackground(Color.LIGHT_GRAY);
		btnIrten.setForeground(SystemColor.activeCaptionText);
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.adm.dispose();
				MainWindow.hasiera.setVisible(true);
			}
		});
		btnIrten.setBounds(311, 12, 117, 25);
		contentPane.add(btnIrten);
		
		JButton btnJabeaEzabatu = new JButton("Erabiltzaile ezabatu");
		btnJabeaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.adminJabeaEzabatu=new AdminJabeaEzabatu();
				MainWindow.adminJabeaEzabatu.setVisible(true);
			}
		});
		btnJabeaEzabatu.setBounds(36, 64, 191, 25);
		contentPane.add(btnJabeaEzabatu);
		
		JButton btnNewButton = new JButton("Landetxea Ezabatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.AdminLandetxeaEzabatu = new AdminLandetxeaEzabatu();
				MainWindow.AdminLandetxeaEzabatu.setVisible(true);
			}
		});
		btnNewButton.setBounds(36, 101, 191, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salaketak Ikusi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.salaketakIkusi= new SalaketakIkusi();
				MainWindow.salaketakIkusi.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(36, 138, 191, 25);
		contentPane.add(btnNewButton_1);
	}
}
