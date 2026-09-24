package tp.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCsvUtil {
	
	private String produitsFileName="produits.csv"; //par défaut
	private String statsFileName="stats_produits.csv"; //par défaut
	private String statsByCategorieFileName="stats_par_categories.csv"; //par défaut

	public List<Produit> readProductFile(){
		List<Produit> listeProduits = new ArrayList<>();
		
		try( FileInputStream fis = new FileInputStream(produitsFileName);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fis))
		    ){
			br.readLine();//lecture ligne d'entête
			String ligne = br.readLine(); //lecture première ligne de valeurs
			while(ligne!=null) {
				String p[] = ligne.split(";");
		        Produit produit = new Produit(
		        		Integer.parseInt(p[0]),
		        		p[1],
		        		p[2],
		        		Double.parseDouble(p[3]),
		        		Double.parseDouble(p[4]));
				listeProduits.add(produit);
				ligne = br.readLine(); //lecture prochaine ligne de valeurs
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//automatic finally in try(withResources){....}catch(...){...}
		//with automatic .close() on AutoClosable resource like FileInputStream or BufferedReader
			
		return listeProduits;
	}
	
	public void writeStatFile(Stat stat) {
		try(
			FileOutputStream fos = new FileOutputStream(statsFileName);
			PrintStream ps = new PrintStream(fos)){
		    ps.println("nbProduits;moyennePrix;moyennePoids") ;
			ps.printf("%d;%s;%s",stat.getNbProduits(),
					             String.valueOf(stat.getMoyennePrix()),
					             String.valueOf(stat.getMoyennePoids()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//automatic finally in try(withResources){....}catch(...){...}
		//with automatic .close() on AutoClosable resource like FileOutputStream or PrintStream
		System.out.println("le fichier " + statsFileName + " a été généré");
	}
	
	public void writeStatFileFromCategorieMap(Map<String,Stat> statMap) {
		try(
			FileOutputStream fos = new FileOutputStream(statsByCategorieFileName);
			PrintStream ps = new PrintStream(fos)){
		    ps.println("categorie;nbProduits;moyennePrix;moyennePoids") ;
		    for( String categorie : statMap.keySet()) {
		    	Stat stat = statMap.get(categorie);
		    	ps.printf("%s;%d;%s;%s\n",categorie,
		    			 stat.getNbProduits(),
			             String.valueOf(stat.getMoyennePrix()),
			             String.valueOf(stat.getMoyennePoids()));
		    }
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//automatic finally in try(withResources){....}catch(...){...}
		//with automatic .close() on AutoClosable resource like FileOutputStream or PrintStream
		System.out.println("le fichier " + statsByCategorieFileName + " a été généré");
	}

	public void writeProductFile(List<Produit> listeProduits,String outputFileName) {
		try(
				FileOutputStream fos = new FileOutputStream(outputFileName);
				PrintStream ps = new PrintStream(fos)){
			    ps.println("num;label;categorie;prix;poids") ;
			    for(Produit prod : listeProduits) {
				ps.printf("%d;%s;%s;%s;%s\n",prod.getNumero(),
						             prod.getLabel(),
						             prod.getCategorie(),
						             String.valueOf(prod.getPrix()),
						             String.valueOf(prod.getPoids()));
			    }
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			//automatic finally in try(withResources){....}catch(...){...}
			//with automatic .close() on AutoClosable resource like FileOutputStream or PrintStream
			System.out.println("le fichier " + outputFileName + " a été généré");
	}

}
