package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoChiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoChiave = attrezzoSbloccante;
	}
	

	@Override
	public Stanza getStanzaAdiacente(String dir) { //decommentare la System.out per debugging su console
		if (!super.hasAttrezzo(this.attrezzoChiave) && this.direzioneBloccata.equals(dir)) {		
//			System.out.println("la direzione è bloccata!");
			return this;
		}
		return super.getStanzaAdiacente(dir);	
	}
	
	@Override
	public String getDescrizione() {
		return "La porta a " + this.direzioneBloccata + " sembra essere bloccata! Servirebbe un/a " 
				+ this.attrezzoChiave + "\n\n" + super.getDescrizione();
	}
}
