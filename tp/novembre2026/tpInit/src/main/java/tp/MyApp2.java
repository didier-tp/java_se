package tp;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyApp2 {
    static void main() {
        System.out.println("Hello, welcome to MyApp2!");
        //testConversion();
        //testBagage();
        //testMoyenne();
        //testString();
        //testListeString();
        //testMoyenneListeDouble();
        //testGenericMyStack();
        testerScanner();
        //testerJOptionPane();
    }

    public static void testerScanner(){
        Scanner reader = new Scanner(System.in);
        System.out.print("Veuillez saisir un nombre réel x=");
        double x = reader.nextDouble();
        System.out.print("Veuillez saisir un nombre réel y=");
        double y = reader.nextDouble();
        double sum = x + y;
        System.out.println("La somme de " + x + " et " + y + " est: " + sum);
    }

    public static void testerJOptionPane(){

        double x = Double.parseDouble(JOptionPane.showInputDialog(null,"x=" , "valeur de x ?" , JOptionPane.INFORMATION_MESSAGE));
        double y = Double.parseDouble(JOptionPane.showInputDialog(null,"y=" , "valeur de y ?" , JOptionPane.INFORMATION_MESSAGE));
        double sum = x + y;
        JOptionPane.showMessageDialog(null,"La somme de " + x + " et " + y + " est: " + sum);
    }

    public static void testListeString(){
        List<String> liste = new ArrayList<>();
        liste.add("janvier"); liste.add("fevrier"); liste.add("mars");
        System.out.println("liste = " + liste);
        for(int i=0; i<liste.size(); i++) {
            System.out.println("liste.get(" + i + ") = " + liste.get(i));
        }
        for(int i=0; i<liste.size(); i++) {
           String mois =  liste.get(i);
           liste.set(i,mois.toUpperCase());
        }
        for(String mois : liste) {
            System.out.println("mois = " + mois);
        }
        System.out.println("taille initiale liste = " + liste.size());
        liste.add("avril"); liste.add("mai");
        System.out.println("liste après ajout de 2 éléments = " + liste);
        liste.remove("JANVIER");
        System.out.println("liste après suppression de janvier = " + liste);
    }

    public static void testMoyenneListeDouble(){
        List<Double> liste  = Arrays.asList(12.0, 14.0, 18.0, 20.0 , 10.0 , 8.0);
        double somme = 0.0;
        for(Double d : liste) {
            somme += d;
        }
        final double moyenne = somme / liste.size();
        System.out.println("La moyenne (de liste de Double) est: " + moyenne);
    }

    public static void testGenericMyStack() {
        MyStack<Integer> pileEntiers = new MyStack<Integer>();
        pileEntiers.push(2);
        pileEntiers.push(1);
        System.out.println(pileEntiers.pop());//1
        System.out.println(pileEntiers.pop());//2

        MyStack<String> pileChaines = new MyStack<String>();
        pileChaines.push("abc");
        pileChaines.push("def");
        System.out.println(pileChaines.pop());//"def"
        System.out.println(pileChaines.pop());//"abc"
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
        final double moyenne = somme / tab.length; //final ressemble à const du langage javascript
        //moyenne = 9; //modification impossible car moyenne est final
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
