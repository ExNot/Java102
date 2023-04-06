package CollectionMapInterface.BookListHW;


/*
Book sınıfından 10 tane nesne üretip bunu bir ArrayList yapısında saklayınız. stream yapısını ve lambda ifadelerini kullanarak kitap isminin
karşısında yazar ismi olacak şekilde yeni bir Map<String, String> oluşturacak şekilde yazınız.



Bu 10 elemanlık Book listesinden sayfa sayısı 100'den fazla olan kitapları filtreleyen ve yeni bir liste olarak verecek geliştirmeyi yapınız
. (Stream ve Lambda ifadeleri kullanabilirsiniz.)
 */
public class Book {

    private String bookName;
    private int pageNum;
    private String author;
    private int releaseDate;

    public Book(String bookName, int pageNum, String author, int releaseDate) {
        this.bookName = bookName;
        this.pageNum = pageNum;
        this.author = author;
        this.releaseDate = releaseDate;
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

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}
