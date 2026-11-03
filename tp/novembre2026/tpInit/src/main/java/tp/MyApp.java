package tp;

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to MyApp!");
        //testConversion();
        //testPersonne();
        //testBagage();
        testMoyenne();
        testString();
    }

    public static void testString(){
        String s1 ="abc";
        String s0 ="ab";
        String s2 =s0 + "c";
        //if(s1==s2) { //pas ok
        if(s1.equals(s2)) { //ok avec .equals() dejà codé dans la classe String
            System.out.println("s1 et s2 ont meme valeurs internes");
        }

        String sDate = "2023-01-17";
        //extraire la partie mois de différentes
        String mois = sDate.substring(sDate.indexOf('-')+1,sDate.lastIndexOf('-'));
        System.out.println("mois = " + mois);
        String moisV2 = sDate.split("-")[1];
        System.out.println("moisV2 = " + moisV2);

        String chaine="YTREZA" ;
        //créer une nouvelle chaine inverse où tous les caractères sont dans l'ordre inverse
        StringBuilder buffer = new StringBuilder(chaine.length());
        for(int i=chaine.length()-1; i>=0; i--){
            buffer.append(chaine.charAt(i));
        }
        String chaineInverse = buffer.toString();
        System.out.println("chaineInverse = " + chaineInverse);
       
    }

    public static void testMoyenne(){
        /*
        double[] tab = new double[6];
        tab[0] = 12.0; tab[1] = 14.0; tab[2] = 18.0;
        tab[3] = 20.0; tab[4] = 10.0; tab[5] = 8.0;
         */
        double[] tab = {12.0, 14.0, 18.0, 20.0 , 10.0 , 8.0};
        double somme = 0.0;
        for(int i=0; i<tab.length; i++) {
            somme += tab[i];
        }
        double moyenne = somme / tab.length;
        System.out.println("La moyenne est: " + moyenne);
        double plusGrand = tab[0];
        for(int i=1; i<tab.length; i++) {
           if(tab[i] > plusGrand) {
               plusGrand = tab[i];
           }
        }
        System.out.println("plusGrand: " + plusGrand);
    }

    public static void testBagage(){
        Bagage b1 = new Bagage();
        b1.setLabel("Valise");
        b1.setPoids(67);
        b1.setVolume(50.0);
        System.out.println("b1 = " + b1.toString());
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
    }

    public static void testConversion(){
        String sx ="2.5";
        String sy= "3.5";
        //calculer et afficher la somme de sx et sy
        double x = Double.parseDouble(sx);
        double y = Double.parseDouble(sy);
        double sum = x + y;
        System.out.println("La somme de " + sx + " et " + sy + " est: " + sum);
    }
}
