import java.util.ArrayList;

public class Library {
    // attributes
    private String libraryName;
    private ArrayList<Member> members;
    private ArrayList<Book> books;

    // constructors
    public Library(String name) {
        this.libraryName = name;
    }

    // methods
    public String getName() {
        return libraryName;
    }

    public void addBook(String isbn, String title, String authors) {
        // loop over books to find max bookID
        int last = 0;
        for (int i = 0; i < books.size(); i++) {
            last = i;
        }

        // add one to max (if empty, will be 0)
        last += 1;

        // Create a new book with the bookID and the given arguments. Add it to books.
        Book newBook = new Book(last, isbn, title, authors);
        books.add(newBook);
        
        // print book added when done
        System.out.print("Book Added");
    }

    /**
     * Loop over the members to find the maximum memberID. Add 1 to maximum to get memberID for the new member. If there are no members then first memberID should be 1.
        Create a new member with the memberID and the given arguments. Add it to members.
        Print "Member Added" when done.
     * 
     */
    public void addMember(String name) {
        
        // loop over members to find max memberID
        int ID = 0;
        for (int i = 0; i < members.size(); i++) {
            ID = i;
        }
        // add one to max (if empty, will be 0)
        ID += 1;
        // Create a new member with the memberID and the given arguments. Add it to members.
        Member newMember = new Member(ID, name);
        members.add(newMember);
        System.out.print("Member Added");

        
    }

    // Loop over the books to find which contain the partialTiltle in the book title. 
    // The check must be case insensitive. 
    // Print all partialTitle matches. 
    // Print both bookID and complete Title for each match.

    public void findBook(String partialTitle) {
        for (int j = 0; j < books.size(); j++) {
            if (books.get(j).getTitle().toLowerCase().equals(partialTitle)) {
                System.out.print(books.get(j).bookID + " " + books.get(j).getTitle());
            }
        }
    }

    // Loop over the books to find by bookID. Print the status of the book.
    public void checkBookStatus(int bookID) {
        for (int k = 0; k < books.size(); k++) {
            if (books.get(k).bookID == bookID) {
                System.out.print(books.get(k).getStatus());
            }
        }
    }

    // Loop over the books to find the Book object by bookID. 
    // Loop over the members to find the Member object by memberID. 
    // Issue the book to the member. Mark the book as checked out.
    // Print "Book issued"
    public void issueBook(int bookID, int memberID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                for (int j = 0; j <members.size(); j++) {
                    if (members.get(j).memberID == memberID) {
                        books.get(i).checkOut();
                        System.out.print("Book issued");
                    }
                }
            }
        }
    }


    /**
     * Loop over the books to find the Book object by bookID. Loop over the members to find the Member object by memberID. member returns the Book. Mark the book checked in.
        Print "Book returned"
     * 
     */
    public void returnBook(int bookID, int memberID) {
        
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).bookID == bookID) {
                for (int j = 0; j < members.size(); j++) {
                    if (members.get(j).memberID == memberID) {
                        members.get(j).returnBook(books.get(i));
                        books.get(i).checkIn();
                        System.out.print("Book returned");
                    }
                }
            }
        }
    }
}
