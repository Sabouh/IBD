package modele;

public class Ticket {

	private int noSerie;
	private int numS;
	private String dateRep;
	private int noPlace;
	private int noRang;
	private String dateEmission;
	
	public Ticket (int noS, int numS, String dR, int nP, int nR, String dE) {
		this.noSerie = noS;
		this.numS = numS;
		this.dateRep = dR;
		this.noPlace = nP;
		this.noRang = nR;
		this.dateEmission = dE;
	}

	public int getNoSerie(){
		return this.noSerie;
	}
	
	public int getNumS(){
		return this.numS;
	}
	
	public String getDateRep(){
		return this.dateRep;
	}

	public int getNoPlace(){
		return this.noPlace;
	}
	
	public int getNoRang(){
		return this.noRang;
	}
	
	public String getDateEmission(){
		return this.dateEmission;
	}
	
	public void setNoSerie(int noS){
		this.noSerie = noS;
	}
	
	public void setNumS(int numS){
		this.numS = numS;
	}
	
	public void setDateRep(String dR){
		this.dateRep = dR;
	}

	public void setNoPlace(int nP){
		this.noPlace = nP;
	}
	
	public void setNoRang(int nR){
		this.noRang = nR;
	}
	
	public void setDateEmission(String dE){
		this.dateEmission = dE;
	}
}
