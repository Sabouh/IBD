package modele;

public class Zone {

	private int numZ;
	private Categorie c;
	
	public Zone (int numZ, Categorie c) {
		this.numZ = numZ;
		this.c = c;
	}

	public Categorie getCategorie () {
		return this.c;
	}
	
	public int getNumZ () {
		return this.numZ;
	}
	
	public void setCategorie (Categorie c) {
		this.c = c;
	}
	
	public void setNumZ (int numZ) {
		this.numZ = numZ;
	}
}
