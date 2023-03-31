package MultiThreadsAndConcurrency.CreateAndUsage;

public class ThreadCreate2 implements Runnable{

    private String name;

    public ThreadCreate2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i< 100; i++){

            System.out.println(this.name + ": " + i);

        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
