package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private final static String ATTREZZO = "attrezzoPerTest";
	private Borsa borsa;
	
	@Before
	public void setUp() {
		this.borsa = new Borsa(Borsa.DEFAULT_PESO_MAX_BORSA);
	}
	
	@Test
	public void testAddAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		assertTrue(this.borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoTroppoPeso() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, Borsa.DEFAULT_PESO_MAX_BORSA+1);
		assertFalse(this.borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoBorsaPiena() {
		for (int i=0; i<Borsa.DEFAULT_CAPIENZA_MAX_BORSA; i++)
			this.borsa.addAttrezzo(new Attrezzo(ATTREZZO+1, 1));
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("diTroppo", 0)));
	}
	
	@Test
	public void testGetAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetAttrezzoNonContenuto() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO, 1);
		this.borsa.addAttrezzo(attrezzo);
		assertNull(this.borsa.getAttrezzo("nonContenuto"));
	}
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testGetPesoAggiuntaAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(1, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoAggiuntaDiversiAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo(ATTREZZO+1,1);
		this.borsa.addAttrezzo(attrezzo1);
		Attrezzo attrezzo2 = new Attrezzo(ATTREZZO+2,1);
		this.borsa.addAttrezzo(attrezzo2);
		assertEquals(2, this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoDopoRimozioneAttrezzo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		this.borsa.removeAttrezzo(ATTREZZO);
		assertEquals(0, this.borsa.getPeso());
	}
	
	@Test
	public void testBorsaNasceVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testHasAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		assertTrue(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testHasAttrezzoNonContenuto() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		assertFalse(this.borsa.hasAttrezzo("nonContenuto"));
	}
	
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testRemoveAttrezzoSingolo() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(attrezzo, this.borsa.removeAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testRemoveAttrezzoNonContenuto() {
		Attrezzo attrezzo = new Attrezzo(ATTREZZO,1);
		this.borsa.addAttrezzo(attrezzo);
		assertNull(this.borsa.removeAttrezzo("nonContenuto"));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsa.removeAttrezzo(ATTREZZO));
	}
}
