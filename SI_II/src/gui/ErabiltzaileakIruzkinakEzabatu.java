package gui;

import java.awt.EventQueue;
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
import businessLogic.FacadeImplementation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

public class ErabiltzaileakIruzkinakEzabatu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695488795836310386L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel iruzk = new DefaultListModel();
	private JList<Iruzkina> IruzkinList;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.erabiltzaileakIruzkinakEzabatu = new ErabiltzaileakIruzkinakEzabatu();
					MainWindow.erabiltzaileakIruzkinakEzabatu.setVisible(true);

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
	public ErabiltzaileakIruzkinakEzabatu(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 571);
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

		btnNewButton = new JButton("Ezabatu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i=-1;
				i=IruzkinList.getSelectedIndex();
				if(i!=-1){

					Iruzkina iruzkina=(Iruzkina) iruzk.get(i);
					ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
					try {
						facade.iruzkinaEzabatu(iruzkina);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MainWindow.erabiltzaileakIruzkinakEzabatu.dispose();
					MainWindow.erabiltzaileakIruzkinakEzabatu=new ErabiltzaileakIruzkinakEzabatu();
					MainWindow.erabiltzaileakIruzkinakEzabatu.setVisible(true);
				}

			}
		});
		btnNewButton.setBounds(466, 13, 117, 25);
		contentPane.add(btnNewButton);



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		Vector<Iruzkina> ir;

		try {

			ir = facade.getNireIruzkinak(MainWindow.KonektatuaTurista);
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
