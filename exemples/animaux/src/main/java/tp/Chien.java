package tp;

public class Chien extends AnimalDomestique {
    private String fonction; //ex: "garder troupeau" , "guider aveugle" , "tenir compagnie"

    @Override
    public void parler(){
        System.out.println("waouf  waouf");
    }

    @Override
    public void decrire(){
        System.out.println("Je suis un chien qui cette principale fonction: " + fonction );
        System.out.print("\t"); super.decrire();
    }

    public void monterLaGarde(){
        System.out.println("je monte la garde");
    }

    public Chien(String nom, Double poids, String fonction) {
        super(nom, poids);
        this.fonction = fonction;
    }

    public Chien(String nom, Double poids){
        this(nom,poids,"tenir compagnie"); //tenir compagnie est la fonction par défaut
    }

    public Chien(String nom){
        this(nom,12.0); //12kg par défaut
    }

    public Chien(){
        this("?");
    }


    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "Chien{" +
                "fonction='" + fonction + '\'' +
                ", nom='" + nom + '\'' +
                ", poids=" + poids +
                '}';
    }
}
