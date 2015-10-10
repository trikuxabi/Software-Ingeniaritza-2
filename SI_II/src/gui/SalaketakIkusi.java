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
import domain.Salaketa;

import javax.swing.JList;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

public class SalaketakIkusi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695488795836310386L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel salaketak = new DefaultListModel();
	private JList<Salaketa> SalaketaList;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

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
	public SalaketakIkusi(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 764, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 420, 500);
		contentPane.add(scrollPane);

		SalaketaList = new JList<Salaketa>();
		scrollPane.setViewportView(SalaketaList);
		SalaketaList.setModel(salaketak);			

		btnNewButton = new JButton("Salaketa Onartu");


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int i=-1;
				i=SalaketaList.getSelectedIndex();
				if(i!=-1){
					Salaketa s= (Salaketa) salaketak.get(i);
					ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
					try {
						facade.salaketaOnartu(s);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MainWindow.salaketakIkusi.dispose();
					MainWindow.salaketakIkusi=new SalaketakIkusi();
					MainWindow.salaketakIkusi.setVisible(true);
				}
			}
		});


		btnNewButton.setBounds(463, 44, 195, 45);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Salaketa Ezeztatu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i=-1;
				i=SalaketaList.getSelectedIndex();
				if(i!=-1){
					Salaketa s= (Salaketa) salaketak.get(i);
					ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
					try {
						facade.salaketaEzeztatu(s);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MainWindow.salaketakIkusi.dispose();
					MainWindow.salaketakIkusi=new SalaketakIkusi();
					MainWindow.salaketakIkusi.setVisible(true);
				}
				
			}
		});
		btnNewButton_1.setBounds(463, 116, 195, 45);
		contentPane.add(btnNewButton_1);



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		Vector<Salaketa> l;

		try {

			l = facade.salaketakLortu();
			for(int i = 0; i<l.size(); i++)salaketak.addElement(l.get(i));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JList<Salaketa> getSalaketaList() {
		return SalaketaList;
	}
}