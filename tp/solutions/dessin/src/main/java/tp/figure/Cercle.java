package tp.figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Cercle extends Figure2D implements Surface{
    private int cx;//x du centre du cercle
    private int cy;//y du centre du cercle
    private int r; //rayon

    public Cercle( int cx, int cy, int r,String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }
    public Cercle(){
        this(0,0,0,"black",1,null);
    }

            @Override
    public String toSvgSubString() {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                ps.printf("<circle cx='%d' cy='%d' r='%d'", cx,cy,r);
                return baos.toString();
    }

    @Override
    public String toString() {
        return "Cercle{" +
                "cx=" + cx +
                ", cy=" + cy +
                ", r=" + r +
                ", couleur='" + couleur + '\'' +
                ", epaisseur=" + epaisseur +
                ", couleurFond='" + couleurFond + '\'' +
                '}';
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

    public double perimetre(){
        return 2 * Math.PI * r;
    }

    public double aire(){
        return Math.PI * r *r ;
    }

    @Override
    public void translater(int dx, int dy) {
        this.cx+=dx;
        this.cy+=dy;
    }

    @Override
    public void zoomer(double coeff) {
        this.cx*=coeff;
        this.cy*=coeff;
        this.r*=coeff;
    }
}
