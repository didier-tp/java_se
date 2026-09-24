package tp.svg;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import tp.figure.Figure2D;

public class MySvgUtil {

    public static String generateGlobalSvgContent(List<Figure2D> listeFig) {
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
        try {
            //ouvrir le fichier en écriture (flux élémentaire + PrintStream)
            FileOutputStream fos = new FileOutputStream(fileName);
            PrintStream ps = new PrintStream(fos);

            ps.println(generateGlobalSvgContent(listeFig));

            //fermer les flux ouverts
            ps.close(); fos.close();  //code à encore améliorer avec try with resources
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
