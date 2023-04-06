package IO;

import java.io.*;

public class BufferedStreamClasss {
    public static void main(String[] args) {


        //BufferedStream'i büyük boyutlu dosyaları okumak yada yazmak için kullanırız
        //BufferedStream bize daha kolay io işlemleri gerçekleştirebilmek için bir arabellek oluşturur.




        /*try {
            FileInputStream fileInputStream = new FileInputStream("Enes.txt");

            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            int i = bufferedInputStream.read();
            while (i != -1){

                System.out.print((char) i);
                i = bufferedInputStream.read();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        String data = "And if we ever\nBreak this walls down";
        try {


            FileOutputStream fileOutputStream = new FileOutputStream("Enes.txt");

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] bytes = data.getBytes();

            bufferedOutputStream.write(bytes);

            bufferedOutputStream.close();
            fileOutputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
