package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JPasswordField;

import businessLogic.ApplicationFacadeInterface;
import java.awt.Color;

public class JabeaSortu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6396032596074806218L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel Errorea;
	private JButton btnSortu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainWindow.jabeaSortu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JabeaSortu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setBounds(29, 38, 70, 15);
		contentPane.add(lblIzena);
		
		JLabel lblNewLabel = new JLabel("Erabiltzailea");
		lblNewLabel.setBounds(29, 65, 103, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setBounds(29, 92, 92, 15);
		contentPane.add(lblPasahitza);
		
		JLabel lblPasahitzaErrepikatu = new JLabel("Pasahitza errepikatu");
		lblPasahitzaErrepikatu.setBounds(29, 119, 150, 26);
		contentPane.add(lblPasahitzaErrepikatu);
		
		JLabel lblNewLabel_1 = new JLabel("Bankuko Zenbakia");
		lblNewLabel_1.setBounds(29, 157, 160, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(157, 36, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 63, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(181, 155, 114, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		btnSortu = new JButton("Sortu");
		btnSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(textField.getText().isEmpty()||textField_1.getText().isEmpty()||textField_4.getText().isEmpty()||
						passwordField.getPassword().toString().isEmpty()||passwordField_1.getPassword().toString().isEmpty()))
				{
					
					String a = new String(passwordField.getPassword());
					String b = new String(passwordField_1.getPassword());

					if (a.compareTo(b)==0){
						
						ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
						try {
							if (facade.sortuEtaStoreOwner(textField.getText(), textField_1.getText(), a, textField_4.getText())){
								System.out.println("jabea sortu da");
								MainWindow.jabeaSortu.dispose();
							}
							else{
								Errorea.setText("jabeak existitzen du");
							}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						
						Errorea.setText("Pasahitzak desberdinak dira");
					}
					
				}else{
					
					Errorea.setText("Lehiatilaren bat hutsik dago");
				}
				
			}
		});
		btnSortu.setBounds(267, 212, 117, 25);
		contentPane.add(btnSortu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 90, 90, 19);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(197, 123, 114, 19);
		contentPane.add(passwordField_1);
		
		Errorea = new JLabel("");
		Errorea.setForeground(Color.RED);
		Errorea.setBounds(29, 216, 183, 21);
		contentPane.add(Errorea);
	}
	protected JTextField getTextField_4() {
		return textField_4;
	}
	protected JPasswordField getPasswordField_1() {
		return passwordField_1;
	}
	protected JPasswordField getPasswordField() {
		return passwordField;
	}
	protected JTextField getTextField_1() {
		return textField_1;
	}
	protected JTextField getTextField() {
		return textField;
	}
	public JLabel getErrorea() {
		return Errorea;
	}
	public JButton getBtnSortu() {
		return btnSortu;
	}
}
