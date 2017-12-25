package pl.poznan.put.cs.si.puttalky;

import java.util.ArrayList;

/** Author: agalawrynowicz<br>
 * Date: 19-Dec-2016 */

public class Fakt {
	
	private String nazwa;
	private String wartosc;
	private ArrayList<String> opcje = new ArrayList<String>();
	
	public Fakt(){}
	
	public Fakt(String nazwa, String wartosc)
	{
		this.nazwa=nazwa;
		this.wartosc = wartosc;
	}

	
	public String getNazwa() {
        return this.nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWartosc() {
        return this.wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }
    
    public ArrayList<String> getOpcje() {
        return this.opcje;
    }

    public void addOpcja(String opcja) {
        this.opcje.add(opcja);
    }
	
}
