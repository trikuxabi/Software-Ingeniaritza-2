package domain;

public class Administratzailea extends Erregistratua{

	private String Erab;
	private String pasahitza;
	
	public Administratzailea(String e, String p)
	{
		this.Erab=e;
		this.pasahitza=p;
	}
	

	public String getErab() {
		return Erab;
	}

	public void setErab(String erab) {
		Erab = erab;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
	
}