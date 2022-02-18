package com.example.serverletdemo;

import com.example.service.AuthenticationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {


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

        System.out.println(request.getMethod());

        request.setAttribute("name", request.getParameter("f_name"));
        request.setAttribute("pass", request.getParameter("pass"));
        request.setAttribute("l_name", request.getParameter("l_name"));
        // redirect here
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("f_name");
        String lastName = request.getParameter("l_name");
        String passWord = request.getParameter("pass");
        String cnf_pass = request.getParameter("pass_conf");



        System.out.println(firstName + " "  + lastName + " "  + passWord + " " + cnf_pass);

        // dispatch to welcome page
    }
}
