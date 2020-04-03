package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
	}
		
	public List<Corso> getCorsiDiUnoStudente (Studente stud) {
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd FROM studente AS s, corso AS c, "+
							"iscrizione i WHERE c.codins=i.codins AND s.matricola=i.matricola AND s.matricola=?";
		
		List<Corso> corsiDiUnoStudente = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, stud.getMatricola());
			ResultSet r = st.executeQuery();
			
			while(r.next()) {
				Corso ci = new Corso(r.getString("codins"), r.getInt("crediti"), r.getString("nome"), r.getInt("pd"));
				corsiDiUnoStudente.add(ci);
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return corsiDiUnoStudente;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String sql="SELECT s.matricola, s.cognome, s.nome, s.CDS FROM iscrizione AS i, corso AS c, "+
						" studente AS s WHERE c.codins=i.codins AND s.matricola=i.matricola AND c.codins=?";
		
		List<Studente> studentiIscritti = new ArrayList();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Studente s = new Studente(res.getInt("matricola"), res.getString("cognome"), 
											res.getString("nome"), res.getString("CDS"));
				studentiIscritti.add(s);
			}
			conn.close();			
			
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		
		return studentiIscritti;		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		 
		String sql = "INSERT INTO iscrizione (matricola, codins) VALUE (?,?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1,studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			Integer rs = st.executeUpdate();
			
			if(rs==1) {
				return true;
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
