package com.example.serverletdemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter writer = response.getWriter();
//        writer.println("<html>");
//        writer.println("<head>");
//        writer.println("<title>login</title>");
//        writer.println("</head>");
//        writer.println("<body>");
//        writer.println("<h2>login page</h2>");
//        writer.println("</body>");
//        writer.println("</html>");
        String param = request.getParameter("name");

        System.out.println(request.getMethod());

        request.setAttribute("name", param);
        // redirect here
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
