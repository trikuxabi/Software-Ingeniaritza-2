package domain;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class TuristaErregistratua extends Erregistratua{
	
	private String erabiltzailea;
	private String pasahitza;
	private String izena;
	private String abizenak;
	private String kontuKorrontea;
	private String postaElektronikoa;
	private LinkedList<Booking> bookings = new LinkedList<Booking>();
	
	public TuristaErregistratua(){
		super();
	}
	
	public TuristaErregistratua(String Erab,String pasahitza, String Izena, String Abizenak, String kontuKorrontea, String postaElektronkoa)
	{
		this.erabiltzailea=Erab;
		this.setIzena(Izena);
		this.setAbizenak(Abizenak);
		this.setKontuKorrontea(kontuKorrontea);
		this.setPostaElektronikoa(postaElektronkoa);
		this.pasahitza=pasahitza;
	}
	
	public Iruzkina iruzkinaSortu(String text)
	{
		return new Iruzkina(text, this);
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizenak() {
		return abizenak;
	}

	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}

	public String getKontuKorrontea() {
		return kontuKorrontea;
	}

	public void setKontuKorrontea(String kontuKorrontea) {
		this.kontuKorrontea = kontuKorrontea;
	}

	public String getPostaElektronikoa() {
		return postaElektronikoa;
	}

	public void setPostaElektronikoa(String postaElektronikoa) {
		this.postaElektronikoa = postaElektronikoa;
	}

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}
	
	public String getPasahitza() {
		return this.pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
	
	public void inpr()
	{
		System.out.println();
		System.out.println(this.erabiltzailea+" ren datuak");
		System.out.println();
		System.out.println(this.erabiltzailea);
		System.out.println(this.pasahitza);
		System.out.println(this.izena);
		System.out.println(this.abizenak);
		System.out.println(this.kontuKorrontea);
		System.out.println(this.postaElektronikoa);
	}

	public LinkedList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(LinkedList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public String toString(){
		return this.izena + " " + this.erabiltzailea + " " + this.abizenak;
	}
	
	public int probatuIzena(){
		if (Pattern.matches("[A-Za-z]+", this.izena)){
			return 1111;
		}
		return 0;
	}
	
	public int probatuAbizena(){
		if (Pattern.matches("[A-Za-z]+", this.abizenak)){
			return 2222;
		}
		return 0;
	}
	
	public int probatuEmaila(){
		if(Pattern.matches("[A-Za-z._0-9]+@[a-z0-9]+.[a-z]+", this.postaElektronikoa)){
			return 3333;
		}
		return 0;
	}
	
	public int probatuPasahitza(){
		if(Pattern.matches("[0-9]*[A-Za-z!_.]{1,}[0-9]*", this.pasahitza)){
			return 4444;
		}
		return 0;
	}
	
	public int probatuKontuKorrontea(){
		if(Pattern.matches("[0-9]{16}", this.kontuKorrontea)){
			return 5555;
		}
		return 0;
	}
	
	public int probatuErabiltzailea(){
		if(Pattern.matches("[A-Za-z._!0-9]+[@]*[A-Za-z._!0-9]+", this.erabiltzailea)){
			return 6666;
		}
		return 0;
	}
}
