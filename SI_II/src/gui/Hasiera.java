package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Hasiera extends JFrame {

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

					MainWindow.hasiera.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Hasiera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JButton btnHasiSaioa = new JButton("Hasi Saioa");
		btnHasiSaioa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				MainWindow.login = new Login();
				MainWindow.login.setVisible(true);

			}
		});
		btnHasiSaioa.setBounds(303, 13, 117, 25);
		btnHasiSaioa.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnHasiSaioa);

		JButton btnEtxeenZerrenda = new JButton("Landetxeen zerrenda");
		btnEtxeenZerrenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.rankingean=false;
				MainWindow.etxeenZerrenda = new EtxeZerrenda();
				MainWindow.etxeenZerrenda.setVisible(true);
			}
		});
		btnEtxeenZerrenda.setBounds(22, 92, 192, 41);
		contentPane.add(btnEtxeenZerrenda);
		
		JButton btnRankingaIkusi = new JButton("Ranking-a Ikusi");
		btnRankingaIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.rankingean=true;
				MainWindow.rankingaIkusi=new RankingaIkusi();
				MainWindow.rankingaIkusi.setVisible(true);
				
			}
		});
		btnRankingaIkusi.setBounds(22, 145, 192, 41);
		contentPane.add(btnRankingaIkusi);
	}
}
