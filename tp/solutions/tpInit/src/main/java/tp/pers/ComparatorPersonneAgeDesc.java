package tp.pers;

import java.util.Comparator;

public class ComparatorPersonneAgeDesc implements Comparator<Personne>{

    @Override
    public int compare(Personne o1, Personne o2) {
        return o2.getAge() - o1.getAge();    //par ordre décroissant
        //return o1.getAge() - o2.getAge();  //par ordre croissant
    }

}