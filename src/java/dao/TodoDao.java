/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Todo;

/**
 *
 * @author MINDIT
 */
public class TodoDao {
    
    private Connection con;
    
    public TodoDao (Connection con) {
        this.con = con;
    }
    
    public void adaugaTodo(Todo todo) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("INSERT INTO todo VALUES(?, ?, ?, ?)");
            stm.setInt(1, todo.getId());
            stm.setString(2, todo.getName());
            stm.setString(3, todo.getOwner());
            stm.setString(4, todo.getPriority());
            stm.executeUpdate();
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
    
    public void deleteTodo(Todo todo) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("DELETE FROM todo WHERE id = ?");
            stm.setInt(1, todo.getId());
            stm.executeUpdate();
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
    
    public Todo afiseazaTodoById(int id) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement("SELECT * FROM todo WHERE id = ?");
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getInt("id"));
                todo.setName(rs.getString("name"));
                todo.setOwner(rs.getString("owner"));
                todo.setPriority(rs.getString("priority"));
                return todo;
            }
            
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return null;
    }
    
    public List<Todo> afiseazaAll() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement("SELECT * FROM todo");
            rs = stm.executeQuery();
            while (rs.next()) {
                Todo todo = new Todo();
                todo.setId(rs.getInt("id"));
                todo.setName(rs.getString("name"));
                todo.setOwner(rs.getString("owner"));
                todo.setPriority(rs.getString("priority"));
                todos.add(todo);
            }
            
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return todos;
    }
    
    public void updateTodo(Todo todo) throws SQLException {
        PreparedStatement stm = null;
        try {
            stm = con.prepareStatement("UPDATE todo SET name = ?, owner = ?, priority = ? WHERE id = ?");
            stm.setString(1, todo.getName());
            stm.setString(2, todo.getOwner());
            stm.setString(3, todo.getPriority());
            stm.setInt(4, todo.getId());
            stm.executeUpdate();
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
}
