package pl.poznan.put.cs.si.puttalky;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

/** Author: agalawrynowicz<br>
 * Date: 19-Dec-2016 */

public class BazaWiedzy {

    private OWLOntologyManager manager = null;
    private OWLOntology ontologia;
    private Set<OWLClass> listaKlas;
    private Set<OWLClass> listaDodatkow;
    private Set<OWLClass> listaPizz;
    
    OWLReasoner silnik;
    
    public void inicjalizuj() {
		InputStream plik = this.getClass().getResourceAsStream("/pizza.owl");
		manager = OWLManager.createOWLOntologyManager();
		
		try {
			ontologia = manager.loadOntologyFromOntologyDocument(plik);
			silnik = new Reasoner.ReasonerFactory().createReasoner(ontologia);
			listaKlas = ontologia.getClassesInSignature();
			listaDodatkow = new HashSet<OWLClass>();
			listaPizz = new HashSet<OWLClass>();
			
			OWLClass dodatek  = manager.getOWLDataFactory().getOWLClass(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#Dodatek"));
			for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: silnik.getSubClasses(dodatek, false)) {
				listaDodatkow.add(klasa.getRepresentativeElement());
			}
			
			OWLClass pizza  = manager.getOWLDataFactory().getOWLClass(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#Pizza"));
			for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: silnik.getSubClasses(pizza, false)) {
				listaPizz.add(klasa.getRepresentativeElement());
			}
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
    }
    
    public Set<String> dopasujDodatek(String s){
	    	Set<String> result = new HashSet<String>();
	    	for (OWLClass klasa : listaDodatkow){
	    		if (klasa.toString().toLowerCase().contains(s.toLowerCase()) && s.length()>2){
	    			result.add(klasa.getIRI().toString());
	    		}
	    	}
	    	
	    	return result;
    }
    
    public Set<String> dopasujPizze(String s){
    		Set<String> result = new HashSet<String>();
	    	
	    	if(s.contains("mies") || s.contains("mięso")) {
	    		OWLClass pizza  = manager.getOWLDataFactory().getOWLClass(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#PizzaZMięsem"));
    			for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: silnik.getSubClasses(pizza, true)) {
    				result.add(klasa.getRepresentativeElement().getIRI().getShortForm());
    			}
	    	} else if(s.contains("wegetaria")) {
	    		OWLClass pizza  = manager.getOWLDataFactory().getOWLClass(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#PizzaWegetariańska"));
    			for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: silnik.getSubClasses(pizza, true)) {
    				result.add(klasa.getRepresentativeElement().getIRI().getShortForm());
    			}
	    	} else {
	    		for (OWLClass klasa : listaPizz) {
		    		if (!Objects.equals(s, "pizza") && klasa.toString().toLowerCase().contains(s.toLowerCase()) && s.length()>2){
		    			result.add(klasa.getIRI().toString());
		    		}
		    	}
	    	}
	    	
	    	return result;
	}
    
    public Set<String> wyszukajPizzePoDodatkach(ArrayList<String> iriList){
	    	Set<String> pizze = new HashSet<String>();
	    	OWLObjectProperty maDodatek = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#maDodatek"));
	    	Set<OWLClassExpression> ograniczeniaEgzystencjalne = new HashSet<OWLClassExpression>();
	    	
	    	for (String iri : iriList) {
	    		OWLClass dodatek = manager.getOWLDataFactory().getOWLClass(IRI.create(iri));
	    		OWLClassExpression wyrazenie = manager.getOWLDataFactory().getOWLObjectSomeValuesFrom(maDodatek, dodatek);
	    		ograniczeniaEgzystencjalne.add(wyrazenie);
	    	}
	  	
	    	OWLClassExpression pozadanaPizza = manager.getOWLDataFactory().getOWLObjectIntersectionOf(ograniczeniaEgzystencjalne);
    	
		for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: silnik.getSubClasses(pozadanaPizza, false)) {
			pizze.add(klasa.getEntities().iterator().next().asOWLClass().getIRI().getShortForm());
		}
	
		return pizze;
    }
	
	public static void main(String[] args) {
		BazaWiedzy baza = new BazaWiedzy();
		baza.inicjalizuj();
		
		OWLClass mieso = baza.manager.getOWLDataFactory().getOWLClass(IRI.create("http://semantic.cs.put.poznan.pl/ontologie/pizza.owl#DodatekMięsny"));
		for (org.semanticweb.owlapi.reasoner.Node<OWLClass> klasa: baza.silnik.getSubClasses(mieso, true)) {
			System.out.println("klasa:"+klasa.toString());
		}
		for (OWLClass d:  baza.listaDodatkow){
			System.out.println("dodatek: "+d.toString());
		}
		for (OWLClass d:  baza.listaPizz){
			System.out.println("pizza: "+d.toString());
		}
	}
	
	public String pizzaName(String uri) {
		String wybrana_pizza = IRI.create(uri).getShortForm();
		return wybrana_pizza;
	}

}
