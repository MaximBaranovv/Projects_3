package com.project.ppp.servlets;

import com.project.ppp.dao.jdbc.JdbcUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.ppp.servlets.Pages.ADMIN;

@WebServlet(name = "GetUsersServlet", value = "/admin/get")
public class GetUsersServlet extends HttpServlet {

    private JdbcUserService userDao;

    @Override
    public void init() throws ServletException {
        this.userDao = new JdbcUserService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("users", userDao.findAll());
        request.getRequestDispatcher(ADMIN).forward(request, response);
    }
}



















