package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private Comando comandoPrendi;
	private static String ATTREZZO = "attrezzoPerTest"; 
	
	@Before
	public void setUp() {
		this.comandoPrendi = new ComandoPrendi();
	}

	@Test
	public void testPrendiRimuoveAttrezzoDaStanza() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		test.addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPrendi.setParametro(ATTREZZO);
		this.comandoPrendi.esegui(partita);
		
		assertFalse(test.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPrendiAggiungiAttrezzoInBorsa() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		test.addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPrendi.setParametro(ATTREZZO);
		this.comandoPrendi.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPrendiStanzaVuotaAttrezzoNonInBorsa() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		partita.setStanzaCorrente(test);
		
		this.comandoPrendi.setParametro(ATTREZZO);
		this.comandoPrendi.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPrendiStanzaConAttrezzoNonAggiuntoInBorsa() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		test.addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPrendi.setParametro("inesistente");
		this.comandoPrendi.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO));
	}

}
