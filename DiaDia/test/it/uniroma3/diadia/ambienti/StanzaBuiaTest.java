package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private Stanza stanzaTest;
	
	@Before
	public void setUp() {
		this.stanzaTest = new StanzaBuia("StanzaTest", "lanterna");
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertEquals("qui c'è un buio pesto!", this.stanzaTest.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneAttrezzoErrato() {
		this.stanzaTest.addAttrezzo(new Attrezzo("errato", 1));
		assertEquals("qui c'è un buio pesto!", this.stanzaTest.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConLanterna() {
		Stanza stanzaUguale = new Stanza("StanzaTest");
		stanzaUguale.addAttrezzo(new Attrezzo("lanterna", 1));
		this.stanzaTest.addAttrezzo(new Attrezzo("lanterna", 1));
		assertEquals(stanzaUguale.getDescrizione(), this.stanzaTest.getDescrizione());
	}

}
