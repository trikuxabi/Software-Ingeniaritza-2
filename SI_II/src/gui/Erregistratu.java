package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;
import domain.ErregistratuGabea;
import domain.TuristaErregistratua;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.awt.Font;

public class Erregistratu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666361127607654339L;
	private JPanel contentPane;
	private JTextField Izena;
	private JTextField Abizenak;
	private JTextField Erabiltzailea;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField Helbidea;
	private JTextField KontuKorrontea;
	private JTextField Posta;
	private JLabel Errorea;
	private JButton btnKontuaSortu;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					
					MainWindow.erregistratu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Erregistratu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(23, 12, 70, 15);
		contentPane.add(lblIzena);

		JLabel lblAbizenak = new JLabel("Abizenak");
		lblAbizenak.setBounds(23, 49, 70, 15);
		contentPane.add(lblAbizenak);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea");
		lblErabiltzailea.setBounds(23, 86, 103, 15);
		contentPane.add(lblErabiltzailea);

		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(23, 126, 85, 15);
		contentPane.add(lblPasahitza);

		JLabel lblPasahitza_1 = new JLabel("Pasahitza");
		lblPasahitza_1.setBounds(23, 158, 85, 15);
		contentPane.add(lblPasahitza_1);

		JLabel lblEr = new JLabel("errepikatu");
		lblEr.setBounds(23, 173, 85, 15);
		contentPane.add(lblEr);

		JLabel lblHelbidea = new JLabel("Helbidea");
		lblHelbidea.setBounds(266, 12, 85, 15);
		contentPane.add(lblHelbidea);

		JLabel lblNewLabel = new JLabel("Kontu korrontea");
		lblNewLabel.setBounds(266, 49, 133, 15);
		contentPane.add(lblNewLabel);

		Izena = new JTextField();
		Izena.setBounds(82, 10, 156, 19);
		contentPane.add(Izena);
		Izena.setColumns(10);

		Abizenak = new JTextField();
		Abizenak.setBounds(92, 47, 146, 19);
		contentPane.add(Abizenak);
		Abizenak.setColumns(10);

		Erabiltzailea = new JTextField();
		Erabiltzailea.setBounds(124, 84, 114, 19);
		contentPane.add(Erabiltzailea);
		Erabiltzailea.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 124, 172, 17);
		contentPane.add(passwordField);


		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(126, 157, 172, 17);
		contentPane.add(passwordField_1);

		Helbidea = new JTextField();
		Helbidea.setBounds(348, 10, 247, 17);
		contentPane.add(Helbidea);
		Helbidea.setColumns(10);

		KontuKorrontea = new JTextField();
		KontuKorrontea.setBounds(400, 47, 195, 17);
		contentPane.add(KontuKorrontea);
		KontuKorrontea.setColumns(10);

		JLabel lblPosta = new JLabel("Posta ");
		lblPosta.setBounds(266, 86, 70, 15);
		contentPane.add(lblPosta);

		Posta = new JTextField();
		Posta.setBounds(324, 84, 271, 19);
		contentPane.add(Posta);
		Posta.setColumns(10);

		btnKontuaSortu = new JButton("Kontua sortu");
		btnKontuaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!(getAbizenak().getText().isEmpty() || getIzena().getText().isEmpty() ||
						getErabiltzailea().getText().isEmpty() || getPasswordField().toString().isEmpty() ||
						getPasswordField_1().toString().isEmpty() || getHelbidea().getText().isEmpty() ||
						getKontuKorrontea().getText().isEmpty() ||  getPosta().getText().isEmpty())){


					String a = new String(getPasswordField().getPassword());
					String b = new String(getPasswordField_1().getPassword());

					if (a.compareTo(b)==0){

						System.out.println(getErabiltzailea().getText() + " erabiltzailea sortu da ");
						ErregistratuGabea kontua=new ErregistratuGabea();
						TuristaErregistratua turistaErregistratua = kontua.erregistratu(getErabiltzailea().getText(), a, getIzena().getText(), getAbizenak().getText(), getKontuKorrontea().getText(), getPosta().getText());
						turistaErregistratua.inpr();
						
						ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
						
						try {
							if (facade.storeTuristaErregistratua(turistaErregistratua)){
								Errorea.setText("");
								btnKontuaSortu.setEnabled(false);
								//frame.setVisible(false);
								MainWindow.erregistratu.setVisible(false);
							}
							else{
								Errorea.setText("Erab. existitzen da!");
							}
						
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Hasiera.turistaErregistratuak.add(turistaErregistratua);
						

					}else{
						Errorea.setBackground(Color.red);
						Errorea.setText("Pasahitzak ezberdinak dira.");
					}
				}else{
					Errorea.setBackground(Color.red);
					Errorea.setText("Gelaxka batzuk hutsik daude.");


				}


			}
		});
		btnKontuaSortu.setBackground(SystemColor.controlHighlight);
		btnKontuaSortu.setBounds(324, 143, 266, 104);
		contentPane.add(btnKontuaSortu);

		Errorea = new JLabel("");
		Errorea.setForeground(Color.RED);
		Errorea.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		Errorea.setBounds(23, 201, 266, 27);
		contentPane.add(Errorea);
	}

	public JTextField getAbizenak() {
		return Abizenak;
	}
	public JTextField getIzena() {
		return Izena;
	}
	public JTextField getErabiltzailea() {
		return Erabiltzailea;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}
	public JTextField getHelbidea() {
		return Helbidea;
	}
	public JTextField getKontuKorrontea() {
		return KontuKorrontea;
	}
	public JTextField getPosta() {
		return Posta;
	}
	public JLabel getErrorea() {
		return Errorea;
	}
	public JButton getBtnKontuaSortu() {
		return btnKontuaSortu;
	}
}
