package dataAccess;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

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

public interface DataAccessInterface {
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price);

	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked;

	public Vector<Owner> getOwners();
	
	public Vector<RuralHouse> getAllRuralHouses();

	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws OverlappingOfferExists;

	public void close();

	public TuristaErregistratua getTuristaErregistratua(String erab);

	public boolean storeTuristaErregistratua(TuristaErregistratua t);

	public boolean AdministratzaileaBaAldago();
	
	public void storeAdministratzailea(Administratzailea a);
	
	public boolean AdministratzaileaAlDa(String erabiltzailea, String pasahitza);
	
	public boolean StoreOwner(Owner o);
	
	public boolean jabeaAlDa(String erab, String pas);
	
	public boolean storeRuralHouse(RuralHouse r);
	
	public Owner getOwner(String erab);
	
	public void jabeariLandetxeaGehitu(Owner o, RuralHouse r);
	
	public void jabeariLandetxeaKendu(Owner o, RuralHouse r);
	
	public boolean landetxeaEditatu(Owner o, int houseNumber, String description, String city);
	
	public LinkedList<Owner> getAllOwner();
	
	public void jabeaEzabatu(Owner o);
	
	public void deleteOffer(Offer o);

	public void LandetxeaEzabatu(RuralHouse r);
	
	public void turistaEguneratu(TuristaErregistratua t,Booking b);
	
	public void landetxeaEguneratu(RuralHouse r);

	public void salaketaGorde(Salaketa s);
	
	public Vector<Salaketa> getSalaketak();
	
	public void salaketaOnartu(Salaketa s);
	
	public void salaketaEzeztatu(Salaketa s);
	
	public Vector<Iruzkina> getNireIruzkinak(TuristaErregistratua t);
	
	public void iruzkinaEzabatu(Iruzkina I);
	
	public LinkedList<Iruzkina> getLandetxearenIruzkinak(RuralHouse r);
}