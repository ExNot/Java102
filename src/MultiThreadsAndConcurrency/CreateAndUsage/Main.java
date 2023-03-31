package MultiThreadsAndConcurrency.CreateAndUsage;

public class Main {
    public static void main(String[] args) {

        /*ThreadCreate threadCreate = new ThreadCreate("Counter1");
        ThreadCreate threadCreate1 = new ThreadCreate("Counter2");

        threadCreate.start();
        threadCreate1.start();*/


        ThreadCreate2 threadCreate2 = new ThreadCreate2("Counter1");
        ThreadCreate2 threadCreate21 = new ThreadCreate2("Counter2");
        ThreadCreate2 threadCreate22 = new ThreadCreate2("Counter3");

        Thread t1 = new Thread(threadCreate2);
        Thread t2 = new Thread(threadCreate21);
        Thread t3 = new Thread(threadCreate22);

        t1.start();
        t2.start();
        t3.start();

    }
}
