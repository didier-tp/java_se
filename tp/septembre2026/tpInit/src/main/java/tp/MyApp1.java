package tp;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


public class MyApp1 {
    static void main() {
        System.out.println("Hello world");
        //testElementaire();
        //testPersonne();
        //testerCollectionPersonne();
        testerStream();
        testerForEachWithLambda();
    }

    public static void testElementaire(){
        String val1AsString="12";
        String val2AsString="3";
        //calculer et afficher la somme
        int val1 = Integer.parseInt(val1AsString);
        int val2 = Integer.parseInt(val2AsString);
        int somme = val1 + val2;
        System.out.println("somme="+somme);

        String chA = null;
        System.out.println("chA="+chA);
        chA = new String("lundi");  //ou plus simplement chA = "lundi"
        System.out.println("chA="+chA);
    }

    public static void testerCollectionPersonneV1SansExpression(){
        Personne p1 = new Personne("jean Bon", 35 , 62.5 );
        Personne p2 = new Personne("toto", 25 , 82.5 );

        List<Personne> listePersonne = new ArrayList<>();
        listePersonne.add(p1);
        listePersonne.add(p2);
        listePersonne.add ( new Personne("luc" , 40 , 77.7));
        System.out.println("----ordre initial----");
        for(Personne p : listePersonne){
            System.out.println("\t" + p);
        }

        /*
        ComparateurPersonneParNom comparateurDePersonneParNom = new ComparateurPersonneParNom();
        Collections.sort(listePersonne,comparateurDePersonneParNom);
        System.out.println("----apres tri par nom----");
        for(Personne p : listePersonne) {
            System.out.println("\t" + p); //"\t" pour tabulation , "\n" pour saut de ligne
        }
        */



        Collections.sort(listePersonne,new /* classe imbriquée anonyme qui implements */
           java.util.Comparator<Personne>(){
               //debut code entre { } de la classe anonyme imbriquée
               @Override
               public int compare(Personne o1, Personne o2) {
                   if(o1.getNom() != null)
                       return o1.getNom().compareTo(o2.getNom());
                   else return -1;
               }
           }//fin du code de la classe imbriquée
           );
        System.out.println("----apres tri par nom----");
        for(Personne p : listePersonne) {
            System.out.println("\t" + p); //"\t" pour tabulation , "\n" pour saut de ligne
        }

        ComparateurPersonneParAge comparateurDePersonneParAge = new ComparateurPersonneParAge();
        Collections.sort(listePersonne,comparateurDePersonneParAge);
        System.out.println("----apres tri par age décroissant----");
        for(Personne p : listePersonne) {
            System.out.println("\t" + p); //"\t" pour tabulation , "\n" pour saut de ligne
        }
    }

    public static List<Personne> initListePersonnes(){
        return Arrays.asList(new Personne("jean Bon", 35 , 62.5 ),
                              new Personne("toto", 15 , 42.5 ) ,
                              new Personne("luc" , 40 , 77.7),
                              new Personne("titi" , 8 , 27.7)
        );
    }

    public static void testerStream(){
        //avec stream, enchaîner "filtrage des personnes majeures" , "tri selon age"
        //puis transformations avec noms en majuscules
        List<Personne> listePersonne = initListePersonnes();
        System.out.println("listePersonne initiale = " + listePersonne);
        AtomicInteger nbPersMajeures= new AtomicInteger();
        AtomicLong sommeAge = new AtomicLong();
        List<Personne> listePersonnesFiltreesTrieesEtTransformees =
                listePersonne.stream()
                        .filter( (p)->p.getAge()>=18 )
                        .sorted( (p1,p2)->Integer.compare(p1.getAge(), p2.getAge()))
                        //.map( (p) -> { p.setNom(p.getNom().toUpperCase()); return p; } )
                        .map( (p) -> new Personne(p.getNom().toUpperCase(),p.getAge(),p.getPoids()) )
                        .peek((p)-> {  nbPersMajeures.getAndIncrement();
                                                sommeAge.addAndGet( p.getAge());  } )
                        .toList();
        System.out.println("nombre de personnes majeures = " + nbPersMajeures.get());
        System.out.println("age moyen  des personnes majeures = " + (double) sommeAge.get() / nbPersMajeures.get());
        System.out.println("listePersonnesFiltreesTrieesEtTransformees = " + listePersonnesFiltreesTrieesEtTransformees);
        System.out.println("listePersonne modifiée ou pas par effet de bord = " + listePersonne);
    }

