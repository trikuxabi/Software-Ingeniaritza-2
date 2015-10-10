package gui;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Owner;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import domain.RuralHouse;

import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

import businessLogic.ApplicationFacadeInterface;

public class AdminJabeaEzabatu extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = -2005782249025754943L;
	private DefaultListModel<Owner> JabeenLista1 = new DefaultListModel<Owner>();
	private JList<Owner> etxeList;
	public static RuralHouse etxea;
	private JButton btnEzabatu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow.adminJabeaEzabatu = new AdminJabeaEzabatu();
					MainWindow.adminJabeaEzabatu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminJabeaEzabatu() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblSdasd = new JLabel();
		lblSdasd.setFont(new Font("Abyssinica SIL", Font.ITALIC, 15));
		lblSdasd.setBackground(Color.BLACK);
		lblSdasd.setBounds(194, 96, 289, 191);
		contentPane.add(lblSdasd);

		btnEzabatu = new JButton("Ezabatu");
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				Owner o = etxeList.getSelectedValue();
				
				try {
					facade.jabeaEzabatu(o);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				MainWindow.adminJabeaEzabatu.dispose();
				MainWindow.adminJabeaEzabatu = new AdminJabeaEzabatu();
				MainWindow.adminJabeaEzabatu.setVisible(true);


			}
		});
		btnEzabatu.setBounds(376, 10, 107, 25);
		contentPane.add(btnEzabatu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 352, 293);
		contentPane.add(scrollPane);

		etxeList = new JList<Owner>();
		scrollPane.setViewportView(etxeList);
		etxeList.setModel(JabeenLista1);

		try {

			LinkedList<Owner> l= new LinkedList<Owner>();
			ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
			l=facade.getAllOwner();
			
			for(int i = 0; i<l.size(); i++)JabeenLista1.addElement(l.get(i));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JList<Owner> getEtxeList() {
		return etxeList;
	}

	public JButton getBtnEzabatu() {
		return btnEzabatu;
	}


}
