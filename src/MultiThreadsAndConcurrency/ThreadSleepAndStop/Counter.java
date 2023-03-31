package MultiThreadsAndConcurrency.ThreadSleepAndStop;

public class Counter implements Runnable{

    private String name;
    private int id;
    private boolean isRun = true;

    public Counter(String name, int id) {
        this.name = name;
        this.id = id;
    }




    @Override
    public void run() {

        int i = 0;
        while (isRun){


                try {
                    Thread.sleep(1000L * this.id);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            if (isRun) {
                System.out.println(this.name + ": " + i++);
            }

        }



    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void stop(){
        this.isRun = false;
    }
}
