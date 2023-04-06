package ListClassHW;


public class GenericMethods<T> {
    private int capacity;
    private T[] myList;
    private T[] tempList;

    private int index = -1;

    //DEFAULT constructor
    public GenericMethods() {

        this.capacity = 10;
        this.myList = (T[]) new Object[this.capacity];

    }

    //Decied capacity!
    public GenericMethods(int capacity) {
        this.capacity = capacity;
        this.myList = (T[]) new Object[this.capacity];
    }

    public int size() {
        int size = 0;
        for (T elem : myList) {
            if (elem != null)
                size++;
        }
        return size;
    }

    public int getCapacity() {
        return this.capacity;
    }


    public void add(T data) {
        if (index + 1 == this.capacity) {
            this.capacity = this.capacity * 2;
            this.tempList = (T[]) new Object[this.capacity];
            for (int i = 0; i < myList.length; i++) {
                tempList[i] = myList[i];
            }
            index++;
            tempList[index] = data;
            this.myList = tempList;
        } else {
            index++;
            myList[index] = data;

        }
    }

    public T getIndex(int index) {
        if (index > this.capacity) {
            System.out.println("Invalid index! Your list has not contains " + index);
            return null;
        } else {
            return myList[index];
        }

    }

    public void remove(int index) {

        if (index > this.capacity || index < 0) {
            System.out.println("Invalid index!");
        } else {

            for (int i = index; i < this.capacity - 1; i++) {

                myList[i] = myList[i + 1];
            }
            myList[capacity - 1] = null;
        }

    }

    public void set(int index, T data) {

        if (index > this.capacity || index < 0) {
            System.out.println("Invalid index!");
        } else {
            myList[index] = data;
        }

    }

    @Override
    public String toString() {

        String list = "[";

        for(int i = 0; i<= this.capacity-1; i++){
            if (myList[i] != null)
                list += myList[i] + ",";

        }

        if (list.length() != 1)
            list = list.substring(0,list.length()-1);

        list += "]";
        return list;
    }

    public int indexOf(T data){
        int counter =0;
        boolean exist = false;

        for (T elem: myList){
            if (elem == data){

                exist = true;
                break;
            }
            counter++;
        }

        if (exist)
            return counter;
        else
            return -1;
    }

    public int lastIndexOf(T data){

        int counter = 0;
        int tempCounter =0;

        boolean exist = false;

        for (T elem: myList){
            if (elem == data){
                exist = true;
                if (tempCounter>counter)
                    counter = tempCounter;
            }
            tempCounter++;
        }
        return counter;
    }

    public boolean isEmpty(){

        boolean flag = true;
        for (T elem: myList){
            if (elem != null)
                flag = false;
        }
        return flag;
    }


    public T[] toArray(){

        T[] myListsArray = (T[]) new Object[size()];
        for (int i = 0; i<size(); i++){
            myListsArray[i] = getIndex(i);
        }
        return myListsArray;
    }


    public void clear(){
        this.capacity = 10;
        this.myList = (T[]) new Object[this.capacity];
        /*for (int i = 0; i<size(); i++){
            this.myList[i] = null;
        }*/
    }

    public GenericMethods<T> subList(int ofIndex, int ToIndex){
        GenericMethods<T> sublist = new GenericMethods<>(ToIndex-ofIndex);
        int index =0;
        for (int i = ofIndex; i<=ToIndex; i++){
            sublist.add(myList[i]);
        }
        return sublist;
    }

    public boolean contains(T data){
        boolean flag = false;
        for (T elem: myList){
            if (elem == data)
                flag = true;
        }
        return flag;
    }


}
























