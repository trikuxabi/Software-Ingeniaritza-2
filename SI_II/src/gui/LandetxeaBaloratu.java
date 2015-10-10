package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;
import domain.Balorazioa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class LandetxeaBaloratu extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JRadioButton radio5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow.LandetxeaBaloratu = new LandetxeaBaloratu();
					MainWindow.LandetxeaBaloratu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LandetxeaBaloratu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		radio1 = new JRadioButton("1");
		buttonGroup.add(radio1);
		radio1.setBounds(43, 83, 41, 25);
		contentPane.add(radio1);
		
		radio2 = new JRadioButton("2");
		buttonGroup.add(radio2);
		radio2.setBounds(113, 83, 41, 25);
		contentPane.add(radio2);
		
		radio3 = new JRadioButton("3");
		buttonGroup.add(radio3);
		radio3.setBounds(182, 83, 41, 25);
		contentPane.add(radio3);
		
		radio4 = new JRadioButton("4");
		buttonGroup.add(radio4);
		radio4.setBounds(249, 83, 35, 25);
		contentPane.add(radio4);
		
		radio5 = new JRadioButton("5");
		buttonGroup.add(radio5);
		radio5.setBounds(318, 83, 35, 25);
		contentPane.add(radio5);
		
		JLabel lblAukeratuLandetxeariEman = new JLabel("Aukeratu landetxeari eman nahi diozun balorazioa");
		lblAukeratuLandetxeariEman.setBounds(54, 38, 303, 16);
		contentPane.add(lblAukeratuLandetxeariEman);
		
		JButton btnBaloratu = new JButton("Landetxea baloratu");
		btnBaloratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int bal;
				
				if (radio5.isSelected()) bal = 5;
				else if (radio4.isSelected()) bal = 4;
				else if (radio3.isSelected()) bal = 3;
				else if (radio2.isSelected()) bal = 2;
				else bal = 1;
				
				ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
				try {
					facade.BalorazioaGehitu(MainWindow.LandetxearenBalorazioa, bal);
					MainWindow.LandetxeaBaloratu.dispose();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnBaloratu.setBounds(113, 145, 171, 69);
		contentPane.add(btnBaloratu);
	}
	public JRadioButton getRadio1() {
		return radio1;
	}
	public JRadioButton getRadio2() {
		return radio2;
	}
	public JRadioButton getRadio3() {
		return radio3;
	}
	public JRadioButton getRadio4() {
		return radio4;
	}
	public JRadioButton getRadio5() {
		return radio5;
	}
}
