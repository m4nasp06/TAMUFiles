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

    public void addBook(String isbn, String title, String authors) {
        // loop over books to find max bookID
        int last = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID > last) {
                last = books.get(i).bookID;
            }
        }

        // add one to max (if empty, will be 0)
        last += 1;

        // Create a new book with the bookID and the given arguments. Add it to books.
        Book newBook = new Book(last, isbn, title, authors);
        books.add(newBook);

        // print book added when done
        System.out.println("Book Added");
    }

    public void addMember(String name) {
        // loop over members to find max memberID
        int ID = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).memberID > ID) {
                ID = members.get(i).memberID;
            }
        }
        // add one to max (if empty, will be 0)
        ID += 1;
        // Create a new member with the memberID and the given arguments. Add it to members.
        Member newMember = new Member(ID, name);
        members.add(newMember);
        System.out.println("Member Added");
    }

    public void findBook(String partialTitle) {
        for (int j = 0; j < books.size(); j++) {
            if (
                books
                    .get(j)
                    .getTitle()
                    .toLowerCase()
                    .contains(partialTitle.toLowerCase())
            ) {
                System.out.println(
                    "ID: " +
                        books.get(j).bookID +
                        " | Title: " +
                        books.get(j).getTitle()
                );
            }
        }
    }

    public void checkBookStatus(int bookID) {
        for (int k = 0; k < books.size(); k++) {
            if (books.get(k).bookID == bookID) {
                String status = books.get(k).getStatus()
                    ? "Checked Out"
                    : "Available";
                System.out.println(
                    "ID: " +
                        books.get(k).bookID +
                        " | Title: " +
                        books.get(k).getTitle() +
                        " | Status: " +
                        status
                );
            }
        }
    }

    public void issueBook(int bookID, int memberID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                for (int j = 0; j < members.size(); j++) {
                    if (members.get(j).memberID == memberID) {
                        members.get(j).issueBook(books.get(i));
                        books.get(i).checkout();
                        System.out.println("Book issued");
                    }
                }
            }
        }
    }

    public void returnBook(int bookID, int memberID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                for (int j = 0; j < members.size(); j++) {
                    if (members.get(j).memberID == memberID) {
                        members.get(j).returnBook(books.get(i));
                        books.get(i).checkin();
                        System.out.println("Book returned");
                    }
                }
            }
        }
    }
}
