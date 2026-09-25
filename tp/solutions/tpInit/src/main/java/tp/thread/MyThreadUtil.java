package tp.thread;

public class MyThreadUtil {
	
	public static void afficherMessage(String msg) {
		//System.out.println(msg + " depuis le thread " + Thread.currentThread().getName());
		//System.out.println(msg + " depuis le thread " + Thread.currentThread().toString());
		System.out.println(msg + " depuis le thread " + Thread.currentThread().getName() + "[" + Thread.currentThread().getClass().getSimpleName() + "]");
	}

	public static void pause(long nbMs){
		try {
			Thread.sleep(nbMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
