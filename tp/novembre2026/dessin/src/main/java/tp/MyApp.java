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
    }
}
