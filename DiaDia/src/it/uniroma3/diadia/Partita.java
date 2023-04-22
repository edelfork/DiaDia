package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private IO console;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto labirinth;
	private Giocatore player;
	
	public Partita(){
		
		this.console = new IOConsole();
		this.player = new Giocatore();
		this.labirinth = new Labirinto();
		this.stanzaCorrente = this.labirinth.getStanzaIniziale();
		this.finita = false;
		
	}
	
	public Labirinto getLabirinto() {
		return labirinth;
	}

	public Giocatore getGiocatore() {
		return player;
	}
	
	public int getCfu() {
		return this.player.getCfu();
	}
	
	public IO getConsole() {
		return this.console;
	}

	public Stanza getStanzaVincente() {
		return this.labirinth.getStanzaVincente();
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setGiocatore(Giocatore player) {
		this.player = player;
	}

	public void setCfu(int cfu) {
		this.player.setCfu(cfu);
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.stanzaCorrente == this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

}
