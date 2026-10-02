package tp.thread;

public class MyApp4 {
    static void main() {
        MyThreadUtil.afficherMessage("début du main " );
        TacheCafe tacheCafeCourt = new TacheCafe(false);
        TacheCafe tacheCafeLong = new TacheCafe(true);
       // Thread t1 = new Thread(tacheCafeLong); t1.setName("t1");
        Thread t1 = Thread.ofVirtual().name("t1").unstarted(tacheCafeLong);
        Thread t2 = new Thread(tacheCafeLong); t2.setName("t2");
        Thread t3 = new Thread(tacheCafeCourt); t3.setName("t3");
        Thread t4 = new Thread(tacheCafeLong); t4.setName("t4");
        Thread t5 = new Thread(tacheCafeLong); t5.setName("t5");
        Thread t6 = new Thread(tacheCafeCourt); t6.setName("t6");
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start(); t6.start();
        MyThreadUtil.afficherMessage("fin du main , le prog ne s'arrête pas complétement car autres Threads pas encore terminés" );
    }
}
