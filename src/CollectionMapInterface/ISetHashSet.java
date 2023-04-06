package CollectionMapInterface;

import java.util.HashSet;
import java.util.Iterator;


//HashSetList = Boyut belirlememize gerek yok, Hash yapısını kullandığı için Sıralama yapmaz ve aynı değerler 1 tane oluşur.

public class ISetHashSet {


    public static void main (String[] args) {

        HashSet<Integer> hSet = new HashSet<>();
        hSet.add(10);
        hSet.add(20);
        hSet.add(30);
        hSet.add(null);




        Iterator<Integer> itr = hSet.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
