import java.util.Scanner;

public class LibraryInterface {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter the library name: ");
        String libraryName = scnr.nextLine();
        Library library = new Library(libraryName);
        System.out.println();
        System.out.println(
            "Welcome to the " + libraryName + " Management System!"
        );

        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Select your option:");
            System.out.println("1 - Add a new Book.");
            System.out.println("2 - Add a new Member.");
            System.out.println("3 - Find book by title.");
            System.out.println("4 - Check book status by bookID.");
            System.out.println("5 - Check out a book to member.");
            System.out.println("6 - Check in a book.");
            System.out.println("7 - Exit.");
            System.out.print("Enter your selection (1-7): ");
            String input = scnr.nextLine();
            int selection = 0;
            if (
                input.length() == 1 &&
                input.charAt(0) >= '1' &&
                input.charAt(0) <= '7'
            ) {
                selection = input.charAt(0) - '0';
            }

            switch (selection) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scnr.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scnr.nextLine();
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
                    System.out.print("Enter partial title to search: ");
                    String searchTitle = scnr.nextLine();
                    library.findBook(searchTitle);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    int bookID = Integer.parseInt(scnr.nextLine());
                    library.checkBookStatus(bookID);
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int checkoutBookID = Integer.parseInt(scnr.nextLine());
                    System.out.print("Enter Member ID: ");
                    int memberID = Integer.parseInt(scnr.nextLine());
                    library.issueBook(checkoutBookID, memberID);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    int checkinBookID = Integer.parseInt(scnr.nextLine());
                    System.out.print("Enter Member ID: ");
                    int checkinMemberID = Integer.parseInt(scnr.nextLine());
                    library.returnBook(checkinBookID, checkinMemberID);
                    break;
                case 7:
                    System.out.print("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }
}
