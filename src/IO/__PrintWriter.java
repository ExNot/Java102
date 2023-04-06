package IO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class __PrintWriter {
    public static void main(String[] args) {
        

        String data = "Cause i know my ways\n out";

        try {
            PrintWriter printWriter = new PrintWriter("Enes.txt");
            printWriter.print(data);
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
