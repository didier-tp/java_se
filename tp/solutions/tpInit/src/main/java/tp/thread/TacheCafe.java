package tp.thread;

public class TacheCafe implements Runnable {
	
	private boolean estLong =false;
	//private int compteur=0; // si pas static compteur distinct pour chaque instance de la tache (compteur de café long + compteur de café court)
	private static int compteur=0; //si static : compteur unique pour café court et long
	
	TacheCafe(boolean estLong){
		this.estLong=estLong;
	}

	TacheCafe(){
		this(false);
	}

	String longOuCourt(){
		return estLong?"long":"court";
	}

	@Override
	public void run() {
		int numero = 0;

		//synchronized(this) {
		synchronized(TacheCafe.class) {
			TacheCafe.compteur++; //ou this.compteur++; si pas static
			numero=compteur;
		}
		MyThreadUtil.afficherMessage("café numéro "+numero + " -- " + longOuCourt() +" en début préparation");

		//NB: PAS BESOIN de synchronized si manipulation de variables locales (ex: i,tempsPrepa, numero  ou input ou en return)
		int tempsPrepa =  estLong?4:2;
		for(int i=tempsPrepa;i>0;i--) {
			MyThreadUtil.afficherMessage("\tcafé numéro " + numero + ": " + i);
			MyThreadUtil.pause(1000);
		}

		MyThreadUtil.afficherMessage("café numéro "+numero + " -- " + longOuCourt() + " prêt");
	}

}
