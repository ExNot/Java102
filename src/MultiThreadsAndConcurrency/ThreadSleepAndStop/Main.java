package MultiThreadsAndConcurrency.ThreadSleepAndStop;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter c1 = new Counter("C1", 1);
        Thread t1 = new Thread(c1);
        t1.start();
        Thread.sleep(2000);
        c1.stop();


        Counter c2 = new Counter("C2", 2);
        Thread t2 = new Thread(c2);
        t2.start();
        c2.stop();



    }

}
