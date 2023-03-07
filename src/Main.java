package CollectionMapInterface.BookOrder;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        TreeSet<Book> books = new TreeSet<>();
        books.add(new Book("Masumiyet Müzesi", 453, "Orhan Pamuk"));
        books.add(new Book("Orman" , 538, "Harlan Coben"));
        books.add(new Book("Deliliğe Övgü" , 284, "Desiderius Erasmus"));
        books.add(new Book("Felsefenin Tesellisi", 200 , "Boethius"));
        books.add(new Book("The Great Gatsby", 180 ,"F. Scott Fitzgerald"));


        for (Book elem: books){
            System.out.println(elem.getBookName());
        }

        Set<Book> myBookList = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPageNum()-o2.getPageNum();
            }
        }.reversed());

        myBookList.addAll(books);

        System.out.println();

        for (Book elem: myBookList){
            System.out.println(elem.getPageNum());
        }
    }






}
