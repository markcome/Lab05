package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.ParolaDAO;

public class Model {

	private ParolaDAO parolaDao;
	private List<String> giusti;
	private List<String> sbagliati;
	private Set<String> anagrammi;

	
	public Model() {
		this.parolaDao = new ParolaDAO();
		this.giusti = new LinkedList<String>();
		this.sbagliati = new LinkedList<String>();
		this.anagrammi = new HashSet<String>();
	}
	
	public Set<String> calcolaAnagrammi (String parola) {
		
		String parziale = "";
		recursive(parziale, parola, 0, anagrammi);
		
		// Salvo i rusultati dentro le strutture dati appropriate

		for (String anagramma: anagrammi) {
			if(this.parolaDao.isCorrect(anagramma)) {
				giusti.add(anagramma);
			} else {
				sbagliati.add(anagramma);
			}
		}	
		return anagrammi;	
	}
	
	// Data una parola devo ritornare gli anagrammi presenti nel vocabolario
	public List<String> getAnagrammiGiusti (String parola) {

		// Controllo se ho già popolato "anagrammi"
		Iterator<String> ana = anagrammi.iterator();
		if (!ana.hasNext()) {
			this.calcolaAnagrammi(parola);
		}	
		return giusti;
	}
	
	// Data una parola devo ritornare gli anagrammi NON presenti nel vocabolario
	public List<String> getAnagrammiSbagliati (String parola) {
		// Controllo se ho già popolato "anagrammi"
		Iterator<String> ana = anagrammi.iterator();
		if (!ana.hasNext()) {
			this.calcolaAnagrammi(parola);
		}	
		return sbagliati;
	}
	
	private void recursive(String parziale, String parola, int step, Set<String> anagrammi) {
		
		// A: condizione di terminazione
		if(step == parola.length()){
			if(parziale.compareTo(parola) != 0)
				anagrammi.add(parziale);
			return;			
		}
		
		// B: genera nuova soluzione parziale
		for(int i = 0; i< parola.length(); i++){
			if(charCounter(parziale, parola.charAt(i)) < charCounter(parola, parola.charAt(i))){
				parziale += parola.charAt(i);
				recursive(parziale, parola, step+1, anagrammi);
				parziale = parziale.substring(0,parziale.length()-1);
			}
		}	
	}
	
	private int charCounter(String string, char c) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == c)
				count++;
		}
		return count;
	}
	
	public void clear() {
		this.anagrammi.clear();
		this.giusti.clear();
		this.sbagliati.clear();
	}
}
