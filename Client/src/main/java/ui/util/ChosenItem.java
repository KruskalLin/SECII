package ui.util;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ChosenItem<T> {
    private HashSet<T> chosenitems=new HashSet();
    public void addItem(T t){
        chosenitems.add(t);
    }
    public void removeItem(T t){
        chosenitems.remove(t);
    }
    public Set<T> getSet(){return chosenitems;}
}
