package tp.basic;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class DevinerNombreAppV2 {
    public static void main(String[] args) {
            Random random = new Random();
            int n = 0; //nombrePropose
            int nbEssais=0;
            int nombreADeviner = 1 + random.nextInt(100); //entre 1 et 100 (1 + 0à99)
            System.out.println("");
            JOptionPane.showMessageDialog(null,"**** NOMBRE A DEVINER (entre 1 et 100) ****");

            do {
                n = Integer.parseInt(JOptionPane.showInputDialog(null,"n=" , "valeur du nombre à deviner ?" ,JOptionPane.INFORMATION_MESSAGE));
                nbEssais++;
                if(nombreADeviner>n)
                    JOptionPane.showMessageDialog(null,"le nombre a deviner est plus grand que n="+n);
                else if(nombreADeviner<n)
                    JOptionPane.showMessageDialog(null,"le nombre a deviner est plus petit que n="+n);
            }while(nombreADeviner!=n);
             JOptionPane.showMessageDialog(null,"vous avez trouver la valeur n="+nombreADeviner+ " du nombre à deviner en "+nbEssais+" essai(s)");
    }

}
