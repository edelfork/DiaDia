package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private Stanza stanzaTest;
	
	@Before
	public void setUp() {
		this.stanzaTest = new StanzaBloccata("StanzaTest", "nord", "chiave");
	}

	@Test
	public void testGetStanzaAdiacenteIrraggiungibile() {
		this.stanzaTest.impostaStanzaAdiacente("nord", new Stanza("Adiacente"));
		assertEquals(this.stanzaTest.getStanzaAdiacente("nord"), this.stanzaTest);
	}
	
	@Test
	public void testGetStanzaAdiacenteSenzaChiave() {
		this.stanzaTest.impostaStanzaAdiacente("nord", new Stanza("Adiacente"));
		this.stanzaTest.addAttrezzo(new Attrezzo("errato", 1));
		assertEquals(this.stanzaTest.getStanzaAdiacente("nord"), this.stanzaTest);
	}
	
	@Test
	public void testGetStanzaAdiacenteConChiave() {
		Stanza adiacente = new Stanza("adiacente");
		this.stanzaTest.impostaStanzaAdiacente("nord", adiacente);
		this.stanzaTest.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(adiacente, this.stanzaTest.getStanzaAdiacente("nord"));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneNonBloccata() {
		Stanza adiacente = new Stanza("adiacente");
		this.stanzaTest.impostaStanzaAdiacente("sud", adiacente);
		assertEquals(adiacente, this.stanzaTest.getStanzaAdiacente("sud"));
	}
}
