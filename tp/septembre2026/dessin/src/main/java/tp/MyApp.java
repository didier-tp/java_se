package tp;

import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;

public class MyApp {
    static void main() {
        System.out.println("cette application va générer un fichier dessin.svg");

        Rectangle r = new Rectangle(100,80,1200,50,"red",5,"blue");
        System.out.println(r.toString());
        System.out.println(r.toSvgStringWithColor());
        System.out.println("r.perimetre="+r.perimetre());
        System.out.println("r.aire="+r.aire());

        Cercle c = new Cercle(60,80,40,"red",5,"green");
        System.out.println(c.toSvgStringWithColor());
        System.out.println(c.toString());
        System.out.println("c.perimetre="+c.perimetre());
        System.out.println("c.aire="+c.aire());

        Ligne l = new Ligne(100,10,200,150,"blue",4,"red");
        System.out.println(l.toSvgStringWithColor());
        System.out.println(l.toString());

        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(l); listeFigures.add(r); listeFigures.add(c);
        //listeFigures.add(new Cercle(150,100,50,"blue",3,"red"));

        System.out.println("---- globalSvgContent or generate dessin.svg ---");
        String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeFigures); //v1
        System.out.println(globalSvgContent); //V1

        for(Figure2D figure : listeFigures){
            System.out.println("\t typeFig=" + figure.typeFig(true));
        }

        changeFigures(listeFigures);

        /*
        //exemple de code pas astucieux (PAS BIEN):
        for(Figure2D figure : listeFigures){
            if( figure instanceof Cercle){
                Cercle cercle = (Cercle) figure;
                //cercle.genererSvgPourCercle();
            }
            else if( figure instanceof Rectangle){
                Rectangle rectangle = (Rectangle) figure;
                //rectangle.genererSvgPourRectangle();
            }
        }
        */
    }

    public static void changeFigures(List<Figure2D> listeFig){
        System.out.println("longeur totale initiale=" + longeurTotale(listeFig));
        for(Figure2D fig : listeFig){
            fig.translater(30,20);
        }
        System.out.println("longeur totale après translation(dx=30,dy=20) =" + longeurTotale(listeFig));
        for(Figure2D fig : listeFig){
            fig.zoomer(2.0);
        }
        System.out.println("longeur totale après zoom de coeff=2.0 =" + longeurTotale(listeFig));
        System.out.println("---- globalSvgContent after translation(dx=30,dy=20) et zoom coeff=2 ---");
        String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeFig); //v1
        System.out.println(globalSvgContent); //V1
    }

    public static double longeurTotale(List<Figure2D> listeFig){
        double longeurTotale = 0;
        for(Figure2D fig : listeFig){
            if(fig instanceof Surface){
                Surface s = (Surface) fig;
                longeurTotale += s.perimetre();
            }
            else if(fig instanceof Ligne){
                Ligne l = (Ligne) fig;
                longeurTotale += l.longueur();
            }
        }
        return longeurTotale;
    }
}
