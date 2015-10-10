package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JRadioButton;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblPasahitza;
	private JButton btnItzuli;
	private JButton btnKontuaSortu;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnTuristaBezala;
	private JRadioButton rdbtnJabeBezala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.login.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setBounds(100, 100, 434, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(144, 12, 160, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(144, 43, 160, 19);
		contentPane.add(passwordField);

		JLabel lblErabiltzaile = new JLabel("Erabiltzailea");
		lblErabiltzaile.setBounds(36, 14, 90, 15);
		contentPane.add(lblErabiltzaile);

		lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(46, 45, 90, 15);
		contentPane.add(lblPasahitza);

		JButton btnHasiSaioa = new JButton("Hasi saioa");
		btnHasiSaioa.setBounds(61, 90, 114, 19);
		btnHasiSaioa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String erab = textField.getText();
				String pasa = new String(passwordField.getPassword());

				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();

				try {
					if (facade.erabiltzaileaEgiaztatu(erab, pasa)==1)//administratzailea
					{
						MainWindow.login.dispose();
						MainWindow.adm.setVisible(true);
						MainWindow.hasiera.dispose();
					}
					else if(facade.erabiltzaileaEgiaztatu(erab, pasa)==2)//jabea
					{
						MainWindow.login.dispose();
						MainWindow.KonektatuaOwner = facade.getOwner(erab);
						MainWindow.jabea = new jabea();
						MainWindow.jabea.setVisible(true);
						MainWindow.hasiera.dispose();

					}

					else if (facade.erabiltzaileaEgiaztatu(erab, pasa)==3){//turista erregistratua

						MainWindow.login.dispose();
						MainWindow.KonektatuaTurista = facade.getTuristaErregistratua(erab);
						MainWindow.loginHasiera.setVisible(true);
						MainWindow.hasiera.dispose();
						

					}
					else lblNewLabel.setText("Erabiltzaile/pasahitz okerra!");

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		contentPane.add(btnHasiSaioa);

		btnItzuli = new JButton("Itzuli");
		btnItzuli.setBounds(187, 90, 117, 19);
		contentPane.add(btnItzuli);

		btnKontuaSortu = new JButton("Erregistratu");
		btnKontuaSortu.setBounds(187, 155, 141, 19);
		btnKontuaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(rdbtnTuristaBezala.isSelected()){

					MainWindow.erregistratu = new Erregistratu();
					MainWindow.erregistratu.setVisible(true);
				}
				else if(rdbtnJabeBezala.isSelected()){
					
					MainWindow.jabeaSortu = new JabeaSortu();
					MainWindow.jabeaSortu.setVisible(true);
				}
			}
		});
		contentPane.add(btnKontuaSortu);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(169, 117, 224, 26);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		rdbtnTuristaBezala = new JRadioButton("Turista bezala");
		rdbtnTuristaBezala.setBounds(24, 139, 149, 23);
		contentPane.add(rdbtnTuristaBezala);

		rdbtnTuristaBezala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				rdbtnJabeBezala.setSelected(false);

			}
		});

		rdbtnJabeBezala = new JRadioButton("Jabe bezala");
		rdbtnJabeBezala.setBounds(24, 166, 149, 23);
		contentPane.add(rdbtnJabeBezala);

		rdbtnJabeBezala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				rdbtnTuristaBezala.setSelected(false);

			}
		});
	}


	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public JRadioButton getRdbtnTuristaBezala() {
		return rdbtnTuristaBezala;
	}
	public JRadioButton getRdbtnJabeBezala() {
		return rdbtnJabeBezala;
	}
}
