package tp;

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        //testConversion();
        testPersonne();
    }

    public static void testPersonne(){
        //new Personne + appel de afficher sur un objet de type Personne
        Personne p1 =null ;
        p1 = new Personne();
        p1.afficher();
        p1.setNom("Alice");   p1.setAge(30);   p1.setPoids(65.5);
        p1.afficher();
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
