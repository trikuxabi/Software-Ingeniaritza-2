package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

public class RankingaIkusi extends JFrame {

	private JList<RuralHouse> etxeList;
	private JPanel contentPane;
	private DefaultListModel rural = new DefaultListModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow.rankingaIkusi = new RankingaIkusi();
					MainWindow.rankingaIkusi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public RankingaIkusi(){

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

				int i=etxeList.getSelectedIndex();
				RuralHouse r=(RuralHouse) rural.get(i);
				lblSdasd.setText( "<html><body>DESKRIPZIOA: <br> <br>"+r.getDescription()+"<br>"+r.getBatazbestekoBalorazioa()+"</body></html>" );

			}
		});
		btnEtxearenOrria.setBounds(532, 127, 233, 49);
		contentPane.add(btnEtxearenOrria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 420, 500);
		contentPane.add(scrollPane);

		etxeList = new JList<RuralHouse>();
		scrollPane.setViewportView(etxeList);
		etxeList.setModel(rural);			
		
		JLabel lblNewLabel = new JLabel("Balorazioen araberako ranking-a");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(463, 31, 360, 59);
		contentPane.add(lblNewLabel);



		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		Vector<RuralHouse> rh;

		try {

			rh = facade.rankingaLortu();
			for(int i = 0; i<rh.size(); i++)rural.addElement(rh.get(i));



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
