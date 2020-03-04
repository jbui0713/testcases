/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jjb6569
 */
public interface DAO<T> 
{
    //Optional is a container used to contain not-null objects.
    Optional<T> get(int authorID); //Gets one row of table and returns it
    List<T> getAll(); // Returns all objects of type T, returns all record of table 
    void insert(T t); // Insert object type T 
    void update(T t); // Update ojbect T
    void delete(T t); // Deletes object T
    //void flush();
    List<String> getColumnNames(); // Gets the column of names of the table 

}