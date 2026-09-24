package tp;

import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;

public class MyAppV1 {
    public static void main(String[] args) {
        System.out.println("cette application va générer un fichier dessin.svg");

        Ligne l = new Ligne(10,10,80,10,"green",4);
        System.out.println(l.toString());
        System.out.println("l.longueur="+l.longueur());
        System.out.println(l.toSvgStringWithColor());

        Rectangle r = new Rectangle(100,180,200,50,"black",5,"blue");
        System.out.println(r.toString());
        System.out.println("r.perimetre="+r.perimetre());
        System.out.println("r.aire="+r.aire());
        System.out.println(r.toSvgStringWithColor());

        Cercle c = new Cercle(150,50,50,"black",5,"red");
        System.out.println(c.toString());
        System.out.println("c.perimetre="+c.perimetre());
        System.out.println("c.aire="+c.aire());
        System.out.println(c.toSvgStringWithColor());

        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(l); listeFigures.add(r); listeFigures.add(c);

        System.out.println("---- globalSvgContent or generate dessin.svg ---");
        // String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeFigures); //v1
        //System.out.println(globalSvgContent); //V1
        MySvgUtil.generateSvgFile(listeFigures,"dessin.svg");

        changeFigures(listeFigures);
    }

    public static double longueurTotale(List<Figure2D> listeFig){
        double longueur = 0;
        for(Figure2D fig : listeFig){
            if(fig instanceof Surface){
                longueur+=((Surface)fig).perimetre();
            }else if(fig instanceof Ligne){
                longueur+=((Ligne)fig).longueur();
            }
        }
        return longueur;
    }

    public static void changeFigures(List<Figure2D> listeFig){
        double longueurTotale1 = longueurTotale( listeFig);
        System.out.println("longueurTotale1 (initiale)="+longueurTotale1);
        for(Figure2D fig : listeFig){
            fig.translater(30,20);
        }
        double longueurTotale2 = longueurTotale( listeFig);
        System.out.println("longueurTotale2 (apres translation dx=30,dy=20)="+longueurTotale2);
        for(Figure2D fig : listeFig){
            fig.zoomer(2);
        }
        double longueurTotale3 = longueurTotale( listeFig);
        System.out.println("longueurTotale3 (apres zoom coeff=2)="+longueurTotale3);
    }
}
