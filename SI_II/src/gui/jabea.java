package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class jabea extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLandetxeakEditatu;
	private JButton btnOfertaGehitu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow.jabea.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jabea() {
		setTitle(MainWindow.KonektatuaOwner.getLogin());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLandetxeaGehitu = new JButton("Landetxea Gehitu");
		btnLandetxeaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.landetxeaSortu= new LandetxeaSortu();
				MainWindow.landetxeaSortu.setVisible(true);
				
			}
		});
		btnLandetxeaGehitu.setBounds(30, 82, 186, 25);
		contentPane.add(btnLandetxeaGehitu);
		
		JButton btnIrten = new JButton("Irten");
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow.jabea.dispose();
				MainWindow.hasiera.setVisible(true);
			}
		});
		btnIrten.setBounds(362, 12, 74, 25);
		contentPane.add(btnIrten);
		
		btnLandetxeakEditatu = new JButton("Landetxeak editatu");
		btnLandetxeakEditatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//MainWindow.jabea.dispose();
				MainWindow.jabeEtxeak = new JabearenEtxeZerrenda();
				MainWindow.jabeEtxeak.setVisible(true);
				
			}
		});
		btnLandetxeakEditatu.setBounds(29, 120, 187, 25);
		contentPane.add(btnLandetxeakEditatu);
		
		btnOfertaGehitu = new JButton("Oferta(k) gehitu");
		btnOfertaGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.OfertaGehitu = new SetAvailability2GUI(MainWindow.KonektatuaOwner.getRuralHouses());
				MainWindow.OfertaGehitu.setVisible(true);
				
				
			}
		});
		btnOfertaGehitu.setBounds(30, 158, 186, 25);
		contentPane.add(btnOfertaGehitu);
		
		JButton btnNewButton = new JButton("Iruzkinak Salatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainWindow.iruzkinaSalatu=new IruzkinaSalatu();
				MainWindow.iruzkinaSalatu.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(30, 195, 186, 25);
		contentPane.add(btnNewButton);
	}
	public JButton getBtnLandetxeakEditatu() {
		return btnLandetxeakEditatu;
	}
	public JButton getBtnOfertaGehitu() {
		return btnOfertaGehitu;
	}
}
