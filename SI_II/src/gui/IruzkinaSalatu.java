package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import domain.Iruzkina;
import domain.RuralHouse;

import javax.swing.JList;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class IruzkinaSalatu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695488795836310386L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel iruzk = new DefaultListModel();
	private JList<Iruzkina> IruzkinList;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.iruzkinaSalatu = new IruzkinaSalatu();
					MainWindow.iruzkinaSalatu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 * @throws RemoteException 
	 */
	@SuppressWarnings("unchecked")
	public IruzkinaSalatu(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 420, 500);
		contentPane.add(scrollPane);

		IruzkinList = new JList<Iruzkina>();
		scrollPane.setViewportView(IruzkinList);
		IruzkinList.setModel(iruzk);			
		
		textField = new JTextField();
		textField.setBounds(456, 88, 301, 91);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Salatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=IruzkinList.getSelectedIndex();
				Iruzkina I=(Iruzkina) iruzk.get(i);
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					facade.salatu(MainWindow.KonektatuaOwner, textField.getText(), I);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(456, 203, 117, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblA = new JLabel("Arrazoia");
		lblA.setBounds(469, 61, 70, 15);
		contentPane.add(lblA);



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		LinkedList<Iruzkina> ir;

		try {

			ir = facade.JabearenEtxeenIruzkinakLortu(MainWindow.KonektatuaOwner);
			for(int i = 0; i<ir.size(); i++)iruzk.addElement(ir.get(i));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JList<Iruzkina> getIruzkinList() {
		return IruzkinList;
	}
}
