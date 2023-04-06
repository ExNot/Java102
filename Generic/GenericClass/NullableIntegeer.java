package Generic.GenericClass;

public class NullableIntegeer {
    private final Integer value;

    public NullableIntegeer(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
    public boolean isNull(){
        return this.getValue()==null;
    }
    public void run(){
        if (isNull())
            System.out.println("This varriable is null!");
        else
            System.out.println(this.getValue());
    }

}
