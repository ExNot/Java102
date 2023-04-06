package IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderFileWriter {
    public static void main(String[] args) {

        /*try {
            FileReader fileReader = new FileReader("Enes.txt");

            int i = fileReader.read();
            while (i!=-1){
                System.out.print((char) i);
                i = fileReader.read();
            }
            fileReader.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/


        String data = "It is easier\nSaid than done";

        try {
            FileWriter fileWriter = new FileWriter("Enes2.txt");
            fileWriter.write(data);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
