package modele;

public class Representation {

	private int numS;
	private int date;
	
	public Categorie (int numS, int date) {
		this.numS = numS;
		this.date = date;
	}

	public int getnumS(){
		return this.numS;
	}
	
	public int getDate(){
	 	return this.date;
	}
	
	public void setNumS(int numS){
		this.numS = numS;
	}
	
	public void setDate(int date){
		this.date = date;
	}
}
