package NestedClass;

public class Local {

    public void run(){

        class Yerel{
            private int a;

            public Yerel(int a){
                this.a = a;
            }

            public int getA() {
                return a;
            }

            public void setA(int a) {
                this.a = a;
            }
        }

        Yerel y = new Yerel(7);

        System.out.println(y.getA());

    }

}
