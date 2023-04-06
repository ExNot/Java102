package IO;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class _PrintStreamClass {
    public static void main(String[] args) {

        try {
            PrintStream out = new PrintStream("priint");

            out.print("321" + 123);
            out.close();


        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }
}
