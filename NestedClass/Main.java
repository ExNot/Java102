package NestedClass;

public class Main {
    public static void main(String[] args) {
/*
        Out.In.run();

        Local l = new Local();
        l.run();*/

        Anonym a = new Anonym(){

            @Override
            public void run(){
                System.out.println(this.a);
                 System.out.println("Anonym run override method");
                 print();
            }
             public void print(){
                 System.out.println("Print Method");
             }
        };
        a.run();
        System.out.println(7%2);
    }
}
