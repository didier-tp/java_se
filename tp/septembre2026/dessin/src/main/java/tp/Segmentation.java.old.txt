package tp;

import tp.figure.Figure2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Segmentation /* V1 sans généric au sens SegmentationListeFigures */ {
    public static Map<String,  List<Figure2D>> segmenter  (List<Figure2D> globalListe){
        Map<String, List<Figure2D>> mapTypeFigListFig = new HashMap<>();
        for(Figure2D fig : globalListe){
            String className = fig.getClass().getSimpleName();
            List<Figure2D> subListe = mapTypeFigListFig.get(className);
            if(subListe==null){
                subListe=new ArrayList<>();
                mapTypeFigListFig.put(className,subListe);
            }
            subListe.add(fig);
        }
        return mapTypeFigListFig;
    }


}
