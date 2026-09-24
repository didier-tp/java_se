package tp.figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ligne extends Figure2D {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Ligne(int x1, int y1, int x2, int y2, String couleur, Integer epaisseur) {
        super(couleur, epaisseur, null);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Ligne(){
        this(0,0,0,0,"black",1);
    }

    @Override
    public String toSvgSubString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ps.printf("<line x1='%d' y1='%d' x2='%d' y2='%d'", x1,y1,x2,y2);
        return baos.toString();
    }


    @Override
    public String toString() {
        return "Ligne{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", couleur='" + couleur + '\'' +
                ", epaisseur=" + epaisseur +
                ", couleurFond='" + couleurFond + '\'' +
                '}';
    }

    public double longueur(){
        return Math.sqrt (Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void translater(int dx, int dy) {
        this.x1+=dx; this.x2+=dx;
        this.y1+=dy; this.y2+=dy;
    }

    @Override
    public void zoomer(double coeff) {
        this.x1*=coeff; this.x2*=coeff;
        this.y1*=coeff; this.y2*=coeff;
    }
}
