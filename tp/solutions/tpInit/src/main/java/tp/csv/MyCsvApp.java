package tp.csv;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyCsvApp {
	
	Stat stat;
	List<Produit> listeProduits;
	Map<String , List<Produit>> mapSubListProdByCategorie ;
	Map<String , Stat> mapStatsByCategorie ;
	
	MyCsvUtil csvUtil = new MyCsvUtil();

	public static void main(String[] args) {
		MyCsvApp myApp = new MyCsvApp();
		myApp.loadProducts();
		myApp.computeStats();
		myApp.computeStatsByCategories();
		myApp.writeStats();
		myApp.writeSortedProducts();
		myApp.writeStatsByCategories();
	}
	
	

	private void computeStatsByCategories() {
		//étape 1 : construire la table d'association nomCategorie , sousListeProduitsDeLaCategorie
		mapSubListProdByCategorie = new HashMap<>();
		for(Produit prod : this.listeProduits) {
			List<Produit> subList = mapSubListProdByCategorie.get(prod.getCategorie());
			if(subList==null) {
				subList = new ArrayList<>();
				mapSubListProdByCategorie.put(prod.getCategorie(), subList );
			}
			subList.add(prod);
		}
		//étape 2 : statitistiques pour chaque catégorie:
		mapStatsByCategorie=new HashMap<>();
		for(String categorie : mapSubListProdByCategorie.keySet()) {
			int nbProd=0;
			double sommePrix=0.0;
			double sommePoids=0.0;
			for(Produit prod : this.mapSubListProdByCategorie.get(categorie)) {
				nbProd++;
				sommePrix += prod.getPrix();
				sommePoids += prod.getPoids();
			}
			mapStatsByCategorie.put(categorie, new Stat(nbProd,sommePrix/nbProd,sommePoids/nbProd));
		}
	}

	private void loadProducts(){
		this.listeProduits = csvUtil.readProductFile();
	}
	
	private void sortProductsByPrice(){
		this.listeProduits = 
				this.listeProduits.stream()
				.sorted( (p1,p2)-> Double.compare(p1.getPrix(), p2.getPrix())   )
				.collect(Collectors.toList());
	}
	
    private void computeStats(){
		int nbProd=0;
		double sommePrix=0.0;
		double sommePoids=0.0;
		for(Produit prod : this.listeProduits) {
			nbProd++;
			sommePrix += prod.getPrix();
			sommePoids += prod.getPoids();
		}
		this.stat = new Stat(nbProd,sommePrix/nbProd,sommePoids/nbProd);
	}
    
    private void writeStats(){
    	csvUtil.writeStatFile(stat);
	}
    
    private void writeSortedProducts() {
    	this.sortProductsByPrice();
    	csvUtil.writeProductFile(this.listeProduits,"produits_tries_par_prix.csv");
    }
    
    private void writeStatsByCategories() {
		csvUtil.writeStatFileFromCategorieMap(mapStatsByCategorie);
	}

}
