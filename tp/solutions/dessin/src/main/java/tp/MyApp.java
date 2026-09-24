package tp;

import tp.dessin.Dessin;
import tp.dessin.dao.DaoDessin;
import tp.dessin.dao.DaoDessinJdbc;
import tp.figure.*;
import tp.svg.MySvgUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {

    //exemples de lignes de commandes : java tp.MyApp
    // java tp.MyApp --coeffZoom=1.5  --dx=50 --dy=20 --typeFig=Cercle
    // soit dans .bat ou .sh , soit dans IDE , Modify Run config , program argument : --coeffZoom=1.5  ...
    public static void main(String[] args) {
        double coeffZoom = 1;
        int dx=0;
        int dy=0;
        String typeFig=null;
        //premiersTests();
        if(args.length>0){
            for(String fullArg: args){
                String argName=null , argValue=null;
                if(!fullArg.startsWith("--"))
                    throw new RuntimeException("erreur de syntyaxe sur argument , attendu --argName=argValue avec double tiret");
                try {
                    // --argName=argValue
                    String argWithoutTiretTiret = fullArg.substring(2);
                    String[] argParts = argWithoutTiretTiret.split("=");
                    argName= argParts[0];
                    argValue = argParts[1];
                } catch (Exception e) {
                    throw new RuntimeException("erreur de syntyaxe sur argument , attendu --argName=argValue par exemple --coeffZoom=2");
                }
                switch(argName){
                    case "coeffZoom" :
                        try {
                            coeffZoom = Double.parseDouble(argValue);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("argument coeffZoom invalide (pas numerique)");
                        }
                        break;
                    case "dx" :
                        try {
                            dx = Integer.parseInt(argValue);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("argument dx invalide (pas numerique)");
                        }
                        break;
                    case "dy" :
                        try {
                            dy = Integer.parseInt(argValue);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("argument dy invalide (pas numerique)");
                        }
                        break;
                    case "typeFig" : typeFig=argValue; break;
                }
            }
        }
        System.out.printf("coeffZoom=%f dx=%d dy=%d typeFig=%s",coeffZoom ,dx,dy,typeFig);
        enchainerTransformationsEtGenerationFichierSvg(coeffZoom,dx,dy,typeFig);
    }

    public static List<Figure2D> buildListeFigures() {
        List<Figure2D> listeFigures = new ArrayList<>();
        listeFigures.add(new Rectangle(100,180,200,50,"black",5,"blue"));
        listeFigures.add(new Ligne(150,100,250,100,"green",4));
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
                        //filtrer selon le type de figure (typeFig , ex:"Cercle") via un test de type instanceof ...
                        .collect(Collectors.toList());
        MySvgUtil.generateSvgFile(listeTransformeeFigures, "dessin2.svg");
        try {
            DaoDessin daoDessin = new DaoDessinJdbc();
            daoDessin.insert(new Dessin(null,"nouvelle version de dessin2" , "dessin2.svg" , "généré par code java - " + LocalDateTime.now().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void premiersTests() {
        //déplacer ici les tests effectués au sein de la partie 1
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
