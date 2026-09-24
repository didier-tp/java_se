package tp.csv;

public class Stat {

	private Integer nbProduits;
	private Double moyennePrix;
	private Double moyennePoids;
	
	public Stat(Integer nbProduits, Double moyennePrix, Double moyennePoids) {
		super();
		this.nbProduits = nbProduits;
		this.moyennePrix = moyennePrix;
		this.moyennePoids = moyennePoids;
	}
	
	
	public Stat() {
		super();
	}

	@Override
	public String toString() {
		return "Stat [nbProduits=" + nbProduits + ", moyennePrix=" + moyennePrix + ", moyennePoids=" + moyennePoids
				+ "]";
	}
	
	public Integer getNbProduits() {
		return nbProduits;
	}
	public void setNbProduits(Integer nbProduits) {
		this.nbProduits = nbProduits;
	}
	public Double getMoyennePrix() {
		return moyennePrix;
	}
	public void setMoyennePrix(Double moyennePrix) {
		this.moyennePrix = moyennePrix;
	}
	public Double getMoyennePoids() {
		return moyennePoids;
	}
	public void setMoyennePoids(Double moyennePoids) {
		this.moyennePoids = moyennePoids;
	}
	
	

}
