public class Book {

    // attributes
    public final int bookID;
    private String isbn;
    private String title;
    private String author;
    private boolean isCheckedOut;

    // constructors
    public Book(int bookID, String isbn, String title, String author) {
        this.bookID = bookID;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    // methods
    public String getTitle() {
        return title;
    }

    public boolean getStatus() {
        return isCheckedOut;
    }

    public void checkout() {
        isCheckedOut = true;
    }

    public void checkin() {
        isCheckedOut = false;
    }
}
