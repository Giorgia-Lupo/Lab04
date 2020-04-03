package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO cDao;
	private StudenteDAO sDao;

	public Model() {
		cDao = new CorsoDAO();
		sDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.cDao.getTuttiICorsi();		
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return this.cDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Studente> tuttiGliStudenti (){
		return this.sDao.tuttiGliStudenti();
	}
	
	public Studente getStudente(Studente studente) {
		return this.sDao.getStudente(studente);
	}
	
	public List<Corso> getCorsiDiUnoStudente (Studente stud) {
		return this.cDao.getCorsiDiUnoStudente(stud);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return this.cDao.inscriviStudenteACorso(studente, corso);
	}
	
}
	
	
	
	


