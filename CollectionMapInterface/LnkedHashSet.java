package CollectionMapInterface;

import java.util.Iterator;
import java.util.LinkedHashSet;

//HashSete kıyasen giren verilen; girilen sıraya göre çıkar
public class LnkedHashSet {
    public static void main(String[] args) {

        LinkedHashSet<Integer> lSet = new LinkedHashSet<>();
        lSet.add(20);
        lSet.add(10);
        lSet.add(10);
        lSet.add(30);

        Iterator<Integer> itr = lSet.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
