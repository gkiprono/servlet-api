package com.example.serverletdemo;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
    private String message;
    String userName;
    String password;

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

        if(isValid(userName, password)){
            resp.getWriter().append("Hello" + userName +", welcome");
            System.out.println(this.userName + " " + this.password);
        }else {
            resp.getWriter().append("Wrong combination of password and username");
        }
    }

    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
//    }

    public void destroy() {
    }

    private boolean isValid(String userName, String password){
        return userName.equals("admin") && password.equals("root");
    }
}