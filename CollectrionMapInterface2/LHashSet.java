package CollectrionMapInterface2;

import java.util.Iterator;
import java.util.LinkedHashSet;

//Tekrar eden değerleri almaz ve hashcode mantığı ile çalışmasına rağmen Linked yapısı olduğu için
//listeye eklediğimizi sıra ile çıktı alırız!

public class LHashSet {
    public static void main(String[] args) {

        LinkedHashSet<String> l = new LinkedHashSet<>();

        l.add("a");
        l.add("r");
        l.add("a");
        l.add("b");
        l.add("a");
        l.add("m");

        l.remove("r");
        Iterator<String> itr = l.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }




    }
}
