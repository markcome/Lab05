package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.ParolaDAO;

public class Model {

	private ParolaDAO parolaDao;
	Set<String> anagrammi;

	
	public Model() {
		this.parolaDao = new ParolaDAO();
		this.anagrammi = new HashSet<String>();
	}
	
	public Set<String> calcolaAnagrammi (String parola) {
		String parziale = "";
		recursive(parziale, parola, 0, anagrammi);
		return anagrammi;
		
	}
	
	// Data una parola devo ritornare gli anagrammi presenti nel vocabolario
	public List<String> getAnagrammiGiusti (String parola) {
		List<String> result = new LinkedList<String>();
		
		// Controllo se ho già popolato "anagrammi"
		if (anagrammi == null) {
			this.calcolaAnagrammi(parola);
		}
		for (String anagramma: anagrammi) {
			if(this.parolaDao.isCorrect(anagramma)) {
				
			}
		}
		
		return null;	
	}
	
	// Data una parola devo ritornare gli anagrammi NON presenti nel vocabolario
	public List<String> getAnagrammiSbagliati (String parola) {
		// TODO
		return null;
	}
	
	private void recursive(String parziale, String parola, int step, Set<String> anagrammi) {
		
		// A: condizione di terminazione
		if(step == parola.length()){
			anagrammi.add(parziale);
			return;			
		}
		
		// B: genera nuova soluzione parziale
		for(int i = 1; i< parola.length(); i++){
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
}
