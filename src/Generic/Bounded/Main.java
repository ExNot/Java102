package Generic.Bounded;

public class Main {

    public static void main(String[] args) {


        Integer a = null;
        String str = "abc";
        Nullable<Integer> n1 = new Nullable<>(a);
        //Nullable<String> n2 = new Nullable<>(str); -> yapısını kullanamayız çünkü Nullable classında Number extend edilmiş yanı sınır verilmiş!
        n1.run();

    }

}
