package CollectionMapInterface;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class _LinkedList {
    public static void main(String[] args) {
        List<String> liste =new LinkedList<>();
        liste.add("Enes");
        liste.add("Enes");
        liste.add("Java");
        liste.add("102");
        liste.add("Hey");

        liste.remove(1);
        Iterator<String> itr = liste.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
