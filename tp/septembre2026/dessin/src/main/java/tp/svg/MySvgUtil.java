package tp.svg;

import tp.figure.Figure2D;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

public class MySvgUtil {
    public static String generateGlobalSvgContent(List<Figure2D> listeFig) {
        //utiliser un buffer sous forme d'une instance de la classe StringBuilder
        //ajouter au buffer la première ligne d'un bloc svg
        //<svg xmlns='http://www.w3.org/2000/svg' height='400' width='500'>
        //boucler sur chaque élément de la liste listeFig
        //et ajouter au buffer une ligne dont la valeur est construite
        //via la méthode .toSvgStringWithColor() appelant indirectement
        //la méthode polymorphe .toSvgSubString()
        //ajouter au buffer la dernière ligne d'un bloc svg
        //</svg>
        //retourner la transformation du buffer en String
        StringBuilder buffer = new StringBuilder();
        buffer.append("<svg xmlns='http://www.w3.org/2000/svg' height='400' width='500'>\n");
        for(Figure2D fig:listeFig){
            buffer.append(fig.toSvgStringWithColor()+"\n");
        }
        buffer.append("</svg>");
        return buffer.toString();
    }


    public static void generateSvgFile(List<Figure2D> listeFig , String fileName) {
//ex de fileName : "dessin.svg"
//ouvrir le fichier en écriture (flux élémentaire + PrintStream)
//écrire dans ce fichier le résultat de la sous méthode generateGlobalSvgContent()
//fermer les flux ouverts
        String svgGlobalContent = generateGlobalSvgContent(listeFig);
        try(/*FileOutputStream fos = new FileOutputStream(fileName);
            PrintStream ps = new PrintStream(fos)*/
                PrintStream ps = new PrintStream(fileName)
                /*PrintWriter ps = new PrintWriter(fileName)*/
        ){
               ps.print(svgGlobalContent);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //automatic .close() in automatic .finally{ } block
    }

}
