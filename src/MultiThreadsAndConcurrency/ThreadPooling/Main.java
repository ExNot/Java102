package MultiThreadsAndConcurrency.ThreadPooling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        OrderMatic o1 = new OrderMatic();


        ExecutorService pool = Executors.newFixedThreadPool(50);
        for (int i = 0; i< 100; i++){

            pool.execute(o1);

        }


        System.out.println(o1.getOrderNo());


    }

}
