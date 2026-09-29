package tp.figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Cercle extends Figure2D {
    private int cx;//x du centre du cercle
    private int cy;//y du centre du cercle
    private int r; //rayon


    @Override
    public String toSvgSubString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ps.printf("<circle cx='%d' cy='%d' r='%d'",
                this.cx ,
                this.cy ,
                this.r);
        return baos.toString();
    }

    public Cercle() {
    }

    public Cercle( int cx, int cy, int r,String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    public double perimetre(){
        return 2 * Math.PI * this.r;
    }

    public double aire(){
        return Math.PI * Math.pow(this.r,2);
    }


    @Override
    public String toString() {
        return "Cercle{" +
                "cx=" + cx +
                ", cy=" + cy +
                ", r=" + r +
                "} heritant de " + super.toString();
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
