package gui;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import domain.Booking;
import domain.Offer;
import domain.RuralHouse;

import javax.swing.JList;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

public class OfertakIkusi extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private DefaultListModel rural = new DefaultListModel();
	private JList<Offer> offerList;
	private JButton btnAukeratu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					MainWindow.ofertakIkusi2=new OfertakIkusi();
					MainWindow.ofertakIkusi2.setVisible(true);

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
	public OfertakIkusi() {
		// TODO Auto-generated constructor stub

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 505, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblSdasd = new JLabel();
		lblSdasd.setFont(new Font("Monospaced", Font.BOLD, 12));
		lblSdasd.setBackground(Color.BLACK);
		lblSdasd.setBounds(170, 73, 315, 227);
		contentPane.add(lblSdasd);

		JButton btnEtxearenOrria = new JButton("Informazioa");
		btnEtxearenOrria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(rural.size()!=0)
				{
					int i=offerList.getSelectedIndex();
					Offer of=(Offer) rural.get(i);
					lblSdasd.setText( "<html><body>LEHEN EGUNA: "+of.getFirstDay() +"<br>AZKEN EGUNA: "+of.getLastDay()+"<br> PREZIOA: "+of.getPrice()+"<br> <br></body></html>" );
				}

			}
		});
		btnEtxearenOrria.setBounds(345, 11, 130, 25);
		contentPane.add(btnEtxearenOrria);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 146, 293);
		contentPane.add(scrollPane);

		offerList = new JList<Offer>();
		scrollPane.setViewportView(offerList);
		offerList.setModel(rural);

		btnAukeratu = new JButton("Aukeratu");
		btnAukeratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (offerList.isSelectionEmpty()){
					System.out.println("Ofertarik ez duzu hautatu");
				}
				else{

					if(MainWindow.KonektatuaTurista!=null)
					{
						;
						int i=offerList.getSelectedIndex();
						Offer of=(Offer) rural.get(i);
						Booking b = of.createBooking(1, MainWindow.KonektatuaTurista.getPostaElektronikoa());
						ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();


						try {
							facade.turistaEguneratu(MainWindow.KonektatuaTurista, b);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Oferta hartu da");

						MainWindow.ofertakIkusi2.dispose();				
					}
				}

			}
		});
		btnAukeratu.setBounds(345, 53, 130, 25);
		contentPane.add(btnAukeratu);



		try {

			Vector<Offer> o = MainWindow.LandetxearenOfertak.getAllOffers();

			for(int i = 0; i<o.size(); i++){
				if (o.get(i).getBooking()==null) rural.addElement(o.get(i));
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public JButton getBtnAukeratu() {
		return btnAukeratu;
	}
}