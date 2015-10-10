package domain;

import java.util.LinkedList;

public class Iruzkina {

	private String testua;
	private TuristaErregistratua erab;
	
	public Iruzkina(String text,TuristaErregistratua erab)
	{
		testua=text;
		this.erab=erab;
		
	}

	public String getTestua() {
		return testua;
	}

	public void setTestua(String testua) {
		this.testua = testua;
	}

	public TuristaErregistratua getErab() {
		return erab;
	}

	public void setErab(TuristaErregistratua erab) {
		this.erab = erab;
	}
	
	public String toString(){
		return this.erab + ": " + testua;
	}
	
	
	
	
}
