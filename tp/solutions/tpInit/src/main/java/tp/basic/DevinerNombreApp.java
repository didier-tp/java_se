package tp.basic;

import java.util.Random;
import java.util.Scanner;

public class DevinerNombreApp {
    public static void main(String[] args) {
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);
            int n = 0; //nombrePropose
            int nbEssais=0;
            int nombreADeviner = 1 + random.nextInt(100); //entre 1 et 100 (1 + 0à99)
            System.out.println("**** NOMBRE A DEVINER (entre 1 et 100) ****");
            //System.out.println("nombreADeviner="+nombreADeviner); //temp (en phase de debug)
            do {
                System.out.println("Veuillez proposer une nouvelle la valeur du nombre à deviner");
                System.out.print("n=");
                n = scanner.nextInt();
                nbEssais++;
                if(nombreADeviner>n)
                    System.out.println("le nombre a deviner est plus grand que n="+n);
                else if(nombreADeviner<n)
                    System.out.println("le nombre a deviner est plus petit que n="+n);
            }while(nombreADeviner!=n);
            System.out.printf("vous avez trouver la valeur n=%d du nombre à deviner en %d essai(s)",nombreADeviner,nbEssais);
    }

}
