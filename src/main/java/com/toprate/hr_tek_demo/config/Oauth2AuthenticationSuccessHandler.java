package com.toprate.hr_tek_demo.config;


import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 11:07 03/02/2021
 */
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();
        Users user = userRepository.findByGmail(email).orElse(null);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", user);
            redirectStrategy.sendRedirect(request, response, "/home");
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
    }
}
