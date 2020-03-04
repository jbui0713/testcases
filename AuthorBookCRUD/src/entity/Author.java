package entity;

/**
 *
 * @author jjb6569
 */
public class Author
{ // Class Contact created
    private int authorID; // ID of contact
    private String firstName; // First name of contect
    private String lastName; // Last name of contact
    
    public Author(int authorID, String firstName, String lastName)
    { // Constuctor created for contact
        this.authorID = authorID; // returns ID 
        this.firstName = firstName; // returns first name
        this.lastName = lastName; // returns last name
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() { // returns unique identifer of object as a string
        return "Author{" + "authorID=" + authorID + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}
