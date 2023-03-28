package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setUP() {
		this.partita = new Partita();
	}
	
	@Test
	public void testVinta_True_StanzaCorrenteUgualeVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVinta_False_StanzaCorrenteDiversaVincente() {
		this.partita.setStanzaCorrente(new Stanza("StanzaTest"));
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVinta_False_InizioPartita() {
		assertFalse(this.partita.vinta());
	}

	@Test
	public void testFinita_True_SetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testFinita_True_Cfu0() {
		this.partita.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testFinita_False_Cfu20() {
		assertFalse(this.partita.isFinita());
	}

}
