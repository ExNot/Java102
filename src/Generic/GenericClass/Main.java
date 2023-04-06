package Generic.GenericClass;

//Generic methods are a very efficient way to handle multiple datatypes using a single method.


public class Main {
    public static void main(String[] args) {
        Integer a = 12;
        String str = "abc";
        Nullable<Integer> n = new Nullable<>(a);
        Nullable<String> s = new Nullable<>(str);
        n.run();
        s.run();




        Integer x = 10;
        String y = "enes";
        String y2 = "baskale";
        double z = 3.14;
        Test<Integer, String, Double> t = new Test<>(x, y ,z);

        t.setObj2(y2);

        t.showInfo();




    }
}
