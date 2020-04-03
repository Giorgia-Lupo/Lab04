package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */

		System.out.println(model.getTuttiICorsi());
		
		for(Corso c : model.getTuttiICorsi()) {
			if(c.getCodins().equals("01KSUPG"))
				System.out.println(model.getStudentiIscrittiAlCorso(c));
		}
		
		System.out.println("++++++++++++\n");
		
		String aa="";
		for(Studente s : model.tuttiGliStudenti()) {
			aa+=s+"\n";
		}
		System.out.println(aa);
		
		System.out.println("++++++++++++\n");
		
		for(Studente s : model.tuttiGliStudenti()) {
			if(s.getMatricola()==161245) {
				System.out.println(model.getCorsiDiUnoStudente(s));
			}
		}
		
	}

}
