package gui;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import domain.RuralHouse;

import javax.swing.JList;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;

public class EtxeZerrenda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695488795836310386L;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel rural = new DefaultListModel();
	private JList<RuralHouse> etxeList;
	private JButton btnIruzkinakIkusi;

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
	@SuppressWarnings("unchecked")
	public EtxeZerrenda(){

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 882, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblSdasd = new JLabel();
		lblSdasd.setFont(new Font("Abyssinica SIL", Font.ITALIC, 15));
		lblSdasd.setBackground(Color.BLACK);
		lblSdasd.setBounds(512, 203, 289, 191);
		contentPane.add(lblSdasd);

		JButton btnEtxearenOrria = new JButton("Informazioa");
		btnEtxearenOrria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=-1;
				i=etxeList.getSelectedIndex();
				if(i!=-1){

					RuralHouse r=(RuralHouse) rural.get(i);
					lblSdasd.setText( "<html><body>DESKRIPZIOA: <br> <br>"+r.getDescription()+"</body></html>" );
				}
			}
		});
		btnEtxearenOrria.setBounds(722, 71, 135, 25);
		contentPane.add(btnEtxearenOrria);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 420, 500);
		contentPane.add(scrollPane);

		etxeList = new JList<RuralHouse>();
		scrollPane.setViewportView(etxeList);
		etxeList.setModel(rural);			



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		Vector<RuralHouse> rh;

		try {

			rh = facade.getAllRuralHouses();
			for(int i = 0; i<rh.size(); i++)rural.addElement(rh.get(i));

			JButton btnNewButton = new JButton("Ofertak");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int i=-1;
					i=etxeList.getSelectedIndex();
					if(i!=-1){
						MainWindow.LandetxearenOfertak=(RuralHouse) rural.get(i);

						MainWindow.ofertakIkusi2=new OfertakIkusi();
						MainWindow.ofertakIkusi2.setVisible(true);
					}


				}
			});
			btnNewButton.setBounds(722, 31, 135, 25);
			contentPane.add(btnNewButton);

			JButton buttonBaloratu = new JButton("Baloratu");
			buttonBaloratu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if(MainWindow.KonektatuaTurista!=null)
					{
						int i=-1;
						i=etxeList.getSelectedIndex();
						if(i!=-1){
							MainWindow.LandetxearenBalorazioa=(RuralHouse) rural.get(i);

							MainWindow.LandetxeaBaloratu = new LandetxeaBaloratu();
							MainWindow.LandetxeaBaloratu.setVisible(true);
						}
					}

				}
			});
			buttonBaloratu.setBounds(722, 108, 135, 25);
			contentPane.add(buttonBaloratu);

			JButton btnIruzkindu = new JButton("Iruzkindu");
			btnIruzkindu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if(MainWindow.KonektatuaTurista!=null)
					{

						if(MainWindow.KonektatuaTurista!=null)
						{
							int i=-1;
							i=etxeList.getSelectedIndex();
							if(i!=-1){
								RuralHouse r=(RuralHouse) rural.get(i);
								MainWindow.landetxearenIruzkinak=r;

								MainWindow.iruzkindu=new Iruzkindu();
								MainWindow.iruzkindu.setVisible(true);
							}
						}

					}

				}
			});
			btnIruzkindu.setBounds(722, 146, 135, 25);
			contentPane.add(btnIruzkindu);

			btnIruzkinakIkusi = new JButton("Iruzkinak Ikusi");
			btnIruzkinakIkusi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					int i=-1;
					i=etxeList.getSelectedIndex();
					if(i!=-1){
						RuralHouse r=(RuralHouse) rural.get(i);
						MainWindow.LandetxearenIruzkinakLortzeko=r;

						MainWindow.iruzkinakIkusi = new IruzkinZerrenda();
						MainWindow.iruzkinakIkusi.setVisible(true);
					}

				}
			});
			btnIruzkinakIkusi.setBounds(722, 183, 146, 25);
			contentPane.add(btnIruzkinakIkusi);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JList<RuralHouse> getEtxeList() {
		return etxeList;
	}
	public JButton getBtnIruzkinakIkusi() {
		return btnIruzkinakIkusi;
	}
}
