package tp.figure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rectangle extends Figure2D implements Surface {

    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(int x, int y, int width, int height, String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toSvgSubString() {
        return String.format("<rect x='%d' y='%d' width='%d' height='%d'",
                this.x,
                this.y,
                this.width,
                this.height);
    }

    public double perimetre() {
        return 2 * (this.width + this.height);
    }

    public double aire() {
        return this.width * this.height;
    }

    @Override
    public String toString() {
        return "Rectangle (x=" + x + ",y="+y  + ",width=" + width + ",height=" + height + ") sorte de " + super.toString();
    }

    @Override
    public void translater(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void zoomer(double coeff) {
        this.x = (int) (this.x * coeff);
        this.y = (int) (this.y * coeff);
        this.width = (int) (this.width * coeff);
        this.height = (int) (this.height * coeff);
    }
}
