package tp.csv;

public class Produit {
    private Integer numero;
    private String label;

	@CsvIgnore //like @JsonIgnore
    private String categorie;

    private Double prix;
    private Double poids;
   
    
	public Produit(Integer numero, String label, String categorie, Double prix, Double poids) {
		super();
		this.numero = numero;
		this.label = label;
		this.categorie = categorie;
		this.prix = prix;
		this.poids = poids;
	}
	

	public Produit() {
		super();
	}


	@Override
	public String toString() {
		return "Produit [numero=" + numero + ", label=" + label + ", categorie=" + categorie + ", prix=" + prix
				+ ", poids=" + poids + "]";
	}
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	
    
}
