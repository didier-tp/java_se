package tp.thread;

public class MyApp4 {
    static void main() {
        MyThreadUtil.afficherMessage("début du main " );
        TacheCafe tacheCafeCourt = new TacheCafe(false);
        TacheCafe tacheCafeLong = new TacheCafe(true);
        Thread t1 = new Thread(tacheCafeLong); t1.setName("t1");
        Thread t2 = new Thread(tacheCafeLong); t2.setName("t2");
        Thread t3 = new Thread(tacheCafeCourt); t3.setName("t3");
        t1.start(); t2.start(); t3.start();
        MyThreadUtil.afficherMessage("fin du main , le prog ne s'arrête pas complétement car autres Threads pas encore terminés" );
    }
}
