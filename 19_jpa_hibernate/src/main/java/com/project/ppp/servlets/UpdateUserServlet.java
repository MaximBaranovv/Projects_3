package com.project.ppp.servlets;

import com.project.ppp.dao.jdbc.JdbcRoleService;
import com.project.ppp.dao.jdbc.JdbcUserService;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.project.ppp.servlets.Pages.OK;
import static com.project.ppp.servlets.Pages.UPDATE_USER;

@WebServlet(name = "UpdateUserServlet", value = "/admin/update")
public class UpdateUserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateUserServlet.class.getName());

    private JdbcRoleService roleDao;
    private JdbcUserService userDao;

    @Override
    public void init() throws ServletException {
        this.roleDao = new JdbcRoleService();
        this.userDao = new JdbcUserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", roleDao.findAll());
        request.setAttribute("user", userDao.findById(Long.parseLong(request.getParameter("id"))));
        request.getRequestDispatcher(UPDATE_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Long.parseLong(request.getParameter("id")));
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
        userDao.update(user);
        request.setAttribute("user", "User " + user.getFirstName() + " has been updated successively. Redirecting");
        request.getRequestDispatcher(OK).forward(request, response);
    }
}
