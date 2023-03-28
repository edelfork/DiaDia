package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	static final public int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa bag;
	
	public Giocatore() {
		
		this.cfu = CFU_INIZIALI;
		this.bag = new Borsa();;
		
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return bag;
	}

	public void setBag(Borsa bag) {
		this.bag = bag;
	}
	
}
