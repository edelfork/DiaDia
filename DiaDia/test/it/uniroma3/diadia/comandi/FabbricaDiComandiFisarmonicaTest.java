package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi FdC = new FabbricaDiComandiFisarmonica();
	
	@Before
	public void setUp() {
		this.FdC = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testComandoVai() {
		Comando vai = FdC.costruisci("vai");
		vai.setParametro("nord");
		assertEquals(vai.getNome(), "ComandoVai");
	}
	
	@Test
	public void testComandoPosa() {
		Comando vai = FdC.costruisci("posa");
		vai.setParametro("osso");
		assertEquals(vai.getNome(), "ComandoPosa");
	}
	
	@Test
	public void testComandoPrendi() {
		Comando vai = FdC.costruisci("prendi");
		vai.setParametro("osso");
		assertEquals(vai.getNome(), "ComandoPrendi");
	}
	
	@Test
	public void testComandoGuarda() {
		Comando vai = FdC.costruisci("guarda");
		assertEquals(vai.getNome(), "ComandoGuarda");
	}
	
	@Test
	public void testComandoFine() {
		Comando vai = FdC.costruisci("fine");
		assertEquals(vai.getNome(), "ComandoFine");
	}
	
	@Test
	public void testComandoAiuto() {
		Comando vai = FdC.costruisci("aiuto");
		assertEquals(vai.getNome(), "ComandoAiuto");
	}
	
	@Test
	public void testComandoNonValido() {
		Comando vai = FdC.costruisci("inesistente");
		assertEquals(vai.getNome(), "ComandoNonValido");
	}

}
