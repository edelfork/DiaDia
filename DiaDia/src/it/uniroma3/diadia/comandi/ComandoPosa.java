package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		
		IO console = partita.getConsole();
		
		if (nomeAttrezzo == null)
		{
			console.mostraMessaggio("Cosa vuoi posare?");
			return;
		}
		
		Attrezzo attrezzoCorrente = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		
		if (attrezzoCorrente != null)
		{
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoCorrente))
			{
				console.mostraMessaggio(attrezzoCorrente.getNome() + " posato/a a terra");
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			}
			else 
				console.mostraMessaggio("Stanza piena!");
		}
		else
			console.mostraMessaggio(nomeAttrezzo +" non è nella borsa!");
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
		return "ComandoPosa";
	}

}
