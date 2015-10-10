package businessLogic;

import java.awt.dnd.DnDConstants;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;
import java.sql.SQLException;
import java.util.Vector;

import dataAccess.DataAccessInterface;
import domain.Administratzailea;
import domain.Balorazioa;
import domain.Booking;
import domain.Erabiltzaile;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.Salaketa;
import domain.TuristaErregistratua;
import exceptions.BadDates;
import exceptions.DB4oManagerCreationException;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;



public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vector<Owner> owners;
	Vector<RuralHouse> ruralHouses;
	DataAccessInterface dB4oManager;


	public FacadeImplementation() throws RemoteException, InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException, DB4oManagerCreationException {

	}


	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates, RemoteException, Exception {
		if (firstDay.compareTo(lastDay)>=0) throw new BadDates();
		ruralHouses=null;
		owners=null;
		boolean b = dB4oManager.existsOverlappingOffer(ruralHouse,firstDay,lastDay); // The ruralHouse object in the client may not be updated
		if (!b) return dB4oManager.createOffer(ruralHouse,firstDay,lastDay,price);			
		return null;
	}

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {
		ruralHouses=null;
		owners=null;
		return dB4oManager.createBooking(ruralHouse,firstDate,lastDate,bookTelephoneNumber);
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
	Exception {

		if (owners!=null) { System.out.println("Owners obtained directly from business logic layer");
		return owners; }
		else return owners=dB4oManager.getOwners();
	}

	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {

		//		if (ruralHouses!=null) { System.out.println("RuralHouses obtained directly from business logic layer");
		//		return ruralHouses; }
		//		else{
		ruralHouses=dB4oManager.getAllRuralHouses();
		return ruralHouses;
		//	}

	}

	public void close() throws RemoteException{
		dB4oManager.close();

	}


	@Override
	public void setDataAccess(DataAccessInterface dai) throws RemoteException {
		dB4oManager=dai;
		// TODO Auto-generated method stub

	}

	public int erabiltzaileaEgiaztatu(String erab, String pas) throws RemoteException{

		if(dB4oManager.AdministratzaileaAlDa(erab, pas))
		{
			return 1; //administratzailea da.
		}
		if(dB4oManager.jabeaAlDa(erab,pas)){

			return 2;	
		}

		TuristaErregistratua tur=(TuristaErregistratua)dB4oManager.getTuristaErregistratua(erab);
		if(tur==null)System.out.println("Errorea");
		else
		{

			if (erab.compareTo(tur.getErabiltzailea())==0 && pas.compareTo(tur.getPasahitza())==0)return 3;//turistaErregistratua da.

		}

		return 0; //ez da erabiltzailea
	}

	public boolean storeTuristaErregistratua(TuristaErregistratua t)
	{
		if (dB4oManager.storeTuristaErregistratua(t)) return true;
		else return false;
	}

	public void administratzaileaDagoenBegiratu()
	{
		if(!dB4oManager.AdministratzaileaBaAldago())
		{
			Administratzailea a= new Administratzailea("Admin", "batman");
			dB4oManager.storeAdministratzailea(a);
		}
	}
	public boolean sortuEtaStoreOwner(String name, String login, String password, String bankAccount)
	{
		Owner o = new Owner(name, login, password, bankAccount);
		if (dB4oManager.StoreOwner(o)) return true;
		else return false;

	}
	
	public boolean StoreOwner(Owner o)
	{
		if (dB4oManager.StoreOwner(o)) return true;
		else return false;

	}

	public boolean sortuEtaStoreRuralHouse(Owner o, int houseNumber, String description, String city)
	{
		RuralHouse r = new RuralHouse(houseNumber, o, description, city);
		if(dB4oManager.storeRuralHouse(r)){
			//o.addRuralHouse(houseNumber, description, city);
			//dB4oManager.jabeariLandetxeaGehitu(o, r);//datu baseko jabea eguneratzeko
			return true;
		}
		else return false;
	}
	
	public boolean storeRuralHouse(RuralHouse r)
	{
		if(dB4oManager.storeRuralHouse(r)){
			//o.addRuralHouse(houseNumber, description, city);
			//dB4oManager.jabeariLandetxeaGehitu(o, r);//datu baseko jabea eguneratzeko
			return true;
		}
		else return false;
	}

	public boolean editRuralHouse(Owner o, int houseNumber, String description, String city)
	{
		dB4oManager.landetxeaEditatu(o, houseNumber, description, city);
		return true;

	}


	public Owner getOwner(String erab){
		Owner o = dB4oManager.getOwner(erab);
		return o;
	}

	public TuristaErregistratua getTuristaErregistratua(String erab){
		TuristaErregistratua t = dB4oManager.getTuristaErregistratua(erab);
		return t;
	}

	public boolean jabeariLandetxeaKendu (Owner o, RuralHouse r){
		dB4oManager.jabeariLandetxeaKendu(o, r);
		return true;
	}

	public void jabeaEzabatu(Owner o){

		dB4oManager.jabeaEzabatu(o);
	}

	public LinkedList<Owner> getAllOwner()
	{
		LinkedList<Owner> l=new LinkedList<Owner>();
		l=dB4oManager.getAllOwner();
		return l;
	}

	public void deleteOffer(Offer o)
	{
		dB4oManager.deleteOffer(o);
	}


	@Override
	public void LandetxeaEzabatu(RuralHouse r){

		dB4oManager.LandetxeaEzabatu(r);
	}

	public void turistaEguneratu(TuristaErregistratua t,Booking b)
	{
		dB4oManager.turistaEguneratu(t,b);
	}


	public void iruzkindu(String t, TuristaErregistratua erab, RuralHouse r) throws RemoteException {

		Iruzkina i=erab.iruzkinaSortu(t);
		r.getIruzkinak().add(i);
		dB4oManager.landetxeaEguneratu(r);

	}

	public void BalorazioaGehitu(RuralHouse rh, int bal)throws RemoteException {

		Balorazioa b = new Balorazioa(bal);
		rh.getBalorazioak().add(b);
		dB4oManager.landetxeaEguneratu(rh);


	}

	public LinkedList<Iruzkina> etxearenIruzkinakLortu(RuralHouse rh)throws RemoteException{

		return dB4oManager.getLandetxearenIruzkinak(rh);

	}

	@Override
	public Vector<RuralHouse> rankingaLortu() throws RemoteException {

		Vector<RuralHouse> l= new Vector<RuralHouse>();
		l=dB4oManager.getAllRuralHouses();
		for(int i=0; i<l.size(); i++)
		{
			l.get(i).setBatazbestekoBalorazioa(balorazioaGehitu(l.get(i)));
		}
		ordenatu(l);
		return l;
	}

	public float balorazioaGehitu(RuralHouse r)
	{
		float batazbeste=0;
		for(int i=0; i<r.getBalorazioak().size(); i++)batazbeste+=r.getBalorazioak().get(i).getBalorazioa();
		if(r.getBalorazioak().size()==0)batazbeste=0;
		else batazbeste=batazbeste/r.getBalorazioak().size();
		return batazbeste;
	}

	private void ordenatu(Vector<RuralHouse> l){//Hautaketa bidezko metodoa
		int out,in,min;
		for(out=0; out<l.size()-1;out++){
			min=out; // minimoaren indizea oraingoz
			for(in=out+1; in<l.size(); in++){
				if(l.get(in).getBatazbestekoBalorazioa() < l.get(min).getBatazbestekoBalorazioa()){
					min=in;
					swap(l,out,min);
				}
			}
		}
	}

	private void swap(Vector<RuralHouse> l, int one, int two){//aldagaien aldaketa egiteko soilik.


		RuralHouse lag=l.get(one);
		l.set(one,l.get(two));
		l.set(two, lag);

	}


	public LinkedList<Iruzkina> JabearenEtxeenIruzkinakLortu(Owner o)
			throws RemoteException {

		LinkedList<Iruzkina> iruzkinak =new LinkedList<Iruzkina>();

		for(int i=0;i<o.getRuralHouses().size(); i++)
		{
			LinkedList<Iruzkina> l=new LinkedList<Iruzkina>();
			l=etxearenIruzkinakLortu(o.getRuralHouses().get(i));
			for(int j=0; j<l.size(); j++)iruzkinak.add(l.get(j));
		}
		return iruzkinak;

	}


	public void salatu(Owner o, String zergatia, Iruzkina I)
			throws RemoteException {
		
		Salaketa s=o.iruzkinaSalatu(zergatia, I);
		dB4oManager.salaketaGorde(s);
		
	}


	public Vector<Salaketa> salaketakLortu() throws RemoteException {

		return dB4oManager.getSalaketak();
	}



	public void salaketaOnartu(Salaketa s) throws RemoteException {

		dB4oManager.salaketaOnartu(s);
		
	}



	public void salaketaEzeztatu(Salaketa s) throws RemoteException {

		dB4oManager.salaketaEzeztatu(s);
	}


	@Override
	public Vector getNireIruzkinak(TuristaErregistratua t)
			throws RemoteException {
		
		return dB4oManager.getNireIruzkinak(t);
	}


	
	public void iruzkinaEzabatu(Iruzkina I) throws RemoteException {

		dB4oManager.iruzkinaEzabatu(I);
	}

}

