package StreamApi;

import NestedClass.Out;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class main {
    public static void main(String[] args) {

        List<Integer> myList = new ArrayList<>();

        myList.add(10);
        myList.add(20);
        myList.add(20);
        myList.add(25);
        myList.add(15);
        myList.add(11);
        myList.add(43);
        myList.add(74);
        myList.add(55);
        myList.add(47);

        long c =myList.stream().count();
        boolean match = myList.stream().anyMatch(i-> i == 11);
        System.out.println(match);

        myList.stream().map(i-> Math.sqrt(i)).forEach(System.out::println);

    }
}
