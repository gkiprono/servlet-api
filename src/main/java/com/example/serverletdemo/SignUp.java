package com.example.serverletdemo;

import com.example.dao.UserData;
import com.example.dao.UserDataInterface;
import com.example.models.Users;
import com.example.service.IdProvider;
import com.example.utils.EncryptDecrypt;
import environment.MiddleMan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet {
    private UserDataInterface userDataInterface = new UserData();
    private InputStream inputStream;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getMethod());

        request.setAttribute("name", request.getParameter("f_name"));
        request.setAttribute("pass", request.getParameter("pass"));
        request.setAttribute("l_name", request.getParameter("l_name"));
        // redirect here
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users user = new Users();

        user.setEmail(request.getParameter("email"));
        user.setUserName(request.getParameter("username"));
        user.setFirstName(request.getParameter("f_name"));
        user.setLastName(request.getParameter("l_name"));

        user.setSalt(EncryptDecrypt.getSalt(15));
        user.setPassword(EncryptDecrypt.generateSecurePassword(request.getParameter("pass"), user.getSalt()));

        user.setUserId(IdProvider.getNextUserId());
        user.setAccountId(IdProvider.getNextAccountId());

        this.inputStream = getServletContext().getResourceAsStream("/WEB-INF/lib/database.properties");
        // other business logic

        // save user details && todo if saved successfully
        if(userDataInterface.setUser(user, inputStream)){
            response.getWriter().append("Hello " + user.getFirstName() + " " + user.getLastName() +", welcome");
            response.getWriter().append(" Congratulations");
        }
       // dispatch to welcome page
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

}
