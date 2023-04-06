package CollectrionMapInterface2.TreeSet;

import java.util.Iterator;
import java.util.TreeSet;


//Comparator sınıflar yazılır Comparator implement edilir ve compareTo methodları overload edilir
//main içinde oluşturduğumuz treeSet'in içine oluşturduğumuz comparator sınıfı yazdık(dikkat et).

public class Main {
    public static void main(String[] args) {

        TreeSet<Student> t = new TreeSet<>(new compNote());

        t.add(new Student("Enes", 98));
        t.add(new Student("Bihter", 45));
        t.add(new Student("Damla", 60));
        t.add(new Student("Damla", 60));
        t.add(new Student("Cemre", 60));

        Iterator<Student> itr = t.iterator();
        while (itr.hasNext())
            System.out.println(itr.next().getName());


    }
}
