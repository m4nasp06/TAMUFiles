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
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getStatus() {
        return isCheckedOut;
    }

    public void checkout() throws InvalidBookStatus {
        if (isCheckedOut) {
            throw new InvalidBookStatus(bookID + " is already checked out.");
        }
        isCheckedOut = true;
    }

    public void checkin() throws InvalidBookStatus {
        if (!isCheckedOut) {
            throw new InvalidBookStatus(bookID + " is already checked in.");
        }
        isCheckedOut = false;
    }
}
