package tp.basic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

//MyApp2 regroupera plein de tests/essais qui ne sont pas liés à la classe Personne
public class MyApp2 {
    public static void main(String[] args) {
        System.out.println("MyApp2 de tpInit");
        testerBagage();
        testTableauMoyenne();
        testString();
        System.out.println("la racine carrée de 81 est " + Math.sqrt(81));
        testListe();
        //testerScanner();
        testDates();
    }


    public static void testerBagage(){
        Bagage b1 = new Bagage();
        System.out.println("b1="+b1.toString());
        Bagage b2 = new Bagage("valise2",20000,56.0);
        b2.setPoids(b2.getPoids()+1000);
        System.out.println("b2="+b2.toString());
    }

    public static void testTableauMoyenne(){
        /*
        double tabVal[] = new double[6];
        tabVal[0]=2.0; tabVal[1]=4.0; tabVal[2]=10.0; tabVal[3]=8.0; tabVal[4]=6.0; tabVal[5]=12.0;
        */
        double tabVal[] = { 2.0 , 4.0 , 10.0, 8.0 , 6.0 , 12.0 };
        double somme = 0;
        for(int i=0;i<tabVal.length;i++){
            somme += tabVal[i];
        }
        double moyenne = somme / tabVal.length;
        System.out.println("moyenne="+moyenne);
        double plusGrand = tabVal[0];
        for(int i=1;i<tabVal.length;i++){
           if(tabVal[i]>plusGrand){
               plusGrand=tabVal[i];
           }
        }
        System.out.println("plusGrand="+plusGrand);

    }

    public static void testString(){
        String s1 = "2023-01-17";
        //extraire la partie mois de différentes façons et afficher cette valeur
        //String tabPartS1[] = s1.split("-");  String mois =  tabPartS1[1];
        String mois = s1.substring(5,7);//charater index from 5(inclusive) to 7(exclusive)
        //String mois = s1.substring(s1.indexOf('-')+1,s1.lastIndexOf('-'));
        //String mois = s1.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$1"); //returning second group in ()
        System.out.println("mois="+mois +" in s1="+s1);

        String chaine="YTREZA";
        //créer une nouvelle chaine inverse où tous les caractères sont dans l'ordre inverse
        StringBuilder sb =  new StringBuilder();
        for(int i=chaine.length()-1;i>=0;i--){
            sb.append(chaine.charAt(i));
        }
        String inverse=sb.toString();
        System.out.println("inverse="+inverse);
    }

    public static void testListe() {
        System.out.println("testListe");
        List<String> liste1 = new ArrayList<>();
        liste1.add("janvier"); liste1.add("fevrier"); liste1.add("mars");

        for(String s : liste1)
            System.out.println("s="+s);

        System.out.println("liste initiale=" + liste1.toString());

        List<String> listeMaj = new ArrayList<>();
        for(String s : liste1) {
            String sMaj = s.toUpperCase();
            listeMaj.add(sMaj);
        }
        liste1 = listeMaj;

        System.out.println("liste en majuscule=" + liste1.toString());
        System.out.println("taille de la liste=" + liste1.size());
        liste1.remove("JANVIER");
        System.out.println("nouvelle taille de la liste=" + liste1.size());
        System.out.println("liste apres suppression=" + liste1.toString());

        List<Double> liste2 = new ArrayList<>();
        liste2.add(5.0); //liste2.add(new Double(5.0));
        liste2.add(4.0);
        liste2.add(6.0);
        double somme=0;
        for(double val : liste2)
            somme+=val;
        System.out.println("moyenne = " + (somme/liste2.size()) + " pour liste2="+liste2);

    }

    public static void testerScanner(){
        int x=0, y=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir les valeurs de x et y (nombres entiers sans virgule)");
        System.out.print("x=");
        x=scanner.nextInt();
        System.out.print("y=");
        y=scanner.nextInt();
        int somme = x+y;
        System.out.printf("pour x=%d et y=%d la somme=x+y vaut %d",x,y,somme);
    }

    public static void testDates(){
        LocalDateTime maintenant = LocalDateTime.now();
        System.out.println("maintenant=" + maintenant);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez SVP saisir une première date au format jour/mois/année (ex: 20/03/2025) ");
        System.out.print("date1=");
        String date1AsString = scanner.next();
        System.out.println("Veuillez SVP saisir une seconde date au format jour/mois/année (ex: 27/07/2025)");
        System.out.print("date2=");
        String date2AsString = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate1 = LocalDate.parse(date1AsString, formatter);
        LocalDate localDate2 = LocalDate.parse(date2AsString, formatter);
        System.out.println("localDate1=" + localDate1 +  " localDate2="+localDate1);

        Period periode = Period.between(localDate1, localDate2) ;
        long periodeEnNombreDeJours = ChronoUnit.DAYS.between(localDate1, localDate2);
        System.out.println("écart (période) entre les deux dates: " + periode + " soit globalement " + periodeEnNombreDeJours + " jours");
    }


}
