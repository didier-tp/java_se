package tp;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp.figure.*;
import tp.svg.MySvgUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MyApp {

    //private static final Logger log= LoggerFactory.getLogger(MyApp.class);

    //NB: avec IntelliJ , "Edit Run Configurations..." puis "Program arguments"
    //puis saisir par exemple : --dx=10 --dy=20 --coeffZoom=2
    public static void main(String[] args) {
        double coeffZoom = 1;
        int dx=0;
        int dy=0;
        String typeFig=null;
        //premiersTests();

        Map<String,String> mapArgs = parseArgs(args);
        log.debug("mapArgs="+mapArgs);
        String valDxArgsAsString = mapArgs.get("dx");
        if(valDxArgsAsString != null) {
            try {
                dx = Integer.parseInt(valDxArgsAsString);
            } catch (NumberFormatException e) {
                log.error("Erreur: la valeur de dx n'est pas un entier valide : " + valDxArgsAsString);
                //System.err.println("Erreur: la valeur de dx n'est pas un entier valide : " + valDxArgsAsString);
                log.error("dx aura la valeur par défaut de 0");
            }
        }
        String valDyArgsAsString = mapArgs.get("dy");
        if(valDyArgsAsString != null) {
            try {
                dy = Integer.parseInt(valDyArgsAsString);
            } catch (NumberFormatException e) {
                log.error("Erreur: la valeur de dy n'est pas un entier valide : " + valDyArgsAsString);
                log.error("dy aura la valeur par défaut de 0");
            }
        }
        String valCoeffZoomArgsAsString = mapArgs.get("coeffZoom");
        if(valCoeffZoomArgsAsString != null) {
            try {
                coeffZoom = Double.parseDouble(valCoeffZoomArgsAsString);
            } catch (NumberFormatException e) {
                log.error("Erreur: la valeur de coeffZoom n'est pas un nombre réel valide : " + valCoeffZoomArgsAsString);
                log.error("coeffZoom aura la valeur par défaut de 1.0");
            }
        }
        String typeFigArgsAsString = mapArgs.get("typeFig");
        if(typeFigArgsAsString != null)
            typeFig = typeFigArgsAsString;
        log.debug("dx=%d , dy=%d , coeffZoom=%f , typeFig=%s\n", dx, dy, coeffZoom,typeFig);
        //...

        enchainerTransformationsEtGenerationFichierSvg(coeffZoom,dx,dy,typeFig);
    }

    public static Map<String,String> parseArgs(String[] args) {
        Map<String,String> mapArgs = new java.util.HashMap<>();
        for (String arg : args) {
            if(arg.startsWith("--")) {
                String[] parts = arg.split("=");
                if (parts.length == 2)
                    mapArgs.put(parts[0].substring(2), parts[1]);
            }
        }
        return mapArgs;
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
                        .map(fig -> { fig.zoomer(coeffZoom); return fig; })
        //effecter une seconde transformation de type translation(dx,dy)
                        .map(fig -> { fig.translater(dx,dy); return fig; })
        //filtrer selon le type de figure (typeFig , ex :"Cercle") via un test de type instanceof ...
                        .filter(fig -> {
                            if(typeFig == null) return true;
                            //
                             /*
                            else if(typeFig.equals("Cercle")) return (fig instanceof Cercle);
                            else if(typeFig.equals("Rectangle")) return (fig instanceof Rectangle);
                            else if(typeFig.equals("Ligne")) return (fig instanceof Ligne);
                            else return false;
                            */
                            boolean estDuTypeSouhaite = switch(typeFig) {
                                case "Cercle" -> (fig instanceof Cercle) ;
                                case "Rectangle" -> (fig instanceof Rectangle) ;
                                case "Ligne" ->(fig instanceof Ligne);
                                default ->  false;
                            };
                            return estDuTypeSouhaite;
                        })
                        //.collect(Collectors.toList());
                        .toList();
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
