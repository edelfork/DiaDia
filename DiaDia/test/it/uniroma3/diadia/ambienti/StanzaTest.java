package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private static final String ATTREZZO = "AttrezzoPerTest";
	private static final String STANZA = "StanzaPerTest";
	private static final String NUOVA = "StanzaNuova";
	private static final String NORD = "nord";
	
	private Stanza stanza;
	
	@Before
	public void setUp() {
		this.stanza = new Stanza(STANZA);
	}

	@Test
	public void testImpostaStanzaAdiacente() {
		Stanza nuova = creaStanzaImpostandoAdiacenza(stanza, NUOVA, NORD);
		assertEquals(nuova, this.stanza.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteInesistente() {
		assertNull(this.stanza.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testGetStanzaAdiacenteImpostata() {
		Stanza nuova = creaStanzaImpostandoAdiacenza(this.stanza, NUOVA, NORD);
		assertEquals(this.stanza.getStanzaAdiacente(NORD).getDescrizione(), nuova.getDescrizione());
	}
	
	@Test 
	public void testStanzaIniziaSenzaAdiacenze() {
		String[] testArray = new String[0];
		assertArrayEquals(testArray, this.stanza.getDirezioni());
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		assertTrue(this.stanza.addAttrezzo(test));
	}
	
	@Test
	public void testAddAttrezzoStanzaPiena() {
		for (int i=0; i<Stanza.NUMERO_MASSIMO_ATTREZZI; i++)
			this.stanza.addAttrezzo(new Attrezzo(ATTREZZO+1, 1));
		assertFalse(this.stanza.addAttrezzo(new Attrezzo("DiTroppo", 1)));
	}
	
	@Test
	public void testHasAttrezzoSingolo() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertTrue(this.stanza.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoNonContenuto() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertFalse(this.stanza.hasAttrezzo("nonContenuto"));
	}
	
	@Test
	public void testHasAttrezzoStanzaVuota() {
		assertFalse(this.stanza.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoSingolo() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertEquals(test, this.stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoNonContenuto() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertNull(this.stanza.getAttrezzo("nonContenuto"));
	}
	
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(this.stanza.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testRemoveAttrezzoSingolo() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertTrue(this.stanza.removeAttrezzo(test));
	}
	
	@Test
	public void testRemoveAttrezzoNonContenuto() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		this.stanza.addAttrezzo(test);
		assertFalse(this.stanza.removeAttrezzo(new Attrezzo("nonContenuto", 1)));
	}
	
	@Test
	public void testRemoveAttrezzoStanzaVuota() {
		Attrezzo test = new Attrezzo(ATTREZZO, 1);
		assertFalse(this.stanza.removeAttrezzo(test));
	}

	private Stanza creaStanzaImpostandoAdiacenza(Stanza stanzaIniziale, String nomeStanzaAdiacente, String direzione) {
		Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
		stanzaIniziale.impostaStanzaAdiacente(NORD, stanzaAdiacente);
		return stanzaAdiacente;
	}
}
