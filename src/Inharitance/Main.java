package Inharitance;

public class Main {
    public static void main(String[] args) {
        Lecturer l1 = new Lecturer("İsmail hakki", "0545487965", "ih@kbu.com", "CENG", "DOC", "a13");
        LabAsisstant l2 = new LabAsisstant("Fy", "05987545213", "fy@mnu.com", "CENG", "Inharitance.Assistant", "09.00:18.00");
        Security s1 = new Security("asd", "0549875451", "asd@gmail.com", "SEC", "45", "ATY-13");
        //Academician a1 = new Academician("asd", "0549875451", "asd@gmail.com", "SEC", "45");
        l1.attendClass("10:00");
    }
}
