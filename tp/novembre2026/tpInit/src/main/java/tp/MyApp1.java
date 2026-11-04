package tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyApp1 {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to MyApp1 ");

        //testPersonne();
        testerCollectionPersonne();
    }

    private static void testerCollectionPersonne() {
        List<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(new Personne("zorro",35,67.0)) ;
        listePersonnes.add(new Personne("durand",26, 70.0));
        listePersonnes.add(new Personne("toto",45,55.0)) ;
        listePersonnes.add(new Personne("alfred",25,68.0)) ;
        Collections.sort(listePersonnes,new ComparateurDePersonneParNomCroissant());
        System.out.println("listePersonnes triée par nom croissant = " + listePersonnes);
        Collections.sort(listePersonnes,new ComparateurDePersonneParAgeDecroissant());
        System.out.println("listePersonnes triée par age décroissant = " + listePersonnes);
    }


    public static void testPersonne(){
        //new Personne + appel de afficher sur un objet de type Personne
        Personne p1 =null ;
        p1 = new Personne();
        p1.afficher();
        p1.setNom("Alice");   p1.setAge(30);   p1.setPoids(65.5);
        //p1.afficher();
        System.out.println("p1 = " + p1.toString());
        Personne p2=null;
        p2 = new Personne("Bob", 25, 70.0);
        //p2.afficher();
        System.out.println("p2 = " + p2);//appel implicite de .toString()
        p2.setAge(p2.getAge() +1);
        System.out.println("nouvel age de p2 = " + p2.getAge());

        Personne p2Bis = new Personne("Bob", 26, 70.0);
        if(p2.equals(p2Bis)){
            System.out.println("p2 et p2Bis sont égaux");
        } else {
            System.out.println("p2 et p2Bis sont différents");
        }

        System.out.println("esperance de vie initiale (avant covid) = " + Personne.getEsperanceVie());
        Personne.setEsperanceVie(79);
        System.out.println("nouvelle esperance de vie (après covid) = " + Personne.getEsperanceVie());
        if(p1.estMajeur())
            System.out.println("p1 est majeur");

        Personne p3 = new Personne("Bob", 26, 70.0 , Personne.Genre.HOMME);
        System.out.println("p3 = " + p3);
        p3.setNom("Alice");
        p3.setGenre(Personne.Genre.FEMME);
        System.out.println("p3 après modification = " + p3);

        try {
            p3.setAge(-5);
        } catch (Exception e) {
            //throw new RuntimeException(e); //si on était à un niveau intermédiaire
            //System.out.println("Erreur: " + e.getMessage());  // ... > ficRes.txt
            System.err.println("Erreur: " + e.getMessage());  // ... 2> ficErr.txt
            //e.printStackTrace(); //message plus stack trace
        }
        System.out.println("nouvel age de p3 = " + p3.getAge());
        try {
            p3.setAge(5);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        System.out.println("nouvel age de p3 = " + p3.getAge());

    }


}

class ComparateurDePersonneParNomCroissant implements java.util.Comparator<Personne>{
    @Override
    public int compare(Personne o1, Personne o2) {
        if(o1.getNom() != null)
            return o1.getNom().compareTo(o2.getNom());
        else return -1;
    }
}

class ComparateurDePersonneParAgeDecroissant implements java.util.Comparator<Personne>{
    @Override
    public int compare(Personne o1, Personne o2) {
        return o2.getAge() - o1.getAge(); //si age de type int
        /*
        //si age de type Integer (classe wrapper) , alors on peut faire la comparaison avec compareTo
        if(o2.getAge() != null)
            return o2.getAge().compareTo(o1.getAge());
        else return -1;
        */
    }
}
