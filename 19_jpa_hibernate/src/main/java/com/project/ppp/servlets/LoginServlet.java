package com.project.ppp.servlets;

import com.project.ppp.dao.jdbc.JdbcUserService;
import com.project.ppp.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.project.ppp.servlets.Pages.LOGIN;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private JdbcUserService userDao;

    @Override
    public void init() throws ServletException {
        this.userDao = new JdbcUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        int idUser = 0;
        try {
            idUser = Math.toIntExact(userDao.findByLoginAndPassword(userName, password).getId());
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        if (idUser != 0) {
            User user = userDao.findById(idUser);
            session.setAttribute("userName", userName);
            session.setAttribute("userRole", user.getRole().getName());
            if (user.getRole().getName().equals("USER")) {
                response.sendRedirect("/user");
            } else if (user.getRole().getName().equals("ADMIN")) {
                response.sendRedirect("/admin/get");
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN).forward(request, response);
    }
}

