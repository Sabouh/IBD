package modele;

public class Spectacle {

	private int numS;
	private String nomS;
	
	public Spectacle (int numS, String nomS) {
		this.numS = numS;
		this. nomS = nomS;
	}

	public int getNumS(){
		return this.numS;
	}
	
	public String getNomS(){
		return this.nomS;
	}
	
	public void setNumS(int numS){
		this.numS = numS;
	}
	
	public void setNomS(String nomS){
		this.nomS = nomS;
	}
}
