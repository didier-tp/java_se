package tp.thread;

public class TacheCafe implements Runnable{

    private boolean estLong;

    public TacheCafe(boolean estLong) {
        this.estLong = estLong;
    }

    public TacheCafe(){
        this(false);
    }

    @Override
    public void run() {
        MyThreadUtil.afficherMessage("cafe en preparation");
        MyThreadUtil.pause(this.estLong?4000:2000);
        MyThreadUtil.afficherMessage("cafe pret");
    }
}
