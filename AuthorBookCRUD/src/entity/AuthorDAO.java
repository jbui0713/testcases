/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author jjb6569
 */
public class AuthorDAO implements DAO<Author>
{   
    public AuthorDAO() {
        // Constuctor created named AuthorDAO
    }
    List<Author> authors; // Author table 
    /**
     * Get a single author entity as a author object
     * @param authorID
     * @return 
     */
    @Override
    public Optional<Author> get(int authorID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Author WHERE authorID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, authorID);
            rs = stmt.executeQuery();
            Author author = null;
            while (rs.next()) {
                author = new Author(rs.getInt("authorID"), rs.getString("firstName"), rs.getString("lastname"));
            }
            return Optional.ofNullable(author);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all author entities as a List
     * @return 
     */
    @Override
    public List<Author> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        authors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Author ORDER BY authorID";
            rs = db.executeQuery(sql);
            Author author = null;
            while (rs.next()) {
                author = new Author(rs.getInt("authorID"), rs.getString("firstname"), rs.getString("lastname"));
                authors.add(author);
            }
            return authors;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a author object into author table
     * @param author 
     */
    @Override
    public void insert(Author author)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Author(authorID, firstName, lastName) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, author.getAuthorID());
            stmt.setString(2, author.getFirstName());
            stmt.setString(3, author.getLastName());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new author was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a author entity in database if it exists using a author object
     * @param author
     */
    @Override
    public void update(Author author) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Author SET firstName = ?, lastName = ? WHERE authorID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setInt(3, author.getAuthorID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing author was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a author from author table if the entity exists
     * @param author 
     */
    @Override
    public void delete(Author author) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Author WHERE authorID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, author.getAuthorID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A author was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Author WHERE authorID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
//    @Override
//    public void flush() {
//        DB db = DB.getInstance();
//        ResultSet rs = null;
//        authors = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM Author";
//            rs = db.executeQuery(sql);
//            Author Author = null;
//            while (rs.next()) {
//                contact = new Contact(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("phonenumber"));
//                contacts.delete(contact);
//            }
//            return contacts;
//        } catch (SQLException ex) {
//            System.err.println(ex.toString());
//            return null;
//        }
//    }

//    @Override
//    public Optional<Author> get(int ISBN) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}