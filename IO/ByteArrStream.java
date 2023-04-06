package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrStream {
    public static void main(String[] args) {

/*
        byte[] arr = {1,2,3,4,5,6,7,8,2};

        ByteArrayInputStream inputStream = new ByteArrayInputStream(arr,2 ,4);


        System.out.println("Available byte num: " + inputStream.available());

        int i = inputStream.read();
        while (i != -1){
            System.out.println(i);
            i = inputStream.read();
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

*/
        String str = "Hello World";
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] arr = str.getBytes();

        try {
            out.write(arr);
            String newData = out.toString();
            System.out.println(newData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
