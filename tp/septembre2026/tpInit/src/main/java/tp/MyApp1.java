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
        p1.setNom("didier");
        //p1.age=18; //interdit si age est "private"
        p1.setAge(18);
        p1.setPoids(90.0);
        p1.afficher();
        System.out.println("p1="+p1.toString());
        p1.setAge(-2567);
        p1.afficher();
        Personne p2 = new Personne("toto", 25 , 82.5);
        p2.afficher();
        Personne p3 = new Personne("toto", 25 , 82.5);
        if(p2.equals(p3)) //bon comportement que si generate .equals() sur classe Personne
            System.out.println("p2 et p3 ont mêmes valeurs internes");
        else
            System.out.println("p2 et p3 ont valeurs internes différentes");
    }
}
