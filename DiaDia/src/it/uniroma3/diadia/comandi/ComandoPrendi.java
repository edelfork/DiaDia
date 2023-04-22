package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		
		IO console = partita.getConsole();
		
		if (nomeAttrezzo == null)
		{
			console.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		Attrezzo attrezzoCorrente = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCorrente != null)
		{
			int peso = attrezzoCorrente.getPeso();
			
			if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzoCorrente))
			{
				console.mostraMessaggio(attrezzoCorrente.getNome() + " preso/a e messo/a in borsa");
				partita.getStanzaCorrente().removeAttrezzo(attrezzoCorrente);
			}
			else if (partita.getGiocatore().getBorsa().getPeso() + peso > partita.getGiocatore().getBorsa().getPesoMax()) 
			{
				console.mostraMessaggio("Il peso è eccessivo!");
				return;
			}
			else 
			{
				console.mostraMessaggio("Borsa piena!");
				return;
			}
		}		
		else
			console.mostraMessaggio(nomeAttrezzo + " non è nella stanza!");
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "ComandoPrendi";
	}

}
