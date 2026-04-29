import java.util.ArrayList;

public class Library {

    // attributes
    private String libraryName;
    private ArrayList<Member> members;
    private ArrayList<Book> books;

    // constructors
    public Library(String name) {
        this.libraryName = name;
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    // methods
    public String getName() {
        return libraryName;
    }

    // Returns the newly created book
    public Book addBook(String isbn, String title, String authors) {
        int last = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID > last) {
                last = books.get(i).bookID;
            }
        }
        last += 1;
        Book newBook = new Book(last, isbn, title, authors);
        books.add(newBook);
        return newBook;
    }

    // Returns the newly created member
    public Member addMember(String name) {
        int ID = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).memberID > ID) {
                ID = members.get(i).memberID;
            }
        }
        ID += 1;
        Member newMember = new Member(ID, name);
        members.add(newMember);
        return newMember;
    }

    // Returns list of matching books; throws BookNotFound if none match
    public ArrayList<Book> findBook(String partialTitle) throws BookNotFound {
        ArrayList<Book> results = new ArrayList<>();
        for (int j = 0; j < books.size(); j++) {
            if (books.get(j).getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
                results.add(books.get(j));
            }
        }
        if (results.isEmpty()) {
            throw new BookNotFound("No book found for search term " + partialTitle + ".");
        }
        return results;
    }

    // Returns "Available" or "Not Available"; throws BookNotFound if book does not exist
    public String checkBookStatus(int bookID) throws BookNotFound {
        for (int k = 0; k < books.size(); k++) {
            if (books.get(k).bookID == bookID) {
                return books.get(k).getStatus() ? "Not Available" : "Available";
            }
        }
        throw new BookNotFound(bookID + " not found");
    }

    // Throws BookNotFound, MemberNotFound, or InvalidBookStatus on failure
    public void issueBook(int bookID, int memberID) throws BookNotFound, MemberNotFound, InvalidBookStatus {
        Book targetBook = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                targetBook = books.get(i);
                break;
            }
        }
        if (targetBook == null) {
            throw new BookNotFound(bookID + " not found");
        }

        Member targetMember = null;
        for (int j = 0; j < members.size(); j++) {
            if (members.get(j).memberID == memberID) {
                targetMember = members.get(j);
                break;
            }
        }
        if (targetMember == null) {
            throw new MemberNotFound(memberID + " not found");
        }

        targetBook.checkout(); // throws InvalidBookStatus if already checked out
        targetMember.issueBook(targetBook);
    }

    // Throws BookNotFound, MemberNotFound, or InvalidBookStatus on failure
    public void returnBook(int bookID, int memberID) throws BookNotFound, MemberNotFound, InvalidBookStatus {
        Book targetBook = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                targetBook = books.get(i);
                break;
            }
        }
        if (targetBook == null) {
            throw new BookNotFound(bookID + " not found");
        }

        Member targetMember = null;
        for (int j = 0; j < members.size(); j++) {
            if (members.get(j).memberID == memberID) {
                targetMember = members.get(j);
                break;
            }
        }
        if (targetMember == null) {
            throw new MemberNotFound(memberID + " not found");
        }

        targetBook.checkin(); // throws InvalidBookStatus if already checked in
        targetMember.returnBook(targetBook);
    }
}
