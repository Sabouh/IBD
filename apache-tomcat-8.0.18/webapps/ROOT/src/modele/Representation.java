package modele;

public class Representation {

	private int numS;
	private String date;
	
	public Representation (int numS, String date) {
		this.numS = numS;
		this.date = date;
	}

	public int getNumS(){
		return this.numS;
	}
	
	public String getDate(){
	 	return this.date;
	}
	
	public void setNumS(int numS){
		this.numS = numS;
	}
	
	public void setDate(String date){
		this.date = date;
	}
}
