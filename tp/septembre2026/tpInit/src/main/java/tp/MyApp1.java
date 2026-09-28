package tp;

public class MyApp1 {
    static void main() {
        System.out.println("Hello world");
        testElementaire();
        testPersonne();
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

    public static void testPersonne(){
        Personne p1=null;
        p1=new Personne();
        p1.afficher();
        p1.nom="didier";
        p1.age=18;
        p1.poids=90.0;
        p1.afficher();
        p1.age=-2567;
        p1.afficher();
    }
}
