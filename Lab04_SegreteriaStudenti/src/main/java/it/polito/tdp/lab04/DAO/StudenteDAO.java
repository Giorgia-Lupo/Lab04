package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> tuttiGliStudenti (){
		
		String sql = "SELECT * FROM studente";
		List<Studente> studenti = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				studenti.add(s);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return studenti;
	}
	
	public Studente getStudente(Studente studente) {
		
		String sql="SELECT s.matricola, s.cognome, s.nome, s.CDS FROM studente AS s WHERE s.matricola=?";		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, studente.getMatricola());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				studente.setNome(res.getString("nome"));
				studente.setCognome(res.getString("cognome"));
				studente.setCDS(res.getString("CDS"));
			}
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return studente;
	}

}
