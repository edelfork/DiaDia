package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {
	
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;

	@Before
	public void setUp(){
		this.comandoVai = new ComandoVai();
		this.partita = new Partita();
		this.partenza = new Stanza("partenza");
		this.partita.setStanzaCorrente(partenza);
	}

	@Test
	public void testVaiDirezioneEsistente() {
		Stanza adiacente = new Stanza("Adiacente");
		this.partenza.impostaStanzaAdiacente("nord", adiacente);
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals(adiacente.getNome(), this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiDirezioneInesistente() {
		Stanza adiacente = new Stanza("Adiacente");
		this.partenza.impostaStanzaAdiacente("nord", adiacente);
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(partita);
		assertEquals(this.partenza.getNome(), this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiMonolocale() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals(this.partenza.getNome(), this.partita.getStanzaCorrente().getNome());
	}

}
