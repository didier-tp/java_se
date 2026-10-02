package tp.thread;

public class TacheCafe implements Runnable {

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
        //..
        MyThreadUtil.afficherMessage("début de préparation du café " + this.typeCafe()  );
        if(this.estLong)
            MyThreadUtil.pause(4000);
        else
            MyThreadUtil.pause(2000);
        MyThreadUtil.afficherMessage("fin de préparation du café " + this.typeCafe()  );
    }
}
