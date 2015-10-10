package businessLogic;

import java.rmi.*;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Date;

import dataAccess.DataAccessInterface;
import domain.Booking;
import domain.Erabiltzaile;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;


import domain.Salaketa;
import domain.TuristaErregistratua;
import exceptions.OfferCanNotBeBooked; 


public interface ApplicationFacadeInterface extends Remote {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	Booking createBooking(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException,
			OfferCanNotBeBooked;

	
	/**
	 * This method retrieves the existing  owners 
	 * 
	 * @return a Set of owners
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception;
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	
	public void close() throws RemoteException;

    public void setDataAccess(DataAccessInterface dai) throws RemoteException;

	public int erabiltzaileaEgiaztatu(String erab, String pasa) throws RemoteException;
	
	public boolean storeTuristaErregistratua(TuristaErregistratua t)throws RemoteException;
	
	public void administratzaileaDagoenBegiratu()throws RemoteException;
	
	public boolean sortuEtaStoreOwner(String name, String login, String password, String bankAccount)throws RemoteException;
	
	public boolean sortuEtaStoreRuralHouse(Owner o, int houseNumber, String description, String city)throws RemoteException;

	public Owner getOwner(String erab) throws RemoteException;
	
	public boolean editRuralHouse(Owner o, int houseNumber, String description, String citythrows)throws RemoteException;

	public boolean jabeariLandetxeaKendu (Owner o, RuralHouse r) throws RemoteException;
	
	public void jabeaEzabatu(Owner o)throws RemoteException;
	
	public LinkedList<Owner> getAllOwner()throws RemoteException;
		
	public TuristaErregistratua getTuristaErregistratua(String erab) throws RemoteException;
	
	public void deleteOffer(Offer o)throws RemoteException;
	
	public void LandetxeaEzabatu(RuralHouse r)throws RemoteException;
	
	public void turistaEguneratu(TuristaErregistratua t, Booking b) throws RemoteException;

	public void iruzkindu(String t, TuristaErregistratua erab, RuralHouse r)throws RemoteException;

	public void BalorazioaGehitu(RuralHouse rh, int bal)throws RemoteException;
	
	public LinkedList<Iruzkina> etxearenIruzkinakLortu(RuralHouse rh)throws RemoteException;
	
	public Vector<RuralHouse> rankingaLortu()throws RemoteException;
	
	public float balorazioaGehitu(RuralHouse r)throws RemoteException;
	
	public LinkedList<Iruzkina> JabearenEtxeenIruzkinakLortu(Owner o)throws RemoteException;
	
	public void salatu(Owner o,String zergatia, Iruzkina I)throws RemoteException;
	
	public Vector<Salaketa> salaketakLortu()throws RemoteException;
	
	public void salaketaOnartu(Salaketa s)throws RemoteException;
	
	public void salaketaEzeztatu(Salaketa s)throws RemoteException;
	
	public Vector getNireIruzkinak(TuristaErregistratua t)throws RemoteException;
	
	public void iruzkinaEzabatu(Iruzkina I)throws RemoteException;
	
	public boolean StoreOwner(Owner o)throws RemoteException;
	
	public boolean storeRuralHouse(RuralHouse r) throws RemoteException;

}
