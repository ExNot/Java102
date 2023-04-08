package xCourse.Helper;

public class Item {
    private int key;
    private String val;

    public Item(int key, String val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String toString(){
        return this.val;
    }
}
