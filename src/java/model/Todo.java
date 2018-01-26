/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MINDIT
 */
public class Todo implements java.io.Serializable{
    
    private int id;
    private String name;
    private String owner;
    private String priority;

    public Todo() {
    }

    public Todo(String name, String owner, String priority) {
        this.name = name;
        this.owner = owner;
        this.priority = priority;
    }

    public Todo(int id, String name, String owner, String priority) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
}
