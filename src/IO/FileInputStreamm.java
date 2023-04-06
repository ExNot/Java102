package IO;

public class FileInputStreamm {
    public static void main(String[] args) {

/*
        File dosya = new File("Pat.txt");

        try {
            dosya.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dosya.delete();

        File direc = new File("Enes");
        direc.mkdir();

        File non = new File("asd/Er");
        non.mkdirs();*/


        try {

            java.io.FileInputStream inputStream = new java.io.FileInputStream("Enes.txt");
            inputStream.skip(10);
            int i = inputStream.read();
            while (i != -1){

                System.out.print((char) i);
                i = inputStream.read();
                //System.out.println(inputStream.available());

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
