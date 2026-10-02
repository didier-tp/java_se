package tp.thread;

public class MyThreadUtil {
    public static void afficherMessage(String message){
        System.out.println(message + " - " + Thread.currentThread().getName());
    }

    public static void pause(int nbMs){
        try {
            Thread.sleep(nbMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
