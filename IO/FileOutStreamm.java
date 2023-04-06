package IO;

import java.io.File;
import java.io.FileOutputStream;

public class FileOutStreamm {
    public static void main(String[] args) {

        try {
            //, true koyarsak varolan dosyanın üstüne yazmaz devam eder, yazmazsak varolan dosyanın içini boşaltır ve sonra yazar
            File dosya = new File("Enes.txt");
            FileOutputStream out = new FileOutputStream(dosya, true);

            String str = "\nderkav\nhello";
            byte[] strByte = str.getBytes();
            out.write(strByte);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
