package tp;

public class ComparateurPersonneParAge implements java.util.Comparator<Personne>{
    @Override
    public int compare(Personne o1, Personne o2) {
       return - compareCroissant(o1,o2);

    }

    public int compareCroissant(Personne o1, Personne o2) {
        //si age de type Integer dans classe Personne
        if(o1.getAge() != null)
            return o1.getAge().compareTo(o2.getAge());
        else return -1;
        //ou bien //return o1.getAge() - o2.getAge(); si age de type int dans Personne

    }
}
