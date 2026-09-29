package tp.figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Rectangle extends Figure2D  implements Surface {
    private int x;
    private int y;
    private int width;
    private int height;

    @Override
    public String toSvgSubString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        ps.printf("<rect x='%d' y='%d' width='%d' height='%d'",
                this.x ,
                this.y ,
                this.width ,
                this.height);
        return baos.toString();
    }

    public Rectangle( int x, int y, int width, int height,String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double perimetre(){
        return 2 * (this.width + this.height);
    }

    public double aire(){
        return this.width * this.height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                "} héritant de " + super.toString();
    }

    public String typeFig(boolean majuscule){
        if(majuscule) return "Rectangle".toUpperCase();
        else return "Rectangle";
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void translater(int dx, int dy) {
        x+=dx; y+=dy;
    }

    @Override
    public void zoomer(double coeff) {
        x*=coeff; y*=coeff; width*=coeff; height*=coeff;
    }
}
