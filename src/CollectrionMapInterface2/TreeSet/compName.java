package CollectrionMapInterface2.TreeSet;

import java.util.Comparator;

public class compName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
