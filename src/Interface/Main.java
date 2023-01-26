package Interface;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Withdrow amount: ");
        double amount = scan.nextDouble();
        System.out.println("Enter card number");
        String cardNum = scan.next();
        System.out.println("Expiry date: ");
        String date = scan.next();
        System.out.println("CVC: ");
        String cvc = scan.next();

        System.out.println("1: A Bank");
        System.out.println("2: B Bank");
        System.out.println("3: C Bank");
        System.out.println("Select Bank");
        int selectBank = scan.nextInt();
        switch (selectBank){
            case 1:
                ABank aPos = new ABank("ABank", "124667457645", " 1241241245");
                aPos.connect("127.1.1.1");
                aPos.payment(amount,cardNum,date,cvc);
                break;


            case 2:
                BBank bPos = new BBank("BBank", "124667457645", " 1241241245");
                bPos.connect("127.1.1.1");
                bPos.payment(123, "123561", "1235","12456");
                break;
            default:
                System.out.println("Invalid bank!");

        }
    }
}
