package tp.pers;

import java.util.Comparator;

public class ComparatorPersonneNom implements Comparator<Personne>{

    @Override
    public int compare(Personne o1, Personne o2) {
        if(o1.getNom() != null)
            return o1.getNom().compareTo(o2.getNom());
        else return -1;
    }

}
