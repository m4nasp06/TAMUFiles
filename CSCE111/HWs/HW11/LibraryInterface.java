// Manas Paramathmuni UIN: 635002312
// CSCE 111 Section 503

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class LibraryInterface {

    // attributes
    private Library library;
    private JFrame frame;
    private JPanel formPanel;
    private CardLayout cardLayout;

    // constructors
    public LibraryInterface() {
        String libraryName = JOptionPane.showInputDialog(
            null,
            "Enter the library name:",
            "Library Setup",
            JOptionPane.PLAIN_MESSAGE
        );
        if (libraryName == null || libraryName.trim().isEmpty()) {
            System.exit(0);
        }
        library = new Library(libraryName.trim());
        buildUI();
    }

    // methods
    public static void main(String[] args) {
        new LibraryInterface();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(
            frame,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            frame,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private void buildUI() {
        frame = new JFrame(library.getName() + " Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500 * 3, 350 * 3);
        frame.setLayout(new BorderLayout(10, 10));

        // title label
        JLabel titleLabel = new JLabel(
            library.getName() + " Management System",
            SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 14, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // sidebar buttons
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 10));

        String[] operations = {
            "Add Book",
            "Add Member",
            "Find Book",
            "Book Status",
            "Issue Book",
            "Return Book",
        };

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            JButton button = new JButton(operation);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(180, 38));
            button.setFont(new Font("Times New Roman", Font.BOLD, 13));
            button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(formPanel, operation);
                    }
                }
            );
            sidebar.add(button);
            sidebar.add(Box.createVerticalStrut(10));
        }

        // exit button at the bottom
        sidebar.add(Box.createVerticalGlue());
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(180, 38));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
        exitButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            }
        );
        sidebar.add(exitButton);

        frame.add(sidebar, BorderLayout.WEST);

        // center form panel
        cardLayout = new CardLayout();
        formPanel = new JPanel(cardLayout);
        formPanel.setBorder(
            BorderFactory.createTitledBorder("Operation Details")
        );

        JPanel welcomePanel = new JPanel();
        welcomePanel.add(
            new JLabel("Select an operation from the left panel.")
        );
        formPanel.add(welcomePanel, "welcome");
        formPanel.add(buildAddBookPanel(), "Add Book");
        formPanel.add(buildAddMemberPanel(), "Add Member");
        formPanel.add(buildFindBookPanel(), "Find Book");
        formPanel.add(buildStatusPanel(), "Book Status");
        formPanel.add(buildIssuePanel(), "Issue Book");
        formPanel.add(buildReturnPanel(), "Return Book");

        frame.add(formPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // add book panel
    private JPanel buildAddBookPanel() {
        JTextField isbnField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JButton button = new JButton("Add Book");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("ISBN:"));
        inputPanel.add(isbnField);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author(s):"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (
                        isbnField.getText().trim().isEmpty() ||
                        titleField.getText().trim().isEmpty() ||
                        authorField.getText().trim().isEmpty()
                    ) {
                        showError("Please fill in all fields.");
                        return;
                    }
                    Book book = library.addBook(
                        isbnField.getText().trim(),
                        titleField.getText().trim(),
                        authorField.getText().trim()
                    );
                    showMessage(
                        "Book Added — ID: " +
                            book.bookID +
                            " | \"" +
                            book.getTitle() +
                            "\" by " +
                            book.getAuthor()
                    );
                    isbnField.setText("");
                    titleField.setText("");
                    authorField.setText("");
                }
            }
        );
        return panel;
    }

    // add member panel
    private JPanel buildAddMemberPanel() {
        JTextField nameField = new JTextField(20);
        JButton button = new JButton("Add Member");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("Member Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (nameField.getText().trim().isEmpty()) {
                        showError("Please enter a member name.");
                        return;
                    }
                    Member member = library.addMember(
                        nameField.getText().trim()
                    );
                    showMessage(
                        "Member Added — ID: " +
                            member.memberID +
                            " | " +
                            member.getName()
                    );
                    nameField.setText("");
                }
            }
        );
        return panel;
    }

    // find book panel
    private JPanel buildFindBookPanel() {
        JTextField searchField = new JTextField(20);
        JButton button = new JButton("Search");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("Partial Title:"));
        inputPanel.add(searchField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String partialTitle = searchField.getText().trim();
                    if (partialTitle.isEmpty()) {
                        showError("Please enter a search term.");
                        return;
                    }
                    try {
                        ArrayList<Book> results = library.findBook(
                            partialTitle
                        );
                        String message =
                            "Results for \"" + partialTitle + "\":\n";
                        for (int i = 0; i < results.size(); i++) {
                            Book book = results.get(i);
                            String status = book.getStatus()
                                ? "Not Available"
                                : "Available";
                            message +=
                                "  ID: " +
                                book.bookID +
                                " | \"" +
                                book.getTitle() +
                                "\" by " +
                                book.getAuthor() +
                                " | " +
                                status +
                                "\n";
                        }
                        showMessage(message);
                    } catch (BookNotFound ex) {
                        showError(ex.getMessage());
                    }
                }
            }
        );
        return panel;
    }

    // book status panel
    private JPanel buildStatusPanel() {
        JTextField bookIDField = new JTextField(10);
        JButton button = new JButton("Check Status");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(bookIDField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int bookID = Integer.parseInt(
                            bookIDField.getText().trim()
                        );
                        String status = library.checkBookStatus(bookID);
                        showMessage("Book ID " + bookID + ": " + status);
                    } catch (NumberFormatException ex) {
                        showError("Please enter a valid numeric Book ID.");
                    } catch (BookNotFound ex) {
                        showError(ex.getMessage());
                    }
                }
            }
        );
        return panel;
    }

    // issue book panel
    private JPanel buildIssuePanel() {
        JTextField bookIDField = new JTextField(10);
        JTextField memberIDField = new JTextField(10);
        JButton button = new JButton("Issue Book");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(bookIDField);
        inputPanel.add(new JLabel("Member ID:"));
        inputPanel.add(memberIDField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int bookID = Integer.parseInt(
                            bookIDField.getText().trim()
                        );
                        int memberID = Integer.parseInt(
                            memberIDField.getText().trim()
                        );
                        library.issueBook(bookID, memberID);
                        showMessage(
                            "Book ID " +
                                bookID +
                                " issued to Member ID " +
                                memberID
                        );
                        bookIDField.setText("");
                        memberIDField.setText("");
                    } catch (NumberFormatException ex) {
                        showError("Please enter valid numeric IDs.");
                    } catch (BookNotFound ex) {
                        showError(ex.getMessage());
                    } catch (MemberNotFound ex) {
                        showError(ex.getMessage());
                    } catch (InvalidBookStatus ex) {
                        showError(ex.getMessage());
                    }
                }
            }
        );
        return panel;
    }

    // return book panel
    private JPanel buildReturnPanel() {
        JTextField bookIDField = new JTextField(10);
        JTextField memberIDField = new JTextField(10);
        JButton button = new JButton("Return Book");

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 16, 12));
        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(bookIDField);
        inputPanel.add(new JLabel("Member ID:"));
        inputPanel.add(memberIDField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(button);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);

        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int bookID = Integer.parseInt(
                            bookIDField.getText().trim()
                        );
                        int memberID = Integer.parseInt(
                            memberIDField.getText().trim()
                        );
                        library.returnBook(bookID, memberID);
                        showMessage(
                            "Book ID " +
                                bookID +
                                " returned by Member ID " +
                                memberID
                        );
                        bookIDField.setText("");
                        memberIDField.setText("");
                    } catch (NumberFormatException ex) {
                        showError("Please enter valid numeric IDs.");
                    } catch (BookNotFound ex) {
                        showError(ex.getMessage());
                    } catch (MemberNotFound ex) {
                        showError(ex.getMessage());
                    } catch (InvalidBookStatus ex) {
                        showError(ex.getMessage());
                    }
                }
            }
        );
        return panel;
    }
}
