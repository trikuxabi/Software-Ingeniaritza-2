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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

public class IruzkinZerrenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695488795836310386L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel iruzk = new DefaultListModel();
	private JList<Iruzkina> IruzkinList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.iruzkinakIkusi = new IruzkinZerrenda();
					MainWindow.iruzkinakIkusi.setVisible(true);

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
	public IruzkinZerrenda(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 571);
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



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		LinkedList<Iruzkina> ir;

		try {


			ir = facade.etxearenIruzkinakLortu(MainWindow.LandetxearenIruzkinakLortzeko);
			if(ir!=null)
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
