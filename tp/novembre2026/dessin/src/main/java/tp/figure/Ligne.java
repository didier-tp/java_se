package tp.figure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Ligne extends Figure2D{

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Ligne(int x1, int y1, int x2, int y2) {
        //super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Ligne(int x1, int y1, int x2, int y2, String couleur, Integer epaisseur, String couleurFond) {
        super(couleur, epaisseur, couleurFond);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Ligne(int x1, int y1, int x2, int y2, String couleur, Integer epaisseur) {
        this(x1, y1, x2, y2, couleur, epaisseur, null);
    }

    @Override
    public String toSvgSubString() {
        return String.format("<line x1='%d' y1='%d' x2='%d' y2='%d'",
                this.x1,
                this.y1,
                this.x2,
                this.y2);
    }

    @Override
    public String toString() {
        return "ligne (x1=" + x1 + ",y1="+y1  + ",x2=" + x2 + ",y2=" + y2 + ") sorte de " + super.toString();
    }
}
