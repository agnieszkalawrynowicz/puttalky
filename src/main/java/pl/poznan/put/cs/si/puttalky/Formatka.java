package pl.poznan.put.cs.si.puttalky;

/** Author: agalawrynowicz<br>
 * Date: 19-Dec-2016 */

public abstract class Formatka {

	private String nazwa;
	private String wartosc;
	private String monit;
	private String brakDopasowania;
	private String brakWypowiedzi;
	

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getWartosc() {
		return wartosc;
	}

	public void setWartosc(String wartosc) {
		this.wartosc = wartosc;
	}

	public String getMonit() {
		return monit;
	}

	public void setMonit(String monit) {
		this.monit = monit;
	}

	public String getBrakDopasowania() {
		return brakDopasowania;
	}

	public void setBrakDopasowania(String brakDopasowania) {
		this.brakDopasowania = brakDopasowania;
	}

	public String getBrakWypowiedzi() {
		return brakWypowiedzi;
	}

	public void setBrakWypowiedzi(String brakWypowiedzi) {
		this.brakWypowiedzi = brakWypowiedzi;
	}
	
	public void zadajPytanie() {
	}

}
