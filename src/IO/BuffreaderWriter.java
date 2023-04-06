package IO;

import java.io.*;

public class BuffreaderWriter {
    public static void main(String[] args) {

        /*try {
            FileReader fileReader = new FileReader("Enes2.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            *//*String line = bufferedReader.readLine();
            while (line != null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();*//*

            String line;
            while ((line = bufferedReader.readLine()) != null){

                System.out.println(line);
                line = bufferedReader.readLine();

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/


        try {
            FileWriter fileWriter = new FileWriter("Enes.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Would i be crazy\nEnough to follow");
            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
