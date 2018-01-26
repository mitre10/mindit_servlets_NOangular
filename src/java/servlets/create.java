/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Todo;
import service.MainService;

/**
 *
 * @author MINDIT
 */
@WebServlet(name = "create", urlPatterns = {"/create"})
public class create extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = null;
        try {
           out = resp.getWriter();
           
           String type = req.getParameter("type");
           if (type.equals("create")){
               createTodoView(out);
           } else {
               editTodoView(out, req, resp);
           }
           
        } finally {
            if (out != null) out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
         PrintWriter out = null;
        try {
           out = resp.getWriter();
           
           int id = Integer.parseInt(req.getParameter("id"));
           Todo t = MainService.getInstance().findTodoById(id);
            if (t == null){
                createTodo(req, resp);
           } else {
                editTodo(req, resp);
           }
        } finally {
            if (out != null) out.close();
        }
    }
    
    private void createTodoView(PrintWriter out) {
        out.println("<center>");
        out.println("<h1>Create new Todo</h1>");
        out.println("<p>Your Options: <a href='view'>Back</a>.</p>");
        out.println("<form action='create' method='post' >");
        out.println("<table>");
            out.println("<tr><td>Todo id</td><td> <input type='text' name='id' /></td></tr>");
            out.println("<tr><td>Todo name</td><td> <input type='text' name='name' /></td></tr>");
            out.println("<tr><td>Todo owner</td><td> <input type='text' name='owner' /></td></tr>");
            out.println("<tr><td>Todo priority</td><td> <input type='text' name='priority' /></td></tr>");  
        out.println("</table>");
        out.println(" <input type='submit' value='Add' />");
        out.println("</form>");
        out.println("</center>");
    }
    
    private void editTodoView(PrintWriter out,HttpServletRequest req, HttpServletResponse resp ) {
        out.println("<center>");
        out.println("<h1>Edit Todo</h1>");
        out.println("<p>Your Options: <a href='view'>Back</a>.</p>");
        int id = Integer.parseInt(req.getParameter("id"));
        Todo t = MainService.getInstance().findTodoById(id);
        out.println("<form action='create' method='post' >");
        out.println("<p> <input type='hidden' name='id' value='"+t.getId()+"' /></p>");
        out.println("<table>");
        out.println("<tr><td>new Todo name</td><td> <input type='text' name='name' value='"+t.getName()+"' /></td></tr>");
        out.println("<tr><td> new Todo owner</td><td> <input type='text' name='owner' value='"+t.getOwner()+"'/></td></tr>");
        out.println("<tr><td>new Todo priority</td><td> <input type='text' name='priority' value ='"+t.getPriority()+"' /></td></tr>");
        out.println("</table>");
        out.println(" <input type='submit' value='Save & Exit' />");
        out.println("</form>");
        out.println("</center>");
    }
    
    private void createTodo(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String owner = req.getParameter("owner");
            String priority = req.getParameter("priority");
            MainService.getInstance().adaugaTodo(id, name, owner, priority);
            resp.sendRedirect("view");
        } catch (IOException ex) {
            Logger.getLogger(create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void editTodo(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String owner = req.getParameter("owner");
            String priority = req.getParameter("priority");
            MainService.getInstance().updateTodo(id, name, owner, priority);
            resp.sendRedirect("view");
        } catch (IOException ex) {
            Logger.getLogger(create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
