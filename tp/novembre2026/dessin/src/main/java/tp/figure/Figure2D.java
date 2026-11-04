package tp.figure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@NoArgsConstructor
@Getter @Setter
@ToString
public abstract class Figure2D {
    protected String couleur="black"; //couleur du trait ou du contour ("black" par défaut)
    protected Integer epaisseur=1; //épaisseur du trait ou du contour (1 par défaut)
    protected String couleurFond; //null par défaut ("none" en svg)
    //design pattern "template method" avec polymorphisme sur sous tâche abstraite .
    public String toSvgStringWithColor() {
//polymorphisme sur l'appel à this.toSvgSubString();
//où this pourra référencer une instance de Cercle ou Ligne ou Rectangle
        /*
        String beginOfSvgString = this.toSvgSubString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ps.printf("%s stroke='%s' stroke-width='%d' fill='%s'/>",
                beginOfSvgString ,
                this.couleur ,
                this.epaisseur ,
                this.couleurFond==null?"none":this.couleurFond);
        return baos.toString();
        */
        return String.format("%s stroke='%s' stroke-width='%d' fill='%s'/>",
                this.toSvgSubString() ,
                this.couleur ,
                this.epaisseur ,
                this.couleurFond==null?"none":this.couleurFond);
    }

    public  abstract String toSvgSubString() ;

    public Figure2D(String couleur, Integer epaisseur, String couleurFond) {
        this.couleur = couleur;
        this.epaisseur = epaisseur;
        this.couleurFond = couleurFond;
    }
}