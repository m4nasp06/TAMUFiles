import java.util.ArrayList;

public class Member {
    // attributes
    public final int memberID;
    private String name;
    private ArrayList<Book> books;

    // constructors
    public Member(int memberID, String name) {
        this.memberID = memberID;
        this.name = name;
    }

    // methods
    public String getName() {
        return name;
    }

    public void issueBook(Book book) {
        books.add(book);
    }

    public void returnBook(Book book) {
        books.remove(book);
    }
}
