package pl.poznan.put.cs.si.puttalky;

/** Author: agalawrynowicz<br>
 * Date: 19-Dec-2016 */

public class Pizza extends Formatka {
	
	private String nazwa;
	private String opis; 
	
	public Pizza() {
	}
	
	public Pizza(String nazwa, String opis)
	{
		this.nazwa=nazwa;
		this.opis=opis;
	}
	
	public String getNazwa()
	{
		return this.nazwa;
	}
	
	public String getOpis()
	{
		return this.opis;
	}
	

}
