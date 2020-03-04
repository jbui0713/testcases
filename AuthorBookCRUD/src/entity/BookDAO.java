/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import core.DB;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jjb6569
 */
public class BookDAO implements DAO<Book>{
    public BookDAO() {
        // Constuctor created named BookDAO
    }
    List<Book> books; // Book table 
    /**
     * Get a single book entity as a book object
     * @param isbn
     * @return 
     */
    @Override
    public Optional<Book> get(int isbn) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Book WHERE isbn = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, isbn);
            rs = stmt.executeQuery();
            Book book = null;
            while (rs.next()) {
                book = new Book(rs.getInt("isbn"), rs.getString("title"), rs.getInt("authorID"));
            }
            return Optional.ofNullable(book);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all book entities as a List
     * @return 
     */
    @Override
    public List<Book> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        books = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Book";
            rs = db.executeQuery(sql);
            Book book = null;
            while (rs.next()) {
                book = new Book(rs.getInt("isbn"), rs.getString("title"), rs.getInt("authorID"));
                books.add(book);
            }
            return books;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a book object into book table
     * @param book 
     */
    @Override
    public void insert(Book book)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Book(isbn, title, authorID) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, book.getISBN());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getAuthorID());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a book entity in database if it exists using a book object
     * @param book
     */
    @Override
    public void update(Book book) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Book SET title = ?, authorID = ? WHERE isbn = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorID());
            stmt.setInt(3, book.getISBN());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing book was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a book from book table if the entity exists
     * @param book 
     */
    @Override
    public void delete(Book book) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Book WHERE isbn = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, book.getISBN());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A book was deleted successfully!");
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
            String sql = "SELECT * FROM Book WHERE isbn = -1";//We just need this sql query to get the column headers
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
//    public Optional<Book> get(int authorID) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
