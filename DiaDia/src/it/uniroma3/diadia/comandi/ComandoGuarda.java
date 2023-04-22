package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		
		IO console = partita.getConsole();
		
		console.mostraMessaggio("CFU rimanenti: " + partita.getCfu());
		console.mostraMessaggio("-----------------");
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio("-----------------");
		console.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {

	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "ComandoGuarda";
	}

}
