package tp;

public class MyApp {
    public static void main(String[] args) {
        exempleAnimauxDomestiques();
    }

    public static void exempleAnimauxDomestiques(){
        /*
        //possible en V1 seulement (sans abstract sur AnimalDomestique)
         AnimalDomestique a1 = new AnimalDomestique();
         a1.decrire(); a1.parler();
       */


       Chat chat1 = new Chat("felix" , 5.2);
       chat1.decrire(); chat1.parler(); chat1.ronronner();

       Chien chien1 = new Chien("medor" , 11.2);
       chien1.decrire(); chien1.parler(); chien1.monterLaGarde();

       AnimalDomestique a2 = new Chat("malo",3.9);
       a2.decrire(); a2.parler();
       if( a2 instanceof Chat){
           ((Chat) a2).ronronner();
       }

       AnimalDomestique a3 = new Chien("rantanplan",13.7);
       a3.decrire(); a3.parler();
       if( a3 instanceof Chien){
           ((Chien) a3).monterLaGarde();
       }

        AnimalDomestique a4 =null;
       /*
         //en V1 seulement (sans abstract sur AnimalDomestique)
        a4 = new AnimalDomestique("cochon d'inde",2.9);
        a4.parler(); // ...
        */

        a4 = new Chat("felix",3.9);
        a4.parler(); //miaou miaou
        a4 = new Chien("medor",9.9);
        a4.parler(); //waouf waouf
    }
}
