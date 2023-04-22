package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoPerVedere;
	
	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}

	@Override
	public String getDescrizione() {
		if (super.hasAttrezzo(this.attrezzoPerVedere))
			return super.getDescrizione();
		else
			return "qui c'è un buio pesto!";
	}
}
