package com.servletlearning.controller;

import java.io.IOException;

import com.servletlearning.dao.UserDAO;
import com.servletlearning.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private UserDAO userDAO = new UserDAO();


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String email =
                request.getParameter("email");


        String password =
                request.getParameter("password");


        User user =
                userDAO.login(email, password);



        if(user != null) {


            HttpSession session =
                    request.getSession();


            session.setAttribute(
                    "user",
                    user
            );


            response.sendRedirect("dashboard.jsp");


        } else {


        	request.setAttribute(
        		    "error",
        		    "Invalid email or password"
        		);

        		request.getRequestDispatcher("login.jsp")
        		       .forward(request,response);

        }

    }

}