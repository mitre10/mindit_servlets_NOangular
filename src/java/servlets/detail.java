/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Todo;
import service.MainService;

@WebServlet(name = "detail", urlPatterns = {"/detail"})
public class detail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = resp.getWriter(); 
            int id = Integer.parseInt(req.getParameter("id"));
            Todo todo = MainService.getInstance().findTodoById(id);
            out.println("<center>");
            out.println("<h1>"+todo.getName()+"</h1>");
            out.println("<p>Your Options: <a href='view'>Back</a>.</p>");
            out.println("<table border='1' cellpadding='5'");
            out.println("<tr><td>Id</td><td>Owner</td><td>Priority</td></tr>");
            
            out.println("<tr>");
            out.println("<td>"+todo.getId()+"</td>");
            out.println("<td>"+ todo.getOwner()+"</td>");
            out.println("<td>"+ todo.getPriority()+"</td>");
            out.println("</tr>");
            out.println("</table");
            out.println("</center>");
        } finally {
            if (out != null) out.close();
        }
    }
 
}
