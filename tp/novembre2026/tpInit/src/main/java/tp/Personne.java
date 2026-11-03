package tp;

import java.util.Objects;

public class Personne {
    //V1 (tout en public)
    public static final int AGE_MAJORITE=18;
    private static double esperanceVie = 80.0;//en années
    private String nom ;
    private int age ;
    private Double poids;

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

    public Personne() {
    }


    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", poids=" + poids +
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

    public void setAge(int age) {
        this.age = age;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
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
