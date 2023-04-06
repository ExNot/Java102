package CollectionMapInterface.BookOrder;

import java.util.Comparator;

public class Book implements Comparable<Book> {


    @Override
    public int compareTo(Book b) {
        return this.bookName.compareTo(b.bookName);
    }




    private String bookName;
    private int pageNum;
    private String author;

    public Book(String bookName, int pageNum, String author) {
        this.bookName = bookName;
        this.pageNum = pageNum;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



}
