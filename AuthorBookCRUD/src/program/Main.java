package program;

import entity.*;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;


public class Main {

    private static DAO authorDAO;
    private static DAO bookDAO;
    private static int table;
    private static int option;
    private static String authorID;
    private static String firstName;
    private static String lastName;
    private static String isbn;
    private static String title;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner newinfo = new Scanner(System.in);
        authorDAO = new AuthorDAO();
        Author author;
        bookDAO = new BookDAO();
        Book book;
        printBooks();
        printAuthors();
        System.out.println("Enter Author(0), Book(1), or All Options(2) for table options: ");
        table = scan.nextInt();
        switch (table) {
            case 0:
                System.out.println("Select from Options 1-4\nCreate Author(1) | Return Author(2) | Update Author(3) | Delete Author(4): ");
                break;
            case 1:
                System.out.println("Select from Options 5-8\nCreate Book(5) | Return Book(6) | Update Book(7) | Delete Book(8): ");
                break;
            case 2: 
            default:
                System.out.println("Select from Options 1-4\nCreate Author(1) | Return Author(2) | Update Author(3) | Delete Author(4)"
                        + "\nOptions 5-8\nCreate Book(5) | Return Book(6) | Update Book(7) | Delete Book(8)");
        }
            option = scan.nextInt();
            
            switch (option) {
                case 1: 
                    System.out.println("Enter new author First Name: ");
                    firstName = newinfo.nextLine();
                    System.out.println("Enter new author Last Name: ");
                    lastName = newinfo.nextLine();
                    System.out.println("Enter new author ID: ");
                    authorID = Double.toString(newinfo.nextDouble());
                    addAuthor(authorID,firstName,lastName);
                    printAuthors();
                    break;
                case 2:
                    System.out.println("Enter Author ID for information: ");
                    authorID = Double.toString(newinfo.nextDouble());
                    author = getAuthor(authorID);
                    System.out.println(author);
                    break;
                case 3:
                    System.out.println("Enter new author First Name: ");
                    firstName = newinfo.nextLine();
                    System.out.println("Enter new author Last Name: ");
                    lastName = newinfo.nextLine();
                    System.out.println("Enter new author ID: ");
                    authorID = Double.toString(newinfo.nextDouble());
                    updateAuthor(authorID,firstName,lastName);
                    printAuthors();
                    break; 
                case 4:
                    System.out.println("Works ");
                    break;
                case 5:
                    System.out.println("Enter new book Title: ");
                    title = newinfo.nextLine();
                    System.out.println("Enter new book ISBN: ");
                    isbn = Double.toString(newinfo.nextDouble());
                    System.out.println("Enter new author ID: ");
                    authorID = Double.toString(newinfo.nextDouble());
                    addBook(isbn,title,authorID);
                    printBooks();
                    break;
                case 6:
                    System.out.println("Enter Book ISBN for information: ");
                    isbn = Double.toString(newinfo.nextDouble());
                    book = getBook(isbn);
                    System.out.println(book);
                    break;
                case 7:
                    System.out.println("Enter new book Title: ");
                    title = newinfo.nextLine();
                    System.out.println("Enter new book ISBN: ");
                    isbn = Double.toString(newinfo.nextDouble());
                    System.out.println("Enter new author ID: ");
                    authorID = Double.toString(newinfo.nextDouble());
                    updateBook(isbn,title,authorID);
                    printBooks();
                    break;
                case 8:
                    break;
                case 9:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Exiting program due to invalid input");
                    break;
                    
            }
//        authorDAO = new AuthorDAO();
//        printAuthors();
//        Author author;
//        bookDAO = new BookDAO();
//        Book book;
//        System.out.println("-----------------------------------------------------------");
//        printBooks();
//        System.out.println("\n");
//        author = new Author (1, "Jefferson", "Bui");
//        authorDAO.insert(author);
//        book = new Book (988964113, "Tom & Jerry", 1);
//        bookDAO.insert(book);
//        book = new Book (132456789, "Pankcakes are Yummy", 2);
//        bookDAO.insert(book);
//        book = new Book (132466789, "Pankcakes Yum", 1);
//        bookDAO.insert(book);
//        book = new Book (132456789, "Pankcakes are Yummy", 2);
//        bookDAO.delete(book);
//        author = new Author (2, "John", "Lennon");
//        authorDAO.insert(author);
//        addAuthor(3, "Joe", "Cool");
//        addBook(123456798, "Melons & Sun", 3);
//        book = new Book (123456798, "Melons & Moon", 3);
//        bookDAO.update(book);
//        book = new Book (132466789, "Pankcakes Yum", 1);
//        bookDAO.delete(book);
//        printAuthors();
//        System.out.println("-----------------------------------------------------------");
//        printBooks();
        
    }
    
    static void addAuthor(String authorID, String firstName, String lastName) {
        Author author;
        author = new Author(authorID, firstName, lastName);
        authorDAO.insert(author);
    }
    
    static Author getAuthor(String authorID) {
        Optional<Author> author = authorDAO.get(authorID);
        return author.orElseGet(() -> new Author("Non-exist", "Non-exist", "Non-exist"));
    }
    
    
    static void updateAuthor(String authorID, String firstName, String lastName) {
        Author author;
        author = new Author (authorID, firstName, lastName);
        authorDAO.update(author);
    }
    
    static void deleteAuthor(String authorID) {
        try {
            Author author;
            author = getAuthor(authorID);
            authorDAO.delete(author);
        } catch (Exception ex){
            System.err.println(ex.toString());
            System.out.println("Cannot not delete author with exisiting books in database.");
        }
        
    }
    
    static void printAuthors() {
        List<String> headers = authorDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%17s", header);
        }
        System.out.println();
        //Print the results
        List<Author> authors = authorDAO.getAll();
        int numberRows = authors.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%17s%17s%17s", authors.get(i).getAuthorID(), authors.get(i).getFirstName(), authors.get(i).getLastName());
            System.out.println();
        }
        
    }
    static void addBook(String isbn, String title, String authorID) {
        Book book;
        book = new Book(isbn, title, authorID);
        bookDAO.insert(book);
    }
    
    static Book getBook(String isbn) {
        Optional<Book> book = bookDAO.get(isbn);
        return book.orElseGet(() -> new Book("Non-exist", "Non-exist", "Non-exist"));
    }
    
    static void updateBook(String isbn, String title, String authorID) {
        Book book;
        book = new Book(isbn, title, authorID);
        bookDAO.update(book);
    }
    
    static void deleteBook(String isbn) {
        Book book;
        book = getBook(isbn);
        bookDAO.delete(book);
    }
    
    static void printBooks() {
        List<String> headers = bookDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%17s", header);
        }
        System.out.println();
        //Print the results
        List<Book> books = bookDAO.getAll();
        int numberRows = books.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%17s%17s%17s", books.get(i).getISBN(), books.get(i).getTitle(), books.get(i).getAuthorID());
            System.out.println();
        }
        
    }
}