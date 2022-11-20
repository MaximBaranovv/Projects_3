package com.project.ppp.servlets;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.dao.UserDao;
import com.project.ppp.dao.jdbc.JdbcRoleDao;
import com.project.ppp.dao.jdbc.JdbcUserDao;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;
import configuration.jdbc.ConnectionSupplierJdbc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.project.ppp.servlets.Pages.ADD_USER;
import static com.project.ppp.servlets.Pages.OK;

@WebServlet(name = "AddUserServlet", value = "/admin/add")
public class AddUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddUserServlet.class.getName());
    private UserDao userDao;
    private RoleDao roleDao;

    @Override
    public void init() throws ServletException {
        try {
            this.userDao = new JdbcUserDao(new ConnectionSupplierJdbc());
            this.roleDao = new JdbcRoleDao(new ConnectionSupplierJdbc());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", roleDao.findAll());
        request.getRequestDispatcher(ADD_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        String startDateStr = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(startDateStr);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        user.setBirthday(sqlStartDate);
        Role role = roleDao.findById(Long.parseLong(request.getParameter("role")));
        user.setRole(role);
        userDao.create(user);
        request.setAttribute("user", "User " + user.getFirstName() + " has been created successively. Redirecting");
        request.getRequestDispatcher(OK).forward(request, response);
    }
}
