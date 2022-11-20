package com.project.ppp.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String redirectURL;

        if (userDetails.getAuthorities().stream().anyMatch(user -> user.getAuthority().equals("ROLE_ADMIN"))) {
            redirectURL = "admin";
        } else if (userDetails.getAuthorities().stream().anyMatch(user -> user.getAuthority().equals("ROLE_USER"))) {
            redirectURL = "user";
        } else {
            redirectURL = "login";
        }

        response.sendRedirect(redirectURL);
    }
}
