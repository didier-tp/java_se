package tp.svg;

import tp.figure.Figure2D;
import tp.figure.Ligne;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class MySvgUtil {
    public static String generateGlobalSvgContent(List<Figure2D> listeFig) {
        //utiliser un buffer sous forme d'une instance de la classe StringBuilder
        StringBuilder buffer = new StringBuilder();
        //ajouter au buffer la première ligne d'un bloc svg
        //<svg xmlns='http://www.w3.org/2000/svg' height='400' width='500'>
        buffer.append("<svg xmlns='http://www.w3.org/2000/svg' height='900' width='1200'>\n");
        //boucler sur chaque élément de la liste listeFig
        //et ajouter au buffer une ligne dont la valeur est construite
        //via la méthode .toSvgStringWithColor() appelant indirectement
        //la méthode polymorphe .toSvgSubString()
        for (Figure2D figure : listeFig) {
            buffer.append(figure.toSvgStringWithColor() + "\n");
            /*if(figure instanceof Ligne ligne){
                //Ligne ligne = (Ligne) figure;
                System.out.println("ligne");
            }*/
        }
        //ajouter au buffer la dernière ligne d'un bloc svg
        //</svg>
        buffer.append("</svg>\n");
        //retourner la transformation du buffer en String
        return buffer.toString();
    }

    public static void generateSvgFile(List<Figure2D> listeTransformeeFigures, String filename) {
        String globalSvgContent = generateGlobalSvgContent(listeTransformeeFigures);
        /*
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) {
            writer.write(globalSvgContent);
            System.out.println("Fichier SVG généré avec succès : " + filename);
        } catch (java.io.IOException e) {
            System.err.println("Erreur lors de la génération du fichier SVG : " + e.getMessage());
        }*/
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.println(globalSvgContent);
            System.out.println("Fichier SVG généré avec succès : " + filename);
        } catch (java.io.IOException e) {
            System.err.println("Erreur lors de la génération du fichier SVG : " + e.getMessage());
        }
        //implicit finally block with try-with-resources and auto-closable
    }
}
