package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class LandetxeaSortu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667626950083230701L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.landetxeaSortu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LandetxeaSortu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Zenbakia");
		lblNewLabel.setBounds(47, 53, 70, 15);
		contentPane.add(lblNewLabel);

		JLabel lblHerria = new JLabel("Herria");
		lblHerria.setBounds(47, 90, 70, 15);
		contentPane.add(lblHerria);

		JLabel lblNewLabel_1 = new JLabel("Laburpena");
		lblNewLabel_1.setBounds(47, 127, 94, 15);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(135, 51, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(135, 88, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(135, 127, 259, 65);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnSortu = new JButton("Sortu");
		btnSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!textField.getText().isEmpty())
				{
					int a = 0;

					ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
					try {
						
						a= Integer.parseInt(textField.getText());

					}catch(Exception e2) {

						getLblNewLabel_2().setText("Zenbakia sartu");
					
					}

					try {
						if(facade.sortuEtaStoreRuralHouse(MainWindow.KonektatuaOwner,a, textField_2.getText(), textField_1.getText())){
							MainWindow.landetxeaSortu.dispose();
						}
						else getLblNewLabel_2().setText("Zenbaki Errepikakorra");
						
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		});
		btnSortu.setBounds(288, 223, 117, 25);
		contentPane.add(btnSortu);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(267, 53, 137, 15);
		contentPane.add(lblNewLabel_2);
		
	}
	public JTextField getTextField() {
		return textField;
	}
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
}
