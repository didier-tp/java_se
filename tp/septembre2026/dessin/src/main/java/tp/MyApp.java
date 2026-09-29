package tp;

import tp.figure.Cercle;
import tp.figure.Figure2D;
import tp.figure.Ligne;
import tp.figure.Rectangle;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;

public class MyApp {
    static void main() {
        System.out.println("cette application va générer un fichier dessin.svg");

        Rectangle r = new Rectangle(100,180,200,50,"red",5,"blue");
        System.out.println(r.toString());
        System.out.println(r.toSvgStringWithColor());
        System.out.println("r.perimetre="+r.perimetre());
        System.out.println("r.aire="+r.aire());

        Cercle c = new Cercle(100,180,80,"red",5,"green");
        System.out.println(c.toSvgStringWithColor());
        System.out.println(c.toString());
        System.out.println("c.perimetre="+c.perimetre());
        System.out.println("c.aire="+c.aire());

        Ligne l = new Ligne(100,10,200,150,"blue",4,"red");
        System.out.println(l.toSvgStringWithColor());
        System.out.println(l.toString());

        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(l); listeFigures.add(r); listeFigures.add(c);
        listeFigures.add(new Cercle(150,100,50,"blue",3,"red"));

        System.out.println("---- globalSvgContent or generate dessin.svg ---");
        String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeFigures); //v1
        System.out.println(globalSvgContent); //V1

        for(Figure2D figure : listeFigures){
            System.out.println("\t" + figure.typeFig(true));
        }

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
}
