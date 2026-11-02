package tp;

public class MyApp {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        //testConversion();
        testPersonne();
        String s1 ="abc";
        String s0 ="ab";
        String s2 =s0 + "c";
        //if(s1==s2) { //pas ok
        if(s1.equals(s2)) { //ok avec .equals() dejà codé dans la classe String
            System.out.println("s1 et s2 ont meme valeurs internes");
        }
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
