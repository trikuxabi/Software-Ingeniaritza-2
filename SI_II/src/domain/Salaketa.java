package domain;

public class Salaketa  {

	private String Zergatia;
	private Iruzkina I;
	private Owner O;
	
	public Salaketa(String zergatia, Iruzkina I, Owner O)
	{
		this.I=I;
		this.Zergatia=zergatia;
		this.O=O;
		
	}

	public String getZergatia() {
		return Zergatia;
	}

	public void setZergatia(String zergatia) {
		Zergatia = zergatia;
	}

	public Iruzkina getI() {
		return I;
	}

	public void setI(Iruzkina i) {
		I = i;
	}

	public Owner getO() {
		return O;
	}

	public void setO(Owner o) {
		O = o;
	}
	public String toString(){
		
		return "[Iruzkina]: "+this.I.getTestua()+" [Zergatia]: "+this.Zergatia;
		
	}
	
}
