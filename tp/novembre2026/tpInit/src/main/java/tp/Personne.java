package tp;

public class Personne {
    //V1 (tout en public)
    public String nom;
    public int age;
    public Double poids;

    public void afficher(){
        System.out.println("Personne nom: " + nom + ", age: " + age + ", poids: " + poids);
    }
}
