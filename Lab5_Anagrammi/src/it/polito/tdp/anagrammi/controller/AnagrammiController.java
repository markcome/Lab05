package it.polito.tdp.anagrammi.controller;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnagrammiController {
	
	private Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

    @FXML
    private Button btnReset;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    	
    	this.txtCorretti.setDisable(false);
    	this.txtErrati.setDisable(false);
    	this.btnReset.setDisable(false);
    	
    	this.txtParola.setEditable(false);
    	this.txtParola.setDisable(true);
    	this.btnCalcola.setDisable(true);
    	
    	
    	for (String s: model.getAnagrammiGiusti(this.txtParola.getText().trim())) {
    		this.txtCorretti.appendText(s + "  ");
    	}
    	
    	for (String s: model.getAnagrammiSbagliati(this.txtParola.getText().trim())) {
    		this.txtErrati.appendText(s + "  ");
    	}
    }

    @FXML
    void doReset(ActionEvent event) {

    	this.txtParola.clear();
    	this.txtCorretti.clear();
    	this.txtErrati.clear();
    	
    	this.txtCorretti.setDisable(true);
    	this.txtErrati.setDisable(true);
    	this.btnReset.setDisable(true);
    	
    	this.txtParola.setEditable(true);
    	this.txtParola.setDisable(false);
    	this.btnCalcola.setDisable(false);
    	
    	this.model.clear();
    }
    

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Anagrammi.fxml'.";
    }

}

