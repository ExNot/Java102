package Generic.Method;

public class Print {

    public static <T, U> void printArray(T[] arr, U[] arr2){

        for (T element: arr){
            System.out.println(element);
        }

        for (U element: arr2){
            System.out.println(element);
        }

    }

}
