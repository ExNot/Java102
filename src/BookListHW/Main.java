package BookListHW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("To Kill a Mockingbird", 320, "Harper Lee", 1960);
        Book b2 = new Book("I Capture the Castle", 544, "Dodie Smith", 2016);
        Book b3 = new Book("To the Lighthouse", 208, "Virginia Woolf, Hermione Lee", 2018);
        Book b4 = new Book("The Death Of The Heart", 368, "Elizabeth Bowen", 2012);
        Book b5 = new Book("Frankenstein", 288, "Mary Shelley", 2012);
        Book b6 = new Book("Great Expectations", 544, "Charles Dickens, Charlotte Mitchell, David Trotter", 2003);
        Book b7 = new Book("Another Country", 448, "James Baldwin, Colm Tóibín", 2001);
        Book b8 = new Book("The Outsiders", 160, "S. E. Hinton", 1967);
        Book b9 = new Book("Ulysses", 688, "James Joyce, Hans Walter Gabler, Anne Enright", 2022);
        Book b10 = new Book("The Castle", 320, "Franz Kafka, Idris Parry ", 2015);
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);
        bookList.add(b7);
        bookList.add(b8);
        bookList.add(b9);
        bookList.add(b10);

        Map<String, String> mapList = new HashMap<>();


        bookList.stream().forEach(b ->{

            mapList.put(b.getBookName(), b.getAuthor());

        });


        /*for (String val: mapList.values()){
            System.out.println(val);
        }*/
        mapList.entrySet().stream()
                .forEach(b -> System.out.println("Author: " + b.getValue() + "Book: " + b.getKey() + "\n" + "------------"));

        ArrayList<Book> longNovel = new ArrayList<>();

        bookList.stream().filter(b-> b.getPageNum()>350).forEach(a-> longNovel.add(a));

        for (int i = 0; i < longNovel.size(); i++)
            System.out.println(longNovel.get(i).getBookName());


    }
}
