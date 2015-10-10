package domain;

public class ErregistratuGabea extends Erabiltzaile{

	public TuristaErregistratua erregistratu(String Erabiltzaile,String Pasahitza, String Izena, String Abizenak, String kontuKorrontea, String postaElektronkoa){
		
		TuristaErregistratua erregistratua=new TuristaErregistratua(Erabiltzaile,Pasahitza,Izena,Abizenak,kontuKorrontea,postaElektronkoa);
		return erregistratua;
	}
	
	public Erregistratua saioaHasi(String Erabiltzailea, String Pasahitza)
	{	
		Erregistratua erregistratua = null;
	//	Erregistratua erregistratua = new TuristaErregistratua();
		return erregistratua;
	}
	
	
}
