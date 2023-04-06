package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class _OutpuStreamWriter {
    public static void main(String[] args) {

        String data = "ĞŞÇÜ";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("hf.txt");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
