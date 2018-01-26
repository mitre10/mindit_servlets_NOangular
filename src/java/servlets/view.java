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


@WebServlet(name = "view", urlPatterns = {"/view"})
public class view extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = null;
        
        try {
            out = resp.getWriter(); 
            out.println("<center>");
            out.println("<h1>List of Todos </h1>");
            out.println("<p>Your Options: <a href='/noAngular/index.html'>HomePage</a>.</p>");
            List<Todo> todos = MainService.getInstance().afiseazaAll();
            out.println("<p><a href='create?id=null&type=create' >Create new</a></p>");
            out.println("<table border='1' cellpadding='5'");
            out.println("<tr><td>Name</td><td>Actions</td></tr>");
            for (Todo t : todos) {
                    out.println("<tr>");
                    out.println("<td>"+"<a href='detail?id="+t.getId()+"' >"+t.getName()+"</a></td>");
                    out.println("<td>");
                        out.println("<a href='create?id="+t.getId()+"&type=edit' >Edit</a>");
                        out.println("| <a href='delete?id="+t.getId()+"' >Delete</a>");
                    out.println("</td>");
                out.println("</tr>");
            }
            
            out.println("</table");
            out.println("</center>");
        } finally {
            if (out != null) out.close();
        }
    }   

}
