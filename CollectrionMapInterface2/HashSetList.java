package CollectrionMapInterface2;

import java.util.HashSet;
import java.util.Iterator;

//Tekrar eden değerleri almaz ve hashcode mantığı ile çalıştığı için eklenen sıraya göre değil
//kendi sırasına göre yazdırır!

public class HashSetList {
    public static void main(String[] args) {

        HashSet<String> h = new HashSet<>();

        h.add("a");
        h.add("f");
        h.add("g");
        h.add("r");
        h.add("l");

        System.out.println(h.isEmpty());

        System.out.println(h.contains("a"));
        System.out.println(h.contains("c"));

        Iterator<String> itr = h.iterator();

        while (itr.hasNext()){
            System.out.println(itr.next());
        }



    }
}
