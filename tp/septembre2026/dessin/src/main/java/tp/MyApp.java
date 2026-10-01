package tp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {

    private static final Logger log= LoggerFactory.getLogger(tp.MyApp.class);

    static void main(String[] args) {
        double coeffZoom = 1;
        int dx=0;
        int dy=0;
        String typeFig=null;
        //premiersTests();
        //…..du code sera à ajouter ici ...

        for(String arg : args){
            //System.out.println(arg);  // --coeffZoom=1.5 ou bien --dx=20
            String argSansTiretTiret = arg.substring(2);
            String[] argParts = argSansTiretTiret.split("=");
            String nomArg = argParts[0];
            String valArg = argParts[1];
            log.debug("nomArg={} valArg={}\n",nomArg,valArg);
            try {
            switch(nomArg){
                case "dx": dx=Integer.parseInt(valArg);    break;
                case "typeFig": typeFig=valArg;    break;
                case "dy":
                        dy=Integer.parseInt(valArg);
                    break;
                case "coeffZoom":
                        coeffZoom=Double.parseDouble(valArg);
                    break;
              }
            } catch (NumberFormatException e) {
                log.error( nomArg + "=" + valArg + " est invalide , ca doit être numerique");
                //throw new RuntimeException("erreur de conversion sur arg="+nomArg , e);
            }
        }
        log.info("coeffZoom={} dx={} dy={} typeFig={}\n" , coeffZoom , dx , dy , typeFig);
        enchainerTransformationsEtGenerationFichierSvg(coeffZoom,dx,dy,typeFig);
    }

    public static List<Figure2D> buildListeFigures() {
        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(new Rectangle(100,180,200,50,"black",5,"blue"));
        listeFigures.add(new Ligne(150,100,250,100,"green",4 , null));
        listeFigures.add(new Cercle(100,100,30,"black",3,"red"));
        return listeFigures;
    }
    public static void enchainerTransformationsEtGenerationFichierSvg(double coeffZoom ,
                                                                      int dx, int dy , String typeFig) {
        List<Figure2D> listeInitialeFigures = buildListeFigures();
        List<Figure2D> listeTransformeeFigures =
                listeInitialeFigures.stream()
                        .filter( (fig) -> typeFig!=null?fig.getClass().getSimpleName().equals(typeFig):true )
                        .map( (fig) -> { fig.zoomer(coeffZoom); return fig;})
                        .map( (fig) -> { fig.translater(dx,dy); return fig;})
                //effecter une premiere transformation de type zoomer(coeffZoom)
                //effecter une seconde transformation de type translation(dx,dy)
                //filtrer selon le type de figure (typeFig , ex :"Cercle") via un test de type instanceof ...
                        .collect(Collectors.toList());
                MySvgUtil.generateSvgFile(listeTransformeeFigures, "dessin2.svg");
        //String globalContent = MySvgUtil.generateGlobalSvgContent(listeTransformeeFigures);
        //System.out.println(globalContent);
    }

    static void premiersTests(){
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
