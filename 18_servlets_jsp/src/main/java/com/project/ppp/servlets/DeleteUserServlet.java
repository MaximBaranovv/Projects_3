package com.project.ppp.servlets;

import com.project.ppp.dao.UserDao;
import com.project.ppp.dao.jdbc.JdbcUserDao;
import com.project.ppp.entity.User;
import configuration.jdbc.ConnectionSupplierJdbc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.project.ppp.servlets.Pages.OK;

@WebServlet(name = "DeleteUserServlet", value = "/admin/delete")
public class DeleteUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteUserServlet.class.getName());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User user = new User();
        user.setId(id);
        userDao.remove(user);
        request.setAttribute("user", "User has been deleted successively. Redirecting");
        request.getRequestDispatcher(OK).forward(request, response);
    }
}
