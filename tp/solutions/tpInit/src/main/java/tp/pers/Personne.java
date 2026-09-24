package tp.pers;

import java.util.Objects;

public class Personne {
    private String nom;
    private int age;
    private double poids;

    public static final int AGE_MAJORITE=18;

    private static double esperanceVie = 83.2;

    public enum Genre { HOMME , FEMME , INDETERMINE };

    private Genre genre=Genre.INDETERMINE;  //+get/set

    public static double getEsperanceVie() {
        return esperanceVie;
    }

    public static void setEsperanceVie(double esperanceVie) {
        Personne.esperanceVie = esperanceVie;
    }

    public boolean estMajeur(){
        return (this.age >= Personne.AGE_MAJORITE);
    }

    public Personne(String nom, int age, double poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    public Personne(String nom, int age, double poids, Genre genre) {
        this(nom,age,poids);
        this.genre=genre;
    }

    public Personne(String nom) {
        this(nom,0,0.0);
    }

    public Personne(){
        this.nom="?";
        //this.age=0; //valeur par defaut=0 pour int ou bien null pour Integer
        //this.poids=0.0; //valeur par defaut=0.0 pour double ou bien null pour Double
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

    /*public void setAge(int age) {
        this.age = age;
    }*/
    public void setAge(int age) throws IllegalArgumentException {
        if(age<0)
            throw new IllegalArgumentException("age doit etre positif");
        this.age = age;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                ", poids=" + poids +
                ", genre=" + genre +
                '}';
    }

    public void afficher(){
        //System.out.println("Personne de nom="+nom + " age="+age +  " poids="+poids);
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return age == personne.age && Double.compare(poids, personne.poids) == 0 && Objects.equals(nom, personne.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, age, poids);
    }
}
