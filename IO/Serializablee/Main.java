package IO.Serializablee;

import java.io.*;

public class Main {
    public static void main(String[] args) {


        Car c1 = new Car("Audi","A3");

        /*try {

            File direc = new File("Java102/src/IO/Serializablee/car.txt");
            direc.createNewFile();
            FileOutputStream file = new FileOutputStream(direc);

            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(c1);
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        try {

            FileInputStream fileInputStream = new FileInputStream("Java102/src/IO/Serializablee/car.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            Car newCar = (Car) inputStream.readObject();

            System.out.println(newCar.getBrand());
            System.out.println(newCar.getModel());
            inputStream.close();
            fileInputStream.close();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
