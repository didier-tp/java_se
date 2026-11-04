package tp;

import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {

    public static void main(String[] args) {
        double coeffZoom = 1;
        int dx=0;
        int dy=0;
        String typeFig=null;
        //premiersTests();
        //…..du code sera à ajouter ici ...
        enchainerTransformationsEtGenerationFichierSvg(coeffZoom,dx,dy,typeFig);
    }
    public static List<Figure2D> buildListeFigures() {
        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(new Rectangle(100,180,200,50,"black",5,"blue"));
        listeFigures.add(new Ligne(150,100,250,100,"green",4/*,null*/));
        listeFigures.add(new Cercle(100,100,30,"black",3,"red"));
        return listeFigures;
    }
    public static void enchainerTransformationsEtGenerationFichierSvg(double coeffZoom ,
                                                                      int dx, int dy , String typeFig) {
        List<Figure2D> listeInitialeFigures = buildListeFigures();
        List<Figure2D> listeTransformeeFigures =
                listeInitialeFigures.stream()
        //effecter une premiere transformation de type zoomer(coeffZoom)
        //effecter une seconde transformation de type translation(dx,dy)
        //filtrer selon le type de figure (typeFig , ex :"Cercle") via un test de type instanceof ...
                        .collect(Collectors.toList());
        //futur MySvgUtil.generateSvgFile(listeTransformeeFigures, "dessin2.svg");
        String globalSvgContent = MySvgUtil.generateGlobalSvgContent(listeTransformeeFigures);
        System.out.println(globalSvgContent);
    }

    static void premiersTests() {
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

    public static void changeFigures(List<? extends Transformable> listeFig){
        double longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale initiale= " + longeurTotale);
        for(Transformable choseTransformable : listeFig) {
            choseTransformable.translater(30, 20);
        }
        longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale après translation= " + longeurTotale);
        for(Transformable choseTransformable : listeFig) {
            choseTransformable.zoomer(1.5);
        }
        longeurTotale = calculerLongueurTotale(listeFig);
        System.out.println("longeurTotale après zoom de coeff2= " + longeurTotale);
    }

    public static double calculerLongueurTotale(List<? extends Transformable> listeFig){
        double total = 0;
        for(Transformable fig : listeFig) {
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
