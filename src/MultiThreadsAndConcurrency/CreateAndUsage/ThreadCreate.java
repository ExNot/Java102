package MultiThreadsAndConcurrency.CreateAndUsage;

public class ThreadCreate extends Thread {

    public String counterName;

    public ThreadCreate(String counter) {
        this.counterName = counter;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            System.out.println(this.counterName + " : " + i);

        }

    }
}
