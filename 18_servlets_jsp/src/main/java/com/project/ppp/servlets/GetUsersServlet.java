package com.project.ppp.servlets;

import com.project.ppp.dao.UserDao;
import com.project.ppp.dao.jdbc.JdbcUserDao;
import configuration.jdbc.ConnectionSupplierJdbc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.project.ppp.servlets.Pages.ADMIN;

@WebServlet(name = "GetUsersServlet", value = "/admin/get")
public class GetUsersServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetUsersServlet.class.getName());
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        try {
            this.userDao = new JdbcUserDao(new ConnectionSupplierJdbc());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userDao.findAll());
        request.getRequestDispatcher(ADMIN).forward(request, response);
    }

}
