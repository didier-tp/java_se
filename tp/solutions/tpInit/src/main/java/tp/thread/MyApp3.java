package tp.thread;

public class MyApp3 {
    public static void main(String[] args) {
        MyThreadUtil.afficherMessage("debut de main");

        TacheCafe tacheCafeCourt = new TacheCafe(false); //code qui sera exécuté par un ou plusieur thread
        TacheCafe tacheCafeLong = new TacheCafe(true);

        Thread thread_a = new Thread(tacheCafeLong); //préparer un nouveau Thread qui va executer la tâche (en // vis à vis du Thread principal)
        thread_a.setName("thread_a");
        thread_a.start(); //démarrer le nouveau thread , celui ci s'arrêtera tout seul quand le .run() aura fini son travail

        Thread.ofPlatform().name("thread_b").start(tacheCafeLong);  //préparer un nouveau Thread qui va executer la tâche (en // vis à vis du Thread principal)

        /*
        Thread thread_c = new Thread(tacheCafeCourt); //préparer un nouveau Thread qui va executer la tâche (en // vis à vis du Thread principal)
        thread_c.setName("thread_c");
        thread_c.start();
        */
        Thread.ofVirtual().name("thread_c").start(tacheCafeCourt); //variante avec thread virtuel de java >=21

        MyThreadUtil.afficherMessage("suite du main");
    }
}
