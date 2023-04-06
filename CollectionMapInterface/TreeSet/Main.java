package CollectionMapInterface.TreeSet;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Student> students = new TreeSet<>(new OrderByNote());

        students.add(new Student("Enes", 98));
        students.add(new Student("Ahmet", 27));
        students.add(new Student("Bihter", 45));
        students.add(new Student("Damla", 60));
        students.add(new Student("Damla", 60));
        students.add(new Student("Cemre", 60));

        for (Student stu: students){
            System.out.println(stu.getName());
        }

    }
}
