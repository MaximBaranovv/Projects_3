package com.project.ppp.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.project.ppp.servlets.Pages.LOGIN;

@WebFilter("/admin/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("userName") != null && session.getAttribute("userRole") != null;
        if (loggedIn) {
            if (!request.getSession().getAttribute("userRole").toString().equals("ADMIN")) {
                response.sendRedirect(LOGIN);
            } else {
                filterChain.doFilter(request, response);
            }
        } else response.sendRedirect(LOGIN);


    }

    @Override
    public void destroy() {

    }
}

