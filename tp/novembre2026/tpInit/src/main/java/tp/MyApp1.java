package tp;

public class MyApp1 {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to MyApp1 ");

        testPersonne();

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


}
