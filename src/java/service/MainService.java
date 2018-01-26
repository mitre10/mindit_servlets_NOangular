/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TodoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Todo;

/**
 *
 * @author MINDIT
 */
public class MainService {
    private Connection con;
    private MainService () {
    
    }
    
    private static final class SingletonHolder {
        private static final MainService SINGLETON = new MainService();
    }
    
    public static MainService getInstance() {
        return SingletonHolder.SINGLETON;
    }
    
    public void adaugaTodo(int id, String name, String owner, String priority) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum", "forum");
            TodoDao dao = new TodoDao(con);
            Todo todo = new Todo(id, name, owner, priority);
            dao.adaugaTodo(todo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<Todo> afiseazaAll() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum", "forum");
            TodoDao dao = new TodoDao(con);
            return dao.afiseazaAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
    
    public Todo findTodoById(int id) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum", "forum");
            TodoDao dao = new TodoDao(con);
            return dao.afiseazaTodoById(id);
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public void deleteTodoById(Todo todo) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum", "forum");
            TodoDao dao = new TodoDao(con);
            if(todo != null) {
                dao.deleteTodo(todo);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void updateTodo (int id, String name, String owner, String priority) {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum", "forum");
            TodoDao dao = new TodoDao(con);
            Todo todo = new Todo();
            todo.setName(name);
            todo.setOwner(owner);
            todo.setPriority(priority);
            todo.setId(id);
            dao.updateTodo(todo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(con != null) try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
