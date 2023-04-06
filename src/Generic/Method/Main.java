package Generic.Method;

public class Main {

    public static void main(String[] args) {

        String[] a = {"Enes", "Baskale", "102", "Java"};
        Integer[] b = {1,2,3,4};
        Character[] c = {'J', 'A', 'V', 'A'};

        Print.printArray(a,c);
        Print.printArray(b,b);
        Print.printArray(c,a);
    }
}
