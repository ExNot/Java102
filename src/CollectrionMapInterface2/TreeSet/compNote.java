package CollectrionMapInterface2.TreeSet;

import java.util.Comparator;

public class compNote implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o2.getNote()-o1.getNote();
    }
}
