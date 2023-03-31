package MultiThreadsAndConcurrency.ThreadRaceHw;

public class Main {


    public static void main(String[] args) {

        Controll controll = new Controll();


        Thread t1 = new Thread(controll);
        Thread t2 = new Thread(controll);
        Thread t3 = new Thread(controll);
        Thread t4 = new Thread(controll);

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
