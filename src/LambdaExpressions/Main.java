package LambdaExpressions;

public class Main {
    public static void main(String[] args) {

/*        //AnonymClass!
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World1");
            }
        };
        r1.run();*/

        /*Function interface, içerisinde sadece bir tane abstract metodu olan interface’dir. Eğer ilgili interface’in türetildiği interface’de
         abstract metot varsa bu durumda da functional interface olur. Functional interface’ler, lambda expression’ların kullanılabilmesi için
         tanımlanırlar.
         Functional interface’ler tanımlanırken, @FunctionalInterface anotasyonu kullanması zorunlu değildir. Bu anotasyon sadece validasyon yapma
         amacıyla kullanılır. Eğer anotasyon eklenirse ve birden fazla abstract metot eklenmeye çalışılırsa, bu durumda compile error verecektir.*/


        /*Runnable r2 = () -> {
            System.out.println("Hello world 2");
            System.out.println("Lambda Expression");
        };
        r2.run();*/

            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Math operations with anonym class @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



        /*Math add = new Math() {
            @Override
            public int transaction(int a, int b) {
                return a+b;
            }
        };

        Math sub = new Math() {
            @Override
            public int transaction(int a, int b) {
                return a-b;
            }
        };

        System.out.println(add.transaction(2,8));*/



        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Math operations with Lambda @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


        Math add = (a,b) -> a+b;
        Math sub = (a,b) -> a-b;
        Math multi = (a,b) -> a*b;
        Math div = (a,b) -> {
            if (b == 0)
                b=1;
            return a/b;
        };

        System.out.println(div.transaction(13,0));

    }
}
