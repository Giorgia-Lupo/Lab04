package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> ComboCorsi;

    @FXML
    private Button btnIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnSelezione;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	String matr = txtMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("inserisci una matricola valida!\n");
    		return;
    	}    

    	Studente stud = new Studente(matricola, null, null, null);
    	if(this.model.tuttiGliStudenti().contains(stud))  {
    		String st="";
        	for(Corso c : this.model.getCorsiDiUnoStudente(stud)) {
        		st += c.toString()+"\n";
        		}
        	txtRisultato.appendText(st);
        	}
    	else
    		txtRisultato.appendText("Errore, non esiste la matricola!!");
    	}
    	    

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	Corso c = ComboCorsi.getValue();   
    	
    	if(c==null) {
    		txtRisultato.appendText("Devi inserire un corso!\n");
    	}
    	
    	String aa="";
    	for(Studente s : this.model.getStudentiIscrittiAlCorso(c)) {
    		aa += s.toString()+"\n";    		
    	}    
    	
    	txtRisultato.setText(aa);
    }

    @FXML
    void doCompletaNome(ActionEvent event) {
    
    	String matr = txtMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("inserisci una matricola valida!\n");
    		return;
    	}
    	
    	Studente a=null;
    	boolean trovato=false;
    	for(Studente s : this.model.tuttiGliStudenti()) {
    		if(matricola== s.getMatricola()) {
    			a=s;
    			trovato=true;
    		}    		
    	}
    	if(trovato==true) {
	    	txtNome.setDisable(false);
			txtNome.appendText(a.getNome());
			txtCognome.setDisable(false);
			txtCognome.appendText(a.getCognome());
    	}
    	else
    		txtRisultato.appendText("Non esiste uno studente con matricola: "+matricola);    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String matr = txtMatricola.getText();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(matr);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("inserisci una matricola valida!\n");
    		return;
    	}
    	
    	Studente s = new Studente(matricola, null, null, null);
    	
    	Corso c = ComboCorsi.getValue();
    	
    	if(c==null) {
    		txtRisultato.appendText("Devi inserire un corso!\n");
    	}
    	
    	boolean iscrizione = this.model.inscriviStudenteACorso(s, c);
    	if(iscrizione==true) {
    		txtRisultato.appendText("Lo studente "+s.toString()+" è stato iscritto al corso "+c.toString()+"\n");
    	}
    	else
    		txtRisultato.appendText("Lo studente "+ s.toString()+"è già iscritto al corso" + c.toString()+"\n");
    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ComboCorsi != null : "fx:id=\"ComboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscritti != null : "fx:id=\"btnIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSelezione != null : "fx:id=\"btnSelezione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsi != null : "fx:id=\"btnCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
    	this.model = model;
    	ComboCorsi.getItems().addAll(this.model.getTuttiICorsi());
    }

}
