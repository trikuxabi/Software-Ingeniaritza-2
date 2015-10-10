package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import configuration.ConfigXML;
import domain.Owner;
import domain.RuralHouse;
import domain.TuristaErregistratua;
import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;

    private static ApplicationFacadeInterface appFacadeInterface;
    public static Owner KonektatuaOwner=null;
    public static TuristaErregistratua KonektatuaTurista=null; 
    public static RuralHouse LandetxearenOfertak=null;
    public static RuralHouse landetxearenIruzkinak=null;
    public static RuralHouse LandetxearenBalorazioa=null;
    public static RuralHouse LandetxearenIruzkinakLortzeko=null;
    
	public static Hasiera hasiera = new Hasiera();
    public static Erregistratu erregistratu;
	public static Login login = new Login();
	public static Administratzailea adm = new Administratzailea();
	public static JabeaSortu jabeaSortu;
	public static jabea jabea;
	public static HasieraLogin loginHasiera = new HasieraLogin();
	public static EtxeZerrenda etxeenZerrenda;
	public static LandetxeaSortu landetxeaSortu = new LandetxeaSortu();
	public static JabearenEtxeZerrenda jabeEtxeak;
	public static landetxeaEditatu landetxeaEditatu=new landetxeaEditatu();
	public static AdminJabeaEzabatu adminJabeaEzabatu;
	public static AdminLandetxeaEzabatu AdminLandetxeaEzabatu;
	public static SetAvailability2GUI OfertaGehitu;
	public static QueryAvailabilityGUI OfertakIkusi;
	public static OfertakIkusi ofertakIkusi2;
	public static Iruzkindu iruzkindu;
	public static LandetxeaBaloratu LandetxeaBaloratu;
	public static IruzkinZerrenda iruzkinakIkusi;
	public static RankingaIkusi rankingaIkusi;
	public static IruzkinaSalatu iruzkinaSalatu;
	public static SalaketakIkusi salaketakIkusi;
	public static ErabiltzaileakIruzkinakEzabatu erabiltzaileakIruzkinakEzabatu;	
	public static boolean rankingean=false;

	public static ApplicationFacadeInterface getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterface afi){
		appFacadeInterface=afi;
	}
	protected JLabel lblNewLabel;
	

	/**
	 * This is the default constructor
	 */
	public MainWindow() {
		super();
        
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle("Rural Houses");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(4);
			gridLayout.setColumns(1);
			jContentPane = new JPanel();
			jContentPane.setLayout(gridLayout);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3(), null);
			jContentPane.add(getBoton2(), null);
			jContentPane.add(getBoton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton1() {
		if (boton1 == null) {
			boton1 = new JButton();
			boton1.setText("Book rural house");
			boton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la univerdad
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return boton1;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setText("Set availability");
			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					JFrame a = new SetAvailabilityGUI();
					a.setVisible(true);
				}
			});
		}
		return boton2;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setText("Query availability");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					//JFrame a = new QueryAvailabilityWindow();
					JFrame a = new QueryAvailabilityGUI();

					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Select option:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

} // @jve:decl-index=0:visual-constraint="0,0"

