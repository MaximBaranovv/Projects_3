package com.project.ppp.servlets;

import com.project.ppp.dao.jdbc.JdbcUserService;
import com.project.ppp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.ppp.servlets.Pages.OK;

@WebServlet(name = "DeleteUserServlet", value = "/admin/delete")
public class DeleteUserServlet extends HttpServlet {

    private JdbcUserService userDao;

    public void init() throws ServletException {
        this.userDao = new JdbcUserService();
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
