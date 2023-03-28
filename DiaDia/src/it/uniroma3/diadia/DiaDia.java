package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "inventario"};

	private Partita game;
	private IOConsole console;

	public DiaDia(IOConsole IOC) {
		
		this.console = IOC;
		this.game = new Partita();
		
	}

	public IOConsole getConsole() {
		return console;
	}

	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.sconosciuto()) {
			console.mostraMessaggio("Devi digitare un comando");
			return false;
		}			

		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("guarda"))
			this.guarda();
		else if (comandoDaEseguire.getNome().equals("inventario"))
			this.inventario();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.game.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa le informazioni, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		
		if(direzione==null) {
			console.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		
		Stanza prossimaStanza = this.game.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
		    
		else {
			this.game.setStanzaCorrente(prossimaStanza);
			this.game.setCfu((this.game.getCfu()-1));
		}
		console.mostraMessaggio("CFU rimanenti: " + this.game.getCfu());
		console.mostraMessaggio("-----------------");
		console.mostraMessaggio(game.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Stampa informazioni sulla stanza corrente
	 */
	private void guarda() 
	{
		console.mostraMessaggio(this.game.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Stampa informazioni sulla borsa e quanti CFU possiede il giocatore
	 */
	private void inventario() 
	{
		console.mostraMessaggio("CFU rimanenti: " + this.game.getCfu());
		console.mostraMessaggio("-----------------");
		console.mostraMessaggio(this.game.getPlayer().getBorsa().getDescrizione());
	}

	/**
	 * Cerca di prendere un attrezzo da terra, messaggio di errore se
	 * l'attrezzo digitato non è a terra, o se non si digita nessun
	 * attrezzo dopo il comando
	 * @param nomeAttrezzo
	 */
	private void prendi(String nomeAttrezzo)
	{
		if (nomeAttrezzo == null)
		{
			console.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		Attrezzo attrezzoCorrente = this.game.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCorrente != null)
		{
			int peso = attrezzoCorrente.getPeso();
			if (this.game.getPlayer().getBorsa().addAttrezzo(attrezzoCorrente))
			{
				console.mostraMessaggio(attrezzoCorrente.getNome() + " preso/a e messo/a in borsa");
				this.game.getStanzaCorrente().removeAttrezzo(attrezzoCorrente);
			}
			else if (this.game.getPlayer().getBorsa().getPeso() + peso > this.game.getPlayer().getBorsa().getPesoMax()) 
			{
				console.mostraMessaggio("Il peso è eccessivo!");
				return;
			}
			else 
			{
				console.mostraMessaggio("Borsa piena!");
				return;
			}
		}		
		else
			console.mostraMessaggio(nomeAttrezzo + " non è nella stanza!");
	}

	/**
	 * Cerca di posare a terra un attrezzo presente nella borsa, messaggio di 
	 * errore se l'attrezzo digitato non è posseduto, 
	 * o se non si digita nessun attrezzo dopo il comando
	 * @param nomeAttrezzo
	 */
	private void posa(String nomeAttrezzo)
	{
		if (nomeAttrezzo == null)
		{
			console.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		
		Attrezzo attrezzoCorrente = this.game.getPlayer().getBorsa().getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCorrente != null)
		{
			if (this.game.getStanzaCorrente().addAttrezzo(attrezzoCorrente))
			{
				console.mostraMessaggio(attrezzoCorrente.getNome() + " posato/a a terra");
				this.game.getPlayer().getBorsa().removeAttrezzo(nomeAttrezzo);
			}
			else 
				console.mostraMessaggio("Stanza piena!");
		}
		else
			console.mostraMessaggio(nomeAttrezzo +" non è nella borsa!");
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] args) {
		
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
		
	}
}