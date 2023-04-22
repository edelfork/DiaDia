package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	private Comando comandoPosa;
	private static String ATTREZZO = "attrezzoPerTest"; 
	
	@Before
	public void setUp() {
		this.comandoPosa = new ComandoPosa();
	}

	@Test
	public void testPosaRimuoveAttrezzoDaBorsa() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPosa.setParametro(ATTREZZO);
		this.comandoPosa.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPosaAggiungiAttrezzoInStanza() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPosa.setParametro(ATTREZZO);
		this.comandoPosa.esegui(partita);
		
		assertTrue(test.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPosaBorsaVuotaAttrezzoNonInStanza() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		partita.setStanzaCorrente(test);
		
		this.comandoPosa.setParametro(ATTREZZO);
		this.comandoPosa.esegui(partita);
		
		assertFalse(test.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testPosaBorsaConAttrezzoNonAggiuntoInStanza() {
		Partita partita = new Partita();
		Stanza test = new Stanza("stanzaDiTest");
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(ATTREZZO, 1));
		partita.setStanzaCorrente(test);
		
		this.comandoPosa.setParametro("inesistente");
		this.comandoPosa.esegui(partita);
		
		assertFalse(test.hasAttrezzo(ATTREZZO));
	}

}
