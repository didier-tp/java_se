package tp.basic;

import java.util.ArrayList;
import java.util.List;

/* LIFO = Last In , First Out ,
mieux que FINO = First In, Never Out*/

public class MyStack<T> {
    private List<T> listeInterne = new ArrayList<T>();
    public void push(T objVal){
        listeInterne.add(objVal);
    }
    public T pop(){
        T objVal = null;
        if(!listeInterne.isEmpty()){
            int lastIndex = listeInterne.size() - 1;
            objVal = listeInterne.remove(lastIndex);
        }
        return objVal;
    }
}
