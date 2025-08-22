import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    boolean isAvailable() {
        return available;
    }

    void setAvailable(boolean available) {
        this.available = available;
    }
}

class Member {
    private String name;
    private int id;
    private ArrayList<Book> borrowedBooks;

    Member(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Run
    public static void main(String[] args) {
        Library lib = new Library();
        lib.mainMenu();
    }

    // ---------------- Books ----------------
    void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    void removeBook() {
        listBooks();
        if (books.isEmpty()) return;
        System.out.print("Enter book number to remove: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice >= 1 && choice <= books.size()) {
            books.remove(choice - 1);
            System.out.println("Book removed.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("\n--- Books ---");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() + " | Available: " + book.isAvailable());
        }
    }

    // ---------------- Members ----------------
    void addMember() {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        members.add(new Member(name, id));
        System.out.println("Member added successfully.");
    }

    void removeMember() {
        listMembers();
        if (members.isEmpty()) return;
        System.out.print("Enter member number to remove: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice >= 1 && choice <= members.size()) {
            members.remove(choice - 1);
            System.out.println("Member removed.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in library.");
            return;
        }
        System.out.println("\n--- Members ---");
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            System.out.println((i + 1) + ". " + m.getName() + " (ID: " + m.getId() + ")");
        }
    }

    // ---------------- Borrow / Return ----------------
    void borrowBook() {
        listMembers();
        if (members.isEmpty()) return;
        System.out.print("Select member number: ");
        int memberChoice = scanner.nextInt();
        scanner.nextLine();

        if (memberChoice < 1 || memberChoice > members.size()) {
            System.out.println("Invalid member.");
            return;
        }
        Member member = members.get(memberChoice - 1);

        listBooks();
        if (books.isEmpty()) return;
        System.out.print("Select book number to borrow: ");
        int bookChoice = scanner.nextInt();
        scanner.nextLine();

        if (bookChoice < 1 || bookChoice > books.size()) {
            System.out.println("Invalid book.");
            return;
        }
        Book book = books.get(bookChoice - 1);

        if (book.isAvailable()) {
            book.setAvailable(false);
            member.getBorrowedBooks().add(book);
            System.out.println(member.getName() + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Book not available.");
        }
    }

    void returnBook() {
        listMembers();
        if (members.isEmpty()) return;
        System.out.print("Select member number: ");
        int memberChoice = scanner.nextInt();
        scanner.nextLine();

        if (memberChoice < 1 || memberChoice > members.size()) {
            System.out.println("Invalid member.");
            return;
        }
        Member member = members.get(memberChoice - 1);

        ArrayList<Book> borrowed = member.getBorrowedBooks();
        if (borrowed.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        System.out.println("\n--- Borrowed Books ---");
        for (int i = 0; i < borrowed.size(); i++) {
            Book b = borrowed.get(i);
            System.out.println((i + 1) + ". " + b.getTitle() + " by " + b.getAuthor());
        }

        System.out.print("Select book number to return: ");
        int bookChoice = scanner.nextInt();
        scanner.nextLine();

        if (bookChoice < 1 || bookChoice > borrowed.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        Book book = borrowed.remove(bookChoice - 1);
        book.setAvailable(true);
        System.out.println("Returned: " + book.getTitle());
    }

    // ---------------- Menus ----------------
    void booksMenu() {
        while (true) {
            System.out.println("\n--- Books Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. List Books");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> listBooks();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    void membersMenu() {
        while (true) {
            System.out.println("\n--- Members Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. List Members");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addMember();
                case 2 -> removeMember();
                case 3 -> listMembers();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    void mainMenu() {
        while (true) {
            System.out.println("\n=== Library Main Menu ===");
            System.out.println("1. Books Menu");
            System.out.println("2. Members Menu");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> booksMenu();
                case 2 -> membersMenu();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
