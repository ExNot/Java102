package MultiThreadsAndConcurrency.CriticalSectionAndRaceCondition;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        OrderMatic o1 = new OrderMatic();
        /*Thread t1 = new Thread(o1);
        t1.start();
        t1.join();

        Thread t2 = new Thread(o1);
        t2.start();
        t2.join();*/


        List<Thread> orders = new ArrayList<>();
        for (int i = 0; i< 100; i++){

            Thread t = new Thread(o1);
            orders.add(t);
            t.start();
        }

        for (Thread t : orders){

            t.join();

        }

        System.out.println(o1.getOrderNo());


    }

}
