package gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HasieraLogin extends JFrame {

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
					
					MainWindow.loginHasiera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HasieraLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		
		JButton btnSaioaItxi = new JButton("Saioa Itxi");
		btnSaioaItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				MainWindow.hasiera.setVisible(true);
				MainWindow.loginHasiera.setVisible(false);
				MainWindow.KonektatuaTurista = null;
				
			}
		});
		btnSaioaItxi.setBounds(319, 12, 117, 25);
		btnSaioaItxi.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnSaioaItxi);
		
		JButton btnEtxeenZerrenda = new JButton("Landetxeen Zerrenda");
		btnEtxeenZerrenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.rankingean=false;
				MainWindow.etxeenZerrenda= new EtxeZerrenda();
				MainWindow.etxeenZerrenda.setVisible(true); 
	
			}
		});
		btnEtxeenZerrenda.setBounds(53, 82, 230, 46);
		contentPane.add(btnEtxeenZerrenda);
		
		JButton btnNewButton = new JButton("Nire Iruzkinak");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.rankingean=false;
				MainWindow.erabiltzaileakIruzkinakEzabatu=new ErabiltzaileakIruzkinakEzabatu();
				MainWindow.erabiltzaileakIruzkinakEzabatu.setVisible(true);
			}
		});
		btnNewButton.setBounds(53, 140, 230, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ranking-a");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.rankingean=true;
				MainWindow.rankingaIkusi=new RankingaIkusi();
				MainWindow.rankingaIkusi.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(53, 198, 230, 54);
		contentPane.add(btnNewButton_1);
	}
	
}
