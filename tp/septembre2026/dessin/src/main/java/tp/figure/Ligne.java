package tp.figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ligne extends Figure2D {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Ligne( int x1, int y1, int x2, int y2,String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toSvgSubString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ps.printf("<line x1='%d' y1='%d' x2='%d' y2='%d'",
                this.x1 ,
                this.y1 ,
                this.x2,
                this.y2);
        return baos.toString();
    }

    @Override
    public String toString() {
        return "Ligne{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                "} hériatnt de " + super.toString();
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
}
