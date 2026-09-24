package tp.pers;

//classe Employe qui herite de Personne
//avec salaire en plus
//et redéfinition de .toString()
//et certains constructeurs ...

public class Employe extends Personne {
    private Double salaire;


    @Override
    public String toString() {
        return super.toString() + " de type Employe avec salaire="+this.salaire;
    }

    public Employe(){
        super();
    }


    public Employe(String nom, Integer age, Double poids,Double salaire) {
        super(nom, age, poids);
        this.salaire =salaire;
    }

    //+get/set
    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }
}