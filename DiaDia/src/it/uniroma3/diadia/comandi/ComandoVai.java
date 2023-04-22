package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	
	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		
		IO console = partita.getConsole();
		
		if(direzione==null) {
			console.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
		    
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.setCfu((partita.getCfu()-1));
		}
		console.mostraMessaggio("CFU rimanenti: " + partita.getCfu());
		console.mostraMessaggio("-----------------");
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "ComandoVai";
	}

}
