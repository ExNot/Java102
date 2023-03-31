package MultiThreadsAndConcurrency.ThreadRaceHw;

import java.util.ArrayList;
import java.util.List;

public class Controll implements Runnable {

    ArrayList<Integer> even = new ArrayList<>();
    ArrayList<Integer> odd = new ArrayList<>();

    ArrayList<Integer> nums = new ArrayList<>();

    {
        int i = 0;
        while (i < 10000) {
            nums.add(i + 1);
            i += 1;
        }
    }

    List<Integer> thread1 = new ArrayList<>(nums.subList(0, 2500));
    List<Integer> thread2 = new ArrayList<>(nums.subList(2500, 5000));
    List<Integer> thread3 = new ArrayList<>(nums.subList(5000, 7500));
    List<Integer> thread4 = new ArrayList<>(nums.subList(7500, 10000));


    @Override
    public void run() {

        if (Thread.currentThread().getName().equals("Thread-0")) {
            for (Integer number : thread1) {
                add(number);
            }
        }

        if (Thread.currentThread().getName().equals("Thread-1")) {
            for (Integer number : thread2) {
                add(number);
            }
        }

        if (Thread.currentThread().getName().equals("Thread-2")) {
            for (Integer number : thread3) {
                add(number);
            }
        }

        if (Thread.currentThread().getName().equals("Thread-3")) {
            for (Integer number : thread4) {
                add(number);
            }
        }

    }


    synchronized void add(int number) {

        if (number % 2 == 0) {
            System.out.println(Thread.currentThread().getName() + ": " + number);
            even.add(number);
            if (number == 10000){
                System.out.println("Evens -> " + even);
                System.out.println("Odds -> " + odd);
            }


        } else {
            System.out.println(Thread.currentThread().getName() + ": " + number);
            odd.add(number);

        }

    }


}
