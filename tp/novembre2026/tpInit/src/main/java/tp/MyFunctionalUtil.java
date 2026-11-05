package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class MyFunctionalUtil {


    /**
     * liste : liste d'éléments à partitionner
     * mapPredicat : table d'association entre clefs et predicats
     * en sortie : tablea d'association entres mêmes clefs et sous listes
     *             des élénents qui respectent le predicat associé à la clef
     */
    public static <T> Map<String, List<T>> partionner(List<T> liste , Map<String, Predicate<T>> mapPredicat){
        Map<String,List<T>> map = new java.util.HashMap<>();
        for(String key : mapPredicat.keySet()){
            Predicate<T> predicat = mapPredicat.get(key);
            List<T> sousListe = new ArrayList<>();
            for(T element : liste){
                if(predicat.test(element)){
                    sousListe.add(element);
                }
            }
            map.put(key,sousListe);
        }
        return map;
    }
}
