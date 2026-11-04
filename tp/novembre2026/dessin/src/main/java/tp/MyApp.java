package tp;

import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;

public class MyApp {
    static void main() {
        System.out.println("cette application va générer un fichier dessin.svg");

        List<Figure2D> listeFigures = new ArrayList<>();

        Rectangle r = new Rectangle(100,180,200,50,"black",5,"blue");
        listeFigures.add(r);
        System.out.println(r.toString());
        System.out.println("r.perimetre="+r.perimetre());
        System.out.println("r.aire="+r.aire());
        System.out.println(r.toSvgStringWithColor());

        Cercle c = new Cercle(50,50,40,"red",5,"yellow");
        listeFigures.add(c);
        System.out.println(c.toString());
        System.out.println("c.perimetre="+c.perimetre());
        System.out.println("c.aire="+c.aire());
        System.out.println(c.toSvgStringWithColor());

        Ligne l = new Ligne(70,70,100,180,"green",5,null);
        listeFigures.add(l);
        System.out.println(l.toString());
        System.out.println(l.toSvgStringWithColor());

        String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeFigures);
        System.out.println(globalSvgContent);

        changeFigures(listeFigures);

        String globalSvgContent2 = MySvgUtil.generateGlobalSvgContent(listeFigures);
        System.out.println(globalSvgContent2);
    }

    public static void changeFigures(List<Figure2D> listeFig){
        double longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale initiale= " + longeurTotale);
        for(Figure2D fig : listeFig) {
            fig.translater(30, 20);
        }
        longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale après translation= " + longeurTotale);
        for(Figure2D fig : listeFig) {
            fig.zoomer(1.5);
        }
        longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale après zoom de coeff2= " + longeurTotale);
    }

    public static double calculerLongueurTotale(List<Figure2D> listeFig){
        double total = 0;
        for(Figure2D fig : listeFig) {
            if (fig instanceof Ligne ligne) {
                total += ligne.longueur();
            }
            else if (fig instanceof Surface surface) {
                total += surface.perimetre();
            }
        }
        return total;
    }
}
