package tp.thread;

public class MyApp3 {

    static void main() {
        MyThreadUtil.afficherMessage("debut main");
        Thread t1 =Thread.ofVirtual().name("t1-long").unstarted(new TacheCafe(true));
        Thread t2 =Thread.ofVirtual().name("t2-long").unstarted(new TacheCafe(true));
        Thread t3 =Thread.ofVirtual().name("t3-court").unstarted(new TacheCafe(false));
        t1.start();t2.start();t3.start();
        MyThreadUtil.afficherMessage("milieu main");
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MyThreadUtil.afficherMessage("fin main");
    }
}
