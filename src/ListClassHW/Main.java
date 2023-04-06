package ListClassHW;

import NestedClass.Out;

public class Main {
    public static void main(String[] args) {

        GenericMethods<Integer> myList = new GenericMethods<>();
        //GenericMethods<String> myList2 = new GenericMethods<>();


        myList.add(15);
        myList.add(5);
        myList.add(15);
        myList.add(34);

        myList.add(6);
        myList.add(8564);
        myList.add(76);

        myList.add(3);
        myList.add(125);
        myList.add(15);
        myList.add(135);
        myList.add(589);



        /*GenericMethods<Integer> myarr;
        myarr = myList.subList(2,5);
        for (int i = 0; i< myarr.size(); i++){
            System.out.println(myarr.getIndex(i));
        }*/

        System.out.println(myList.contains(45));




    }
}
