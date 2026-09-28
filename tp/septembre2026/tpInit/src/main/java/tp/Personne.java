package tp;

import java.util.Objects;

public class Personne {
    private String nom;

    //private int age; //0  default
    private Integer age;//null default
    private Double poids;

    public static final int AGE_MAJORITE=18;

    private static double esperanceVie = 83.2;

    public static double getEsperanceVie() {
        return esperanceVie;
    }

    public static void setEsperanceVie(double esperanceVie) {
        Personne.esperanceVie = esperanceVie;
    }

    public boolean estMajeur(){
        return (this.age >= Personne.AGE_MAJORITE);
    }


    public Personne(String nom, Integer age, Double poids) {
        this.nom = nom;
        this.age = age;
        this.poids = poids;
    }

    public Personne(String nom){
        //this(nom,0,0.0);
        this(nom,null,null);
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        //this.age = age;
        if(age>=0)
            this.age=age;
        else {
            System.out.println("nouvel age demandé invalide (négatif) , this.age inchangé");
            //throw new RuntimeException("nouvel age demandé invalide (négatif)");
        }
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void afficher(){
        System.out.println(this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom) && Objects.equals(age, personne.age) && Objects.equals(poids, personne.poids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, age, poids);
    }
}
