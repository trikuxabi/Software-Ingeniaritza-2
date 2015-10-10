package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterface;

public class landetxeaEditatu extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_2;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065275796346221826L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow.landetxeaEditatu=new landetxeaEditatu();
					MainWindow.landetxeaEditatu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public landetxeaEditatu() {

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

			JButton btnSortu = new JButton("Editatu");
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
							if(facade.editRuralHouse(MainWindow.KonektatuaOwner,a, textField_2.getText(), textField_1.getText())){
								MainWindow.landetxeaEditatu.dispose();
								MainWindow.jabeEtxeak.dispose();
								MainWindow.jabeEtxeak = new JabearenEtxeZerrenda();
								MainWindow.jabeEtxeak.setVisible(true);
							}
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
