package modele;

public class Place {

	private int noPlace;
	private int noRang;
	private int noZone;
	
	public Place (int noPlace,int noRang, int noZone) {
		this.noPlace = noPlace;
		this.noRang = noRang;
		this.noZone = noZone;
	}

	public int getNoPlace () {
		return this.noPlace;
	}
	
	public int getNoRang () {
		return this.noRang;
	}
	
	public int getNoZone () {
		return this.noZone;
	}
	
	
	public void setNoPlace (int noPlace) {
		this.noPlace = noPlace;
	}
	
	public void setNoRang (int noRang) {
		 this.noRang= noRang;
	}
	
	public void setNoZone (int noZone) {
		 this.noZone = noZone;
	}
}
