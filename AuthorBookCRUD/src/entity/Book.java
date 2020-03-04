package entity;

import java.math.BigInteger;

/**
 *
 * @author jjb6569
 */
public class Book
{ // Class Contact created
    private int isbn; // ID of contact
    private String title; // First name of contect
    private int authorID; // Last name of contact
    
    public Book(int isbn, String title, int authorID)
    { // Constuctor created for contact
        this.isbn = isbn; // returns ID 
        this.title = title; // returns first name
        this.authorID = authorID; // returns last name
    }

    public int getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorID() {
        return authorID;
    }


    @Override
    public String toString() { // returns unique identifer of object as a string
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", authorID=" + authorID + '}';
    }
}
