package modele;

public class Dossier {

	private int noDossier;
	private float montant;
	
	public Dossier (int noDossier, float montant) {
		this.noDossier = noDossier;
		this.montant = montant;
	}

	public int getNoDossier(){
		return this.noDossier;
	}
	
	public float getMontant(){
		return this.montant;
	}
	
	public void setNoDossier(int noDossier){
		this.noDossier = noDossier;
	}
	
	public void setMontant(float montant){
		this.montant = montant;
	}
}
