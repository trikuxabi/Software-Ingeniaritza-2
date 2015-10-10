package gui;


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

public class JabearenEtxeZerrenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2005782249025754943L;
	private JPanel contentPane;
	private DefaultListModel<RuralHouse> rural = new DefaultListModel<RuralHouse>();
	private JList<RuralHouse> etxeList;
	public static RuralHouse etxea;
	private JButton btnEditatu;
	private JButton btnEzabatu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.etxeenZerrenda.setVisible(true);

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
	public JabearenEtxeZerrenda(){

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

		btnEditatu = new JButton("Editatu");
		btnEditatu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				//Elementu bat baino gehiago hartu al daiteke??
				List<RuralHouse> result = etxeList.getSelectedValuesList();
				RuralHouse r = result.get(0);
				
				
				MainWindow.landetxeaEditatu.getTextField().setText(Integer.toString(r.getHouseNumber()));
				MainWindow.landetxeaEditatu.getTextField().disable();
				MainWindow.landetxeaEditatu.getTextField_1().setText(r.getCity());
				MainWindow.landetxeaEditatu.getTextField_2().setText(r.getDescription());
				
				MainWindow.landetxeaEditatu.setVisible(true);

			}
		});
		btnEditatu.setBounds(376, 13, 107, 25);
		contentPane.add(btnEditatu);

		btnEzabatu = new JButton("Ezabatu");
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				
				RuralHouse rh = etxeList.getSelectedValue();
				try {
					facade.jabeariLandetxeaKendu(MainWindow.KonektatuaOwner, rh);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MainWindow.jabeEtxeak.dispose();
				MainWindow.jabeEtxeak = new JabearenEtxeZerrenda();
				MainWindow.jabeEtxeak.setVisible(true);
				
				
			}
		});
		btnEzabatu.setBounds(376, 49, 107, 25);
		contentPane.add(btnEzabatu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 352, 293);
		contentPane.add(scrollPane);

		etxeList = new JList<RuralHouse>();
		scrollPane.setViewportView(etxeList);
		etxeList.setModel(rural);
		
		try {
		
			for(int i = 0; i<MainWindow.KonektatuaOwner.getRuralHouses().size(); i++)rural.addElement(MainWindow.KonektatuaOwner.getRuralHouses().get(i));
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JList<RuralHouse> getEtxeList() {
		return etxeList;
	}
	public JButton getBtnEditatu() {
		return btnEditatu;
	}
	public JButton getBtnEzabatu() {
		return btnEzabatu;
	}
}