    public static void testerForEachWithLambda(){
        List<Personne> listePersonne = initListePersonnes();
        PersStat persStat = PersStat.buildStatViaForEachWithLambda(listePersonne);
        System.out.println("persStat="+persStat);
    }


    //V2 avec lambda expression
    public static void testerCollectionPersonne(){
        Personne p1 = new Personne("jean Bon", 35 , 62.5 );
        Personne p2 = new Personne("toto", 25 , 82.5 );

        List<Personne> listePersonne = new ArrayList<>();
        listePersonne.add(p1);
        listePersonne.add(p2);
        listePersonne.add ( new Personne("luc" , 40 , 77.7));
        listePersonne.add ( new Personne("zorro" , null , 87.7));
        System.out.println("----ordre initial----");
        for(Personne p : listePersonne){
            System.out.println("\t" + p);
        }


        //Collections.sort(listePersonne, (pers1,pers2) -> pers1.getNom().compareTo(pers2.getNom()) ) ;
        Collections.sort(listePersonne,Personne::comparerDeuxPersonnesParNom);

        System.out.println("----apres tri par nom----");
        for(Personne p : listePersonne) {
            System.out.println("\t" + p); //"\t" pour tabulation , "\n" pour saut de ligne
        }


        System.out.println("----apres tri par age décroissant----");
        //Collections.sort(listePersonne,(pers1,pers2) -> pers2.getAge().compareTo(pers1.getAge()) ) ;
        Collections.sort(listePersonne,(pers1,pers2) -> (pers2.getAge()!=null && pers1.getAge()!=null)?pers2.getAge().compareTo(pers1.getAge()):0 ) ;
        for(Personne p : listePersonne) {
            System.out.println("\t" + p); //"\t" pour tabulation , "\n" pour saut de ligne
        }

    }

    public static void testPersonne(){
        Personne p1=null;
        p1=new Personne();
        p1.afficher();
        p1.setNom("didier");
        //p1.age=18; //interdit si age est "private"
        p1.setAge(18);
        p1.setPoids(90.0);
        p1.setGenre(Personne.Genre.HOMME);
        p1.afficher();
        System.out.println("p1="+p1.toString());


        try {
            p1.setAge(-2567);
            p1.afficher();
        } catch (IllegalArgumentException e) {
        	e.printStackTrace();
            //System.err.println(e.getMessage());
        }


        /*
        try {
            p1.setAge(-2567);
            p1.afficher();
        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println(e.getMessage());
            //System.out.println(e.getMessage()); //possible mais moins bien
        }
        */
        /*
        try {
            (new Personne("toto" , -56 , 81.5 )).afficher();
        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println(e.getMessage());
        }*/

        Personne p2 = new Personne("toto", 25 , 82.5 , Personne.Genre.HOMME);
        p2.afficher();
        Personne p3 = new Personne("julie", 25 , 82.5 , Personne.Genre.FEMME);
        if(p2.equals(p3)) //bon comportement que si generate .equals() sur classe Personne
            System.out.println("p2 et p3 ont mêmes valeurs internes");
        else
            System.out.println("p2 et p3 ont valeurs internes différentes");

        if(p2.estMajeur())
            System.out.println("p2 est majeur avec age=" + p2.getAge());
        else
            System.out.println("p2 n'est pas majeur , il est mineur avec age=" + p2.getAge());
        System.out.println("espéranceVie initiale = " + Personne.getEsperanceVie());
        Personne.setEsperanceVie(84.1);
        System.out.println("nouvelle espéranceVie = " + Personne.getEsperanceVie());
    }
}

@ToString
class PersStat{
    private int nbPers;
    private double sumAge;
    private int averageAge;
    Personne youngestPers=null;
    Personne oldestPers = null;


    public static PersStat buildStatViaForEachWithLambda(List<Personne> listePersonne){
        PersStat persStat = new PersStat();
        persStat.computeThisStatViaForEachWithLambda(listePersonne);
        return persStat;
    }
    public void computeThisStatViaForEachWithLambda(List<Personne> listePersonne) {
        listePersonne.forEach((p) -> {
            this.nbPers++;
            this.sumAge += p.getAge();
            if (youngestPers == null) youngestPers = p;
            else if (youngestPers.getAge() > p.getAge()) youngestPers = p;
            if (oldestPers == null) oldestPers = p;
            else if (oldestPers.getAge() < p.getAge()) oldestPers = p;
        });
        this.averageAge = (int) (this.sumAge / this.nbPers);
    }
}
