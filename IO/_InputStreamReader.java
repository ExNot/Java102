package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class _InputStreamReader {
    public static void main(String[] args) {

        try {
            FileInputStream fileInputStream = new FileInputStream("priint");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            /*InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8"); şeklinde istediğimiz charseti atayabiliriz*/

            System.out.println(inputStreamReader.getEncoding());

            int i = inputStreamReader.read();
            while (i != -1 ){

                System.out.print((char)  i);
                i = inputStreamReader.read();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
