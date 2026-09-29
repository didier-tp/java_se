package tp.svg;

import tp.figure.Figure2D;

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
}
