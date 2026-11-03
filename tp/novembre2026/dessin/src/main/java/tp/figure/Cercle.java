package tp.figure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cercle extends Figure2D {
    private int cx;//x du centre du cercle
    private int cy;//y du centre du cercle
    private int r; //rayon

    public Cercle(int cx, int cy, int r) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    public Cercle(int cx, int cy, int r,String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }


    @Override
    public String toSvgSubString() {
        return String.format("<circle cx='%d' cy='%d' r='%d'",
                this.cx,
                this.cy,
                this.r);
    }

    public double perimetre() {
        return 2 * Math.PI * this.r;
    }

    public double aire() {
        //return this.r * this.r * Math.PI;
        return Math.PI * Math.pow(this.r, 2);
    }

    @Override
    public String toString() {
        return "Cercle (cx=" + cx + ",cy="+cy  + ",r=" + r + ") sorte de " + super.toString();
    }
}
