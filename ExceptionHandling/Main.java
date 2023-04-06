package ExceptionHandling;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);




        try {

            int a = scan.nextInt();
            int b = 20;
            System.out.println(b/a);
        }
        catch (ArithmeticException e){
            System.out.println("Aritmatik hata");
            System.out.println(e.getMessage());
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("RUUUUUUUN");
        }


        System.out.println("PROGRAM bitti");

    }
}
