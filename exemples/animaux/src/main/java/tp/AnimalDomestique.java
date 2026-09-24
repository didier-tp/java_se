package tp;

public abstract class AnimalDomestique {
    protected String nom;
    protected Double poids;//en kg
    public void decrire(){
        System.out.println("AnimalDomestique de nom="+this.nom + " et de poids="+this.poids);
    }

    /*
    //V1 sans abstract
    public void parler(){
        System.out.println("...");
    }
    */

    //V2 (abstract)
    public abstract void parler();

    public AnimalDomestique(String nom, Double poids) {
        this.nom = nom;
        this.poids = poids;
    }

    public AnimalDomestique(String nom){
        this(nom,null);
    }

    public AnimalDomestique(){
        this("?",null);
    }

    @Override
    public String toString() {
        return "AnimalDomestique{" +
                "nom='" + nom + '\'' +
                ", poids=" + poids +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }
}
