package dataAccess;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

import configuration.ConfigXML;
import domain.Administratzailea;
import domain.Booking;
import domain.Erabiltzaile;
import domain.Iruzkina;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.Salaketa;
import domain.TuristaErregistratua;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;

public class DataAccessCommon implements DataAccessInterface {
	protected static ObjectContainer  db;
	private int bookingNumber=0; // if it is "static" then it is not serialized
	private int offerNumber=0;   // if it is "static" then it is not serialized

	protected static DB4oManagerAux theDB4oManagerAux;
	ConfigXML c;

	public DataAccessCommon()  {
		theDB4oManagerAux=new DB4oManagerAux(0,0);
		c=ConfigXML.getInstance();
		System.out.println("Creating DB4oManager instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
	}



	class DB4oManagerAux {
		int bookingNumber;
		int offerNumber;
		DB4oManagerAux(int bookingNumber,int offerNumber){
			this.bookingNumber=bookingNumber;
			this.offerNumber=offerNumber;
		}
	}




	public void initializeDB(){

		Owner jon = new Owner("Jon", "Jonlog", "passJon");
		Owner alfredo = new Owner("Alfredo","AlfredoLog", "passAlfredo");
		Owner jesus = new Owner("Jes�s", "Jesuslog", "passJesus");
		Owner josean = new Owner("Josean","JoseanLog", "passJosean");

		jon.addRuralHouse(1, "Ezkioko etxea","Ezkio");
		jon.addRuralHouse(2, "Etxetxikia","Iru�a");
		jesus.addRuralHouse(3, "Udaletxea","Bilbo");
		josean.addRuralHouse(4, "Gaztetxea","Renteria");

		jon.setBankAccount("12345677");
		alfredo.setBankAccount("77654321");
		jesus.setBankAccount("12344321");
		josean.setBankAccount("43211234");

		db.store(jon);
		db.store(alfredo);
		db.store(jesus);
		db.store(josean);

		db.commit();

	}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {

		try {

			//if (c.isDatabaseLocal()==false) openObjectContainer();


			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,null,null);
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			RuralHouse rh=result.next();
			Offer o=rh.createOffer(theDB4oManagerAux.offerNumber++,firstDay, lastDay, price);
			db.store(theDB4oManagerAux); // To store the new value for offerNumber
			db.store(o);
			db.commit(); 
			return o;
		}
		catch (com.db4o.ext.ObjectNotStorableException e){
			System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createOffer");
			return null;
		}
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

		try {

			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity());
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			RuralHouse rh=result.next();

			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer!=null) {
				offer.createBooking(theDB4oManagerAux.bookingNumber++, bookTelephoneNumber);
				db.store(theDB4oManagerAux); // To store the new value for bookingNumber
				db.store(offer);
				db.commit();
				return offer.getBooking();
			}
			return null;

		} catch (com.db4o.ext.ObjectNotStorableException e){
			System.out.println("Error: com.db4o.ext.ObjectNotStorableException in createBooking");
			return null;
		}
		catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}


	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners()  {


		//if (c.isDatabaseLocal()==false) openObjectContainer();

		try {
			Owner proto = new Owner(null,null,null,null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners=new Vector<Owner>();
			while(result.hasNext())				 
				owners.add(result.next());
			return owners;
		} finally {
			//db.close();
		}
	} 
	public Vector<RuralHouse> getAllRuralHouses() {

		//if (c.isDatabaseLocal()==false) openObjectContainer();

		try {
			RuralHouse proto = new RuralHouse(0,null,null,null);
			List<RuralHouse> result = db.queryByExample(proto);
			Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			for (int i = 0; i<result.size(); i++) ruralHouses.add(result.get(i));
			return ruralHouses;
		} finally {
			//db.close();
		}
	}

	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws  OverlappingOfferExists{
		try {
			//if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse rhn = (RuralHouse) db.queryByExample(new RuralHouse(rh.getHouseNumber(),null,null,null)).next();		
			if (rhn.overlapsWith(firstDay, lastDay)!=null) throw new OverlappingOfferExists();
			else return false; 
		} finally {
			//db.close();
		}
	}



	public boolean storeTuristaErregistratua(TuristaErregistratua turistaErregistratua) {
		//Erabiltzailea errepikatzen bada, false itzuliko du bestela true.

		TuristaErregistratua probatzeko = new TuristaErregistratua(turistaErregistratua.getErabiltzailea(), null, null, null, null, null);
		List<TuristaErregistratua> result=db.queryByExample(probatzeko);

		Owner proba2 = new Owner(null, turistaErregistratua.getErabiltzailea(),null,null);
		List<Owner> result2 = db.queryByExample(proba2);

		Administratzailea proba3 = new Administratzailea(turistaErregistratua.getErabiltzailea(), null);
		List<Administratzailea> result3 = db.queryByExample(proba3);

		if(result.isEmpty()==false || result2.isEmpty()==false || result3.isEmpty()==false ){
			System.out.println("Izen errepikakorra ("+turistaErregistratua.getErabiltzailea()+")");
			return false;
		}

		else
		{			
			db.store(turistaErregistratua);
			db.commit();
			System.out.println("Gordeta " + turistaErregistratua.getErabiltzailea());
			return true;
		}
	}

	public TuristaErregistratua getTuristaErregistratua(String erabiltzailea) {
		//null itzuliko du, datu basean ez badago izen hori duen erabiltzailerik
		TuristaErregistratua probatzeko = new TuristaErregistratua(erabiltzailea, null, null, null, null, null);
		List<TuristaErregistratua> result =  db.queryByExample(probatzeko);
		if(result.isEmpty())
		{
			System.out.println("Erabiltzailea ez da existitzen (getTurista)");
			return null;
		}
		else return result.get(0);//bakarra izango denez, 0garren posizioan egongo da.
	}

	public void storeAdministratzailea(Administratzailea a) {

		db.store(a);
		db.commit();
		System.out.println("administratzailea gehitu da.");
	}

	public boolean AdministratzaileaAlDa(String erabiltzailea, String pasahitza)
	{

		Administratzailea probatzeko = new Administratzailea(erabiltzailea, pasahitza);
		List<Administratzailea> result =  db.queryByExample(probatzeko);
		if(result.isEmpty())
		{
			//Ez badago administratzailerik izen eta pasahitz horrekin zerrenda hutza egongo da, beraz ez da administratzailea.
			return false;
		}
		else return true;

	}


	public boolean AdministratzaileaBaAldago()//Datu basean adminitratzailerik ba dagoen begiratzeko.
	{

		List<Administratzailea> result = db.queryByExample(Administratzailea.class);
		if(result.isEmpty()){
			return false;			
		}
		else{
			return true;
		}
	}

	public boolean StoreOwner(Owner o)
	{

		Owner proto = new Owner(null, o.getLogin(), null, null);
		List<Owner> result=db.queryByExample(proto);

		TuristaErregistratua probatzeko = new TuristaErregistratua(o.getLogin(), null, null, null, null, null);
		List<TuristaErregistratua> result2=db.queryByExample(probatzeko);

		Administratzailea proba3 = new Administratzailea(o.getLogin(), null);
		List<Administratzailea> result3 = db.queryByExample(proba3);


		if(result.isEmpty()==false || result2.isEmpty()==false || result3.isEmpty()==false){
			System.out.println("Izen errepikakorra ("+o.getLogin()+")");
			return false;
		}

		else
		{			
			db.store(o);
			db.commit();
			System.out.println("Gordeta " + o.getLogin());
			return true;
		}
	}

	public boolean jabeaAlDa(String erab, String pas)
	{
		Owner o=new Owner(null, erab, pas, null);
		List<Owner> result = db.queryByExample(o);
		if(result.isEmpty()){
			return false;			
		}
		else{
			return true;
		}
	}
	public Owner getOwner(String erab)
	{
		Owner o=new Owner(null, erab, null, null);
		List<Owner> result = db.queryByExample(o);
		if(result.isEmpty()){
			return null;			
		}
		else{
			return result.get(0);
		}
	}


	public boolean storeRuralHouse(RuralHouse r)
	{
		RuralHouse proba = new RuralHouse(r.getHouseNumber(), null, null, null);
		List<RuralHouse> result = db.queryByExample(proba);
		if(!result.isEmpty())
		{
			System.out.println("Izen errepikakorra ("+r.getHouseNumber()+")");
			return false;
		}
		else
		{
			List<Owner> result2 = db.queryByExample(r.getOwner());	
			result2.get(0).getRuralHouses().add(r);
			db.store(result2.get(0));
			db.commit();
			System.out.println("Gordeta " + r.getHouseNumber());
			return true;
		}
	}

	public boolean landetxeaEditatu(Owner o, int houseNumber, String description, String city)
	{
		RuralHouse proba = new RuralHouse(houseNumber, null, null, null);
		List<RuralHouse> result = db.queryByExample(proba);
		System.out.println(":::::::::::::::::"+result.size());
		result.get(0).setCity(city);
		result.get(0).setDescription(description);
		db.store(result.get(0));
		db.commit();
		return true;
	}

	public void jabeariLandetxeaGehitu(Owner o, RuralHouse r)
	{
		db.delete(r);
		db.delete(o);
		o.addRuralHouse2(r);
		db.store(o);
	}

	public void jabeariLandetxeaKendu(Owner o, RuralHouse r){
		db.delete(r);
		db.delete(o);
		o.getRuralHouses().remove(r);
		db.store(o);
	}

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public String toString() {
		return "bookingNumber="+bookingNumber+" offerNumber="+offerNumber;
	}

	public LinkedList<Owner> getAllOwner()
	{
		LinkedList<Owner> l2=new LinkedList<Owner>();
		List<Owner> l= db.queryByExample(Owner.class);
		for(int i=0;i<l.size();i++)l2.add(l.get(i));
		return l2;
	}
	public void jabeaEzabatu(Owner o)
	{
		db.delete(o);
		System.out.println(o.getName()+" ezabatu da");
	}

	public void deleteOffer(Offer o)
	{
		db.delete(o);
	}


	public void LandetxeaEzabatu(RuralHouse r) {

		db.delete(r);
	}

	public void turistaEguneratu(TuristaErregistratua t, Booking b)
	{
		db.delete(t);
		t.getBookings().add(b);
		db.store(t);
	}

	public void landetxeaEguneratu(RuralHouse r) {

		db.store(r);
		System.out.println("eguneraketa burutu da");
	}

	@Override
	public void salaketaGorde(Salaketa s) {

		db.store(s);
		db.commit();
		System.out.println("Salaketa gorde da");
	}


	public Vector<Salaketa> getSalaketak() {

		Vector<Salaketa> v=new Vector<Salaketa>();
		List<Salaketa> l= db.queryByExample(Salaketa.class);
		for(int i=0; i<l.size(); i++)v.add(l.get(i));
		return v;
	}

	public void salaketaOnartu(Salaketa s) {

		//List<Salaketa> salaketa =  db.queryByExample(s);
		db.delete(s);
		db.delete(s.getI());
		System.out.println("Salaketa eta iruzkina ezabatu dira");

	}


	public void salaketaEzeztatu(Salaketa s) {

		db.delete(s);
		System.out.println("Salaketa ezabatu da");

	}

	@Override
	public Vector<Iruzkina> getNireIruzkinak(TuristaErregistratua t) {

		Vector<Iruzkina> v=new Vector<Iruzkina>();
		Iruzkina iruz = new Iruzkina(null, t);
		List<Iruzkina> l= db.queryByExample(iruz);
		for(int i=0; i<l.size(); i++)v.add(l.get(i));
		return v;

	}


	public void iruzkinaEzabatu(Iruzkina I) {

		db.delete(I);
		System.out.println("Iruzkina ezabatu da");
	}


	public LinkedList<Iruzkina> getLandetxearenIruzkinak(RuralHouse r) {


		List<RuralHouse> l= db.queryByExample(r);	

		if (l.get(0)!=null)
			return l.get(0).getIruzkinak();
		else return null;
	}

}
