package tp;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class MyApp1 {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to MyApp1 ");

        //testPersonne();
        //testerCollectionPersonne();
        testerStreamPersonne();
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

    private static void testerStreamPersonne() {
        List<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(new Personne("zorro", 35, 67.0));
        listePersonnes.add(new Personne("durand", 6, 19.0));
        listePersonnes.add(new Personne("toto", 45, 55.0));
        listePersonnes.add(new Personne("alfred", 25, 68.0));
        listePersonnes.add(new Personne("durand", 3, 12.0));

        List<Personne> listePersonnesFiltreesTrieesEtTransformees =
                listePersonnes.stream()
                        .filter( (p)->p.getAge()>=18 )
                        .sorted( (p1,p2)->Integer.compare(p1.getAge(), p2.getAge()))
                        //.map( (p) -> { p.setNom(p.getNom().toUpperCase()); return p; } ) //avec effet de bord sur la liste initiale
                        .map( (p) -> new Personne(p.getNom().toUpperCase(), p.getAge(), p.getPoids(), p.getGenre()) ) //sans effet de bord sur
                        //.collect(Collectors.toList()); // ou bien directement .toList() depuis java 17
                        .toList();
        System.out.println("listePersonnes initiale (avec effet de bord ou pas) = " + listePersonnes);
        System.out.println("listePersonnesFiltreesTrieesEtTransformees = " + listePersonnesFiltreesTrieesEtTransformees);
    }

    private static void testerCollectionPersonne() {
        List<Personne> listePersonnes = new ArrayList<>();
        listePersonnes.add(new Personne("zorro",35,67.0)) ;
        listePersonnes.add(new Personne("Durand",26, 70.0));
        listePersonnes.add(new Personne("toto",45,55.0)) ;
        listePersonnes.add(new Personne("alfred",25,68.0)) ;
        //Collections.sort(listePersonnes,new ComparateurDePersonneParNomCroissant());
        /*
        //avec classe anomyme imbriquée
        Collections.sort(listePersonnes,new Comparator<Personne>(){
            @Override
            public int compare(Personne o1, Personne o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });
        */
        Collections.sort(listePersonnes,(p1,p2) ->
                (p1.getNom()!=null)?p1.getNom().toLowerCase().compareTo(p2.getNom().toLowerCase()):-1);
        System.out.println("listePersonnes triée par nom croissant = " + listePersonnes);
        //Collections.sort(listePersonnes,new ComparateurDePersonneParAgeDecroissant());
        //Collections.sort(listePersonnes,(p1,p2) -> p2.getAge() - p1.getAge());
        Collections.sort(listePersonnes,Personne::comparerParAgeDecroissant);
        System.out.println("listePersonnes triée par age décroissant = " + listePersonnes);

        Map<String, Predicate<Personne>> mapPredicateParAge = new java.util.HashMap<>();
        mapPredicateParAge.put("0-29", p -> p.getAge() >=0  && p.getAge() <= 29);
        mapPredicateParAge.put("30-99", p -> p.getAge() >= 30 && p.getAge() <= 99);
        Map<String, List<Personne>> mapPersonnesParTrancheAge = MyFunctionalUtil.partionner(listePersonnes,mapPredicateParAge);
        System.out.println("mapPersonnesParTrancheAge = " + mapPersonnesParTrancheAge);
    }

}

class ComparateurDePersonneParNomCroissant implements java.util.Comparator<Personne>{
    @Override
    public int compare(Personne o1, Personne o2) {
        if(o1.getNom() != null)
            return o1.getNom().toLowerCase().compareTo(o2.getNom().toLowerCase());
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
