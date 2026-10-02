package tp.thread;

public class TacheCafe implements Runnable {

    private static int compteur = 0; //si static : meme compteur pour cafe court ou long

    private boolean estLong=false; //par defaut

    public TacheCafe(boolean estLong) {
        this.estLong = estLong;
    }

    public TacheCafe() {
        // café court par defaut avec estLong à false
    }

    public String typeCafe(){
        return this.estLong?"long":"court";
    }

    @Override
    public void run(){
        int numero =0; //numéro du café

        /*
        //version pas fiable ou buggée sans synchronized
            TacheCafe.compteur++;
            numero = TacheCafe.compteur;
         */

        //version avec compteur static
        synchronized(TacheCafe.class) {
            TacheCafe.compteur++;
            numero = TacheCafe.compteur;
        }


        /*
        //version avec compteur pas static (différent pour taches "café long" et "café court")
        synchronized(this) {
            this.compteur++;
            numero = this.compteur;
        }
        */


        MyThreadUtil.afficherMessage("début de préparation du café num="+numero + " " + this.typeCafe()  );
        if(this.estLong)
            MyThreadUtil.pause(4000);
        else
            MyThreadUtil.pause(2000);
        MyThreadUtil.afficherMessage("fin de préparation du café  num="+numero + " " +this.typeCafe()  );
    }
}
