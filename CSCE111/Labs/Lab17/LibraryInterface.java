import java.util.Scanner;


public class LibraryInterface {

    /**
     * This will contain your main function to interact with the user. The interface should work as follows.

At the beginning of your main function ask the user for the library name.
Create a Library object with the provided name.
Print a welcome message. Example shown in full example.
Present user with a menu shown below:
Select your option:
1 - Add a new Book.
2 - Add a new Member.
3 - Find book by title.
4 - Check book status by bookID.
5 - Check out a book to member.
6 - Check in a book.
7 - Exit
Enter your selection (1-7): 
This menu should be repeated after every operation. Remember, you can copy the prompts from the given examples.
Validate the user input. It should be a number between 1-7. If it is out of range then print the message "Invalid Selection" and repeat the menu.
For each selection, ask the user for further information needed for the operation and invoke the appropriate library methods.
For simplicity, we will not test cases with invalid input for the operations.
     * 
     * 
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the library name: ");
        String libraryName = scnr.nextLine();
        Library library = new Library(libraryName);
        System.out.println();
        System.out.println("Welcome to the " + libraryName + " Management System!");
        System.out.println();

        boolean exit = false;
        while (!exit) {
            System.out.println("Select your option:");
            System.out.println("1 - Add a new Book.");
            System.out.println("2 - Add a new Member.");
            System.out.println("3 - Find book by title.");
            System.out.println("4 - Check book status by bookID.");
            System.out.println("5 - Check out a book to member.");
            System.out.println("6 - Check in a book.");
            System.out.println("7 - Exit.");
            System.out.println("Enter your selection (1-7): ");
            int selection = scnr.nextInt();
            
            switch (selection) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scnr.nextLine(); // Consume the newline
                    System.out.println();
                    System.out.print("Enter Title: ");
                    String title = scnr.nextLine();
                    System.out.println();
                    System.out.print("Enter Author(s): ");
                    String authors = scnr.nextLine();
                    library.addBook(isbn, title, authors);
                    break;
                case 2:
                    System.out.print("Enter Member Name: ");
                    String name = scnr.nextLine();
                    library.addMember(name);
                    break;
                case 3:
                    System.out.print("Enter Title: ");
                    String searchTitle = scnr.nextLine();
                    library.findBook(searchTitle);
                    break;
                case 4: 
                    System.out.print("Enter Book ID: ");
                    int bookID = scnr.nextInt();
                    library.checkBookStatus(bookID);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int checkoutBookID = scnr.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memberID = scnr.nextInt();
                    library.issueBook(checkoutBookID, memberID);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    int checkinBookID = scnr.nextInt();
                    System.out.println();
                    System.out.print("Enter Member ID: ");
                    int checkinMemberID = scnr.nextInt();
                    library.returnBook(checkinBookID , checkinMemberID);
                    break;
                case 7:
                    System.out.print("Exiting the system. Goodbye!");
            }

        }
        
        
    }
    
}