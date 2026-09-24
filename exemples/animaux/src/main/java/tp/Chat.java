package tp;

public class Chat extends AnimalDomestique{
    private int nbHeuresSommeil;

    @Override
    public void parler(){
        System.out.println("miaou miaou");
    }

    @Override
    public void decrire(){
        System.out.println("Je suis un chat qui dort " + nbHeuresSommeil + " heures par jour");
        System.out.print("\t"); super.decrire();
    }

    public void ronronner(){
        System.out.println("ronron");
    }

    public Chat(String nom, Double poids, int nbHeuresSommeil) {
        super(nom, poids);
        this.nbHeuresSommeil = nbHeuresSommeil;
    }

    public Chat(String nom, Double poids){
        this(nom,poids,12); //12h par défaut
    }

    public Chat(String nom){
        this(nom,4.5); //4.5kg par défaut
    }

    public Chat(){
        this("?");
    }

    public int getNbHeuresSommeil() {
        return nbHeuresSommeil;
    }

    public void setNbHeuresSommeil(int nbHeuresSommeil) {
        this.nbHeuresSommeil = nbHeuresSommeil;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "nbHeuresSommeil=" + nbHeuresSommeil +
                ", nom='" + nom + '\'' +
                ", poids=" + poids +
                '}';
    }
}
