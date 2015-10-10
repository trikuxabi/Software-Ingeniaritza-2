package domain;

public abstract class Erabiltzaile {
	
	public void LandetxeakBilatu(){}
	
	public void HizkuntzaAldatu(){}
	
	public Owner jabeaSortu(String name, String login, String password, String bankAccount)
	{
		Owner o=new Owner(name, login, password, bankAccount);
		return o;
	}

}
