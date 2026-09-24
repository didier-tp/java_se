package tp.pers;

import java.util.*;

//MyApp1 regroupera plein de tests/essais autour de la classe Personne
public class MyApp1 {
    public static void main(String[] args) {
        System.out.println("MyApp1 de tpInit");
        testerPersonne();
        testerCollectionPersonne();
        //testerEmploye();
        testerStream();
    }

    public static void testerPersonne(){
        Personne p1 = new Personne();
        p1.setNom("toto");
        p1.setAge(12);
        p1.setPoids(25.0);
        p1.afficher();
        try {
            p1.setAge(-5);
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        p1.afficher();
        p1.setAge(35);
        p1.afficher();

        Personne p2 = new Personne("toto",12,25.0);
        if(p1.equals(p2))
            System.out.println("p1 et p2 ont les MEMES VALEURS internes");
        else
            System.out.println("p1 et p2 n'ont PAS les mêmes valeurs internes");

        if(p1.estMajeur())
            System.out.println("p2 est majeur avec age=" + p2.getAge());
           else
            System.out.println("p2 n'est pas majeur , il est mineur avec age=" + p2.getAge());
        System.out.println("espéranceVie initiale = " + Personne.getEsperanceVie());
        Personne.setEsperanceVie(84.1);
        System.out.println("nouvelle espéranceVie = " + Personne.getEsperanceVie());
    }


    public static void testerCollectionPersonne(){
        //créer une liste de Personne , y ajouter quelques valeurs
        //List<Personne> listePers = new ArrayList<Personne>();
        List<Personne> listePers = new ArrayList<>(); //possible depuis java 1.7
        //var listePers = new ArrayList<Personne>(); //possible depuis java 10 (souvent 11 ou 17)

        listePers.add(new Personne("toto" , 30 , 77.6)); //new Personne(nom,age,poids)
        listePers.add(new Personne("dupond" , 36 , 78.6));
        listePers.add(new Personne("luc" , 25 , 76.6));

        /*
		//trier cette liste par noms croissants
		//avec ComparatorPersonneNom puis afficher cette liste modifiée
		Collections.sort(listePers,new ComparatorPersonneNom());
		System.out.println("liste triée selon noms croissants:" + listePers);
        */
        //trier cette liste par noms croissants avec une lambda expression (plus besoin du ComparatorPersonneNom):
        //Collections.sort(listePers, (Personne p1,Personne p2)-> { return p1.getNom().compareTo(p2.getNom()) ; });
        Collections.sort(listePers, (p1, p2)->p1.getNom().compareTo(p2.getNom()) );
        System.out.println("liste triée selon noms croissants:" + listePers);

        /*
		//trier une nouvelle fois cette liste par ages décroissants
		//avec ComparatorPersonneAgeDesc puis afficher cette liste modifiée
		Collections.sort(listePers,new ComparatorPersonneAgeDesc());
		System.out.println("liste triée selon ages décroissants (avec classe de comparateur):" + listePers);
        */

        //trier une nouvelle fois cette liste par ages décroissants  (plus besoin du ComparatorPersonneAgeDesc):
        //Collections.sort(listePers,(Personne p1,Personne p2)->{ return p2.getAge()-p1.getAge(); });
        //Collections.sort(listePers,(p1,p2)->p2.getAge()-p1.getAge());
        Collections.sort(listePers,(p1,p2)->Integer.compare(p2.getAge(),p1.getAge()));
        System.out.println("liste triée selon ages décroissants:" + listePers);

        //Ancien code avec classe imbriquée anonyme
        Collections.sort(listePers, new /* classe anonyme imbriquée et qui implements */
                Comparator<Personne>() {
                    @Override
                    public int compare(Personne o1, Personne o2) {
                        return o2.getAge() - o1.getAge();
                    }
                }
        );

        System.out.println("liste triée selon ages décroissants:" + listePers);

    }

    public static void testerEmploye() {
        Employe e1 = new Employe();
        e1.setNom("toto");
        e1.setAge(26);
        e1.setSalaire(2600.0);
        System.out.println("e1="+e1.toString() + " majeur:" + e1.estMajeur());

        Employe e2 = new Employe("jean Bon",44,77.6,2500.0);
        System.out.println("e2="+e2.toString());

        Personne p=null;

        p=new Personne("toto",45,67.9);
        //System.out.println("p="+p.toString());
        p.afficher(); //afficher() appel indirectement .toString() , polymorphisme indirect

        if(p instanceof Employe) {
            Double salaire = ((Employe) p).getSalaire();
            System.out.println("salaire de p="+salaire);
        }

        p=new Employe("toto2",55,77.9,2500.0);
        //System.out.println("p="+p.toString());
        p.afficher();

        //NB: if(... instanceof ....) et appel spécifique = techniquement possible mais très déconseillé!!!
        //if faut utiliser au maximum le polymorphisme
        if(p instanceof Employe) {
            Double salaire = ((Employe) p).getSalaire();
            System.out.println("salaire de p="+salaire);
            //p.afficherEmploye() // PAS BIEN
        }else {
            //p.afficherPersonne(); //PAS BIEN
        }

    }

    public static void testerStream(){
        List<Personne> listePers = new ArrayList<>(); //possible depuis java 1.7

        listePers.add(new Personne("toto" , 30 , 77.6)); //new Personne(nom,age,poids)
        listePers.add(new Personne("dupond" , 36 , 78.6));
        listePers.add(new Personne("titi" , 6 , 38.6));
        listePers.add(new Personne("luc" , 25 , 76.6 , Personne.Genre.HOMME));
        listePers.add(new Personne("jeanne" , 16 , 58.6, Personne.Genre.FEMME));

        //En Tp :
        //construire une liste filtrée , triée et transformée
        //personnes majeures seulement
        //tri par nom
        //nom en majuscule
        List<Personne> listePers2 =
                listePers.stream()
                        .filter(p->p.estMajeur())
                        .sorted((p1,p2)->Integer.compare(p2.getAge(),p1.getAge()))
                        //.map((p)->{ p.setNom(p.getNom().toUpperCase()); return p; })
                        .map((p)->new Personne(p.getNom().toUpperCase(),p.getAge(),p.getPoids()))
                        //.collect(Collectors.toList()) //depuis java 8
                        .toList();//depuis java 17

        System.out.println("listePers2="+listePers2);
        System.out.println("listePers (d'origine)="+listePers);

        List<Double> listeDouble = Arrays.asList( 1.0 ,6.8 , 9.5 , 2.2 , 9.1 , 3.3  );
        //en tp , calculer et afficher la somme , puis la moyenne
        double somme = listeDouble.stream().reduce(0.0,(x,y)->(x+y));
        double moyenne = somme / listeDouble.size();
        System.out.println("moyenne="+moyenne);

    }
}
