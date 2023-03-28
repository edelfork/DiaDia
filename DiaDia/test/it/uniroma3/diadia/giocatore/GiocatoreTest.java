package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore;
	
	@Before
	public void setUp() {
	
		this.giocatore = new Giocatore();
		
	}
	
	@Test
	public void testGetCfu_Equals_CfuInizioPartita() {
		assertEquals(this.giocatore.getCfu(), 20);
	}
	
	@Test
	public void testGetCfu_Equals_CfuCorrettamenteSettati() {
		assertEquals(Giocatore.CFU_INIZIALI, this.giocatore.getCfu());
	}

}
