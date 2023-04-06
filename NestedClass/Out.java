package NestedClass;
//Outer Class
public class Out {

    public int a = 5;

    //Inner class
    public static class In{
        public int a = 10;

        public static void run(){
            System.out.println("In class!");
            int a = 1;
            System.out.println(a);

        }
    }

    public void run(){

        In.run();

    }


}
