package com.example.serverletdemo;

import com.example.service.AuthenticationService;
import environment.MiddleMan;


import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    AuthenticationService authenticationService = new AuthenticationService();
    private String message;
    String userName;
    String password;
    InputStream databaseProp;

    public void init() {

        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.password = req.getParameter("password"); // key has to be the same as name field in html
        this.userName = req.getParameter("userName");
        this.databaseProp = getServletContext().getResourceAsStream("/WEB-INF/lib/database.properties");

        req.setAttribute("datafile", databaseProp); // to be dispatched
        MiddleMan.setInputStream(databaseProp);

        if(authenticationService.isValid(userName,password, this.databaseProp)){
            resp.getWriter().append("Hello " + userName +", welcome");
            System.out.println(this.userName + " " + this.password);
        }else {
            resp.getWriter().append("Wrong combination of password and username");
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req,resp);
        }
    }

    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
//    }

    public void destroy() {
    }

    private boolean isValid(String userName, String password, InputStream in){
        AuthenticationService authenticationService = new AuthenticationService();

        return authenticationService.isValid(userName, password, in);
    }
}