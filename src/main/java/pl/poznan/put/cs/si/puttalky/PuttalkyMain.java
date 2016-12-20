package pl.poznan.put.cs.si.puttalky;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/** Author: agalawrynowicz<br>
 * Date: 19-Dec-2016 */

public class PuttalkyMain {

	public static int liczba=0;
	
    public static final void main(String[] args) {
        try {
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

        	
            Zamowienie zamowienie = new Zamowienie();
            Ciasto ciasto = new Ciasto();
            Dodatek dodatek = new Dodatek();
            Pizza pizza = new Pizza();
			pizza.setBrakDopasowania("Nie mamy takiej pizzy w ofercie"); 

            Parser parser = new Parser();
            BazaWiedzy baza = new BazaWiedzy(); 
            baza.inicjalizuj();
            
            Fakt fakt = new Fakt();
            fakt.setNazwa("");
            fakt.setWartosc("-1");
            kSession.insert(fakt);
            kSession.insert(zamowienie);
            kSession.insert(ciasto);
            kSession.insert(dodatek);
            kSession.insert(pizza);
            kSession.insert(baza);
            kSession.insert(parser);
            
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
