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


@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = resp.getWriter(); 
            int id = Integer.parseInt(req.getParameter("id"));
            Todo t = MainService.getInstance().findTodoById(id);
            MainService.getInstance().deleteTodoById(t);
            resp.sendRedirect("view");
            
        } finally {
            if (out != null) out.close();
        }
    }

    

}
