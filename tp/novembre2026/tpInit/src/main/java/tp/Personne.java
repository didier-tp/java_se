package tp;

import java.util.Objects;

public class Personne {
    public enum Genre {HOMME, FEMME , INDETERMINE};
    //V1 (tout en public)
    public static final int AGE_MAJORITE=18;
    private static double esperanceVie = 80.0;//en années

    private String nom ;
    private int age ;
    private Double poids;
    private Genre genre = Genre.INDETERMINE; //+get/set

    public boolean estMajeur() {
        return (this.age >= AGE_MAJORITE);
    }

    public static double getEsperanceVie() {
        return esperanceVie;
    }

    public static void setEsperanceVie(double esperanceVie) {
        Personne.esperanceVie = esperanceVie;
    }

    public Personne(String nom, int age, Double poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    //pour pouvoir écrire ailleurs  Collections.sort(listePersonnes,Personne::comparerParAgeDecroissant);
    public static int comparerParAgeDecroissant(Personne p1, Personne p2){
        return Integer.compare(p2.getAge(), p1.getAge());
    }

    public Personne(String nom, int age, Double poids,Genre genre) {
        this(nom,age,poids);
        this.genre = genre;
    }

    public Personne() {
    }


    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", poids=" + poids +
                " , genre=" + genre +
                '}';
    }

    public void afficher(){
        // System.out.println("Personne nom: " + this.nom + ", age: " + this.age + ", poids: " + this.poids);
        System.out.println(this.toString());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if(age>=0)
           this.age = age;
        else throw new IllegalArgumentException("L'age ne peut pas être négatif");
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return age == personne.age && Objects.equals(nom, personne.nom) && Objects.equals(poids, personne.poids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, age, poids);
    }
}
