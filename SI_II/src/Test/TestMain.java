package Test;


import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import javax.swing.UIManager;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccessLocal;
import dataAccess.DataAccessRemote;
import domain.Owner;
import domain.RuralHouse;
import domain.TuristaErregistratua;
import exceptions.DB4oManagerCreationException;
import gui.MainWindow;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import junit.framework.TestCase;

public class TestMain extends TestCase{
	
	TuristaErregistratua erab = new TuristaErregistratua();
	Owner jabea = new Owner();
	RuralHouse etxea = new RuralHouse();
	
	@Override
	protected void setUp() throws Exception{
		super.setUp();
				
		//DBa
		ConfigXML c=ConfigXML.getInstance();
		try {
			
			ApplicationFacadeInterface appFacadeInterface;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new FacadeImplementation();
				
				if (c.isDatabaseLocal()) 
					 appFacadeInterface.setDataAccess(new DataAccessLocal());
			    else 
			    	 appFacadeInterface.setDataAccess(new DataAccessRemote());
			} 		
			
			else {
				System.setProperty("java.security.policy", c.getJavaPolicyPath());				
				System.setSecurityManager(new RMISecurityManager());
				
				final String businessLogicNode = c.getBusinessLogicNode();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				
				appFacadeInterface=(ApplicationFacadeInterface) Naming.lookup("rmi://"+ businessLogicNode + ":" + portNumber + serviceName);
			} 
			MainWindow.setBussinessLogic(appFacadeInterface);
			
			ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
			facade.administratzaileaDagoenBegiratu();//ez badago sortu, eta datu basean gehituko du

		} catch (java.rmi.ConnectException e) {
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (java.rmi.NotBoundException e) {
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (com.db4o.ext.DatabaseFileLockedException e) {
			System.out.println("Error in StartWindow: "+e.toString());
		} catch (DB4oManagerCreationException e) {
			System.out.println("Error in StartWindow: "+e.toString());
		}catch (Exception e) {	
			System.out.println("Error in StartWindow: "+e.toString());
		}
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
	}
	
	public void testTuristaErregistratu1() throws InterruptedException, RemoteException{
		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		erab.setIzena("Aniceto");
		assertEquals(1111,erab.probatuIzena());
		Thread.sleep(1000);
		erab.setAbizenak("Pino");
		assertEquals(2222,erab.probatuAbizena());
		Thread.sleep(1000);
		erab.setErabiltzailea("proba4");
		assertEquals(6666,erab.probatuErabiltzailea());
		Thread.sleep(1000);
		erab.setPasahitza("pasahitza1");
		assertEquals(4444,erab.probatuPasahitza());
		Thread.sleep(1000);
		erab.setKontuKorrontea("0000231456203147");
		assertEquals(5555,erab.probatuKontuKorrontea());
		Thread.sleep(1000);
		erab.setPostaElektronikoa("email@zerb.com");
		assertEquals(3333,erab.probatuEmaila());
		Thread.sleep(1000);
		facade.storeTuristaErregistratua(this.erab);
		facade.close();
	}
	
	
	@Test
	public void testJabeaErregistratu() throws InterruptedException, RemoteException{
		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		
		jabea.setName("Proba");
		assertEquals(jabea.probatuIzena(),1111);
		Thread.sleep(1000);
		
		jabea.setLogin("Jabea1");
		assertEquals(jabea.probatuLogin(),2222);
		Thread.sleep(1000);
		
		jabea.setPassword("pasahitza123");
		assertEquals(jabea.probatuPasahitza(),3333);
		Thread.sleep(1000);
		
		jabea.setBankAccount("1234567891234567");
		assertEquals(jabea.probatuKontuKorrontea(),4444);
		Thread.sleep(1000);
		
		facade.StoreOwner(jabea);
		facade.close();
	}
	
	//pasahitza laburra, banku zenbaki okerra
	@Test
	public void testJabeaErregistratu2() throws InterruptedException, RemoteException{
		
		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		
		jabea.setName("Proba");
		assertEquals(jabea.probatuIzena(),1111);
		Thread.sleep(1000);
		
		jabea.setLogin("Jabea2");
		assertEquals(jabea.probatuLogin(),2222);
		Thread.sleep(1000);
		
		jabea.setPassword("pasa");
		assertEquals(jabea.probatuPasahitza(),3333);
		Thread.sleep(1000);
		
		jabea.setBankAccount("1234567das234567");
		assertEquals(jabea.probatuKontuKorrontea(),4444);
		Thread.sleep(1000);
		
		facade.StoreOwner(jabea);
		facade.close();
		
	}
	
	@Test
	public void testLandetxeaSortu() throws InterruptedException, RemoteException{
		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		
		etxea.setCity("Proba");
		assertEquals(etxea.probatuHerria(),1111);
		Thread.sleep(1000);
		
		etxea.setDescription("Honakoa deskribapen egoki bat da.");
		assertEquals(etxea.probatuLaburpena(),2222);
		Thread.sleep(1000);
		
		facade.storeRuralHouse(etxea);
		facade.close();
	}
	
	
	
	@Test
	public void testLandetxeaSortu2() throws InterruptedException, RemoteException{
		ApplicationFacadeInterface facade=MainWindow.getBusinessLogic();
		
		etxea.setCity("1");
		assertEquals(etxea.probatuHerria(),1111);
		Thread.sleep(1000);
		
		etxea.setDescription("Laburra");
		assertEquals(etxea.probatuLaburpena(),2222);
		Thread.sleep(1000);
		
		facade.storeRuralHouse(etxea);
		facade.close();
	}
	
	
	
}
