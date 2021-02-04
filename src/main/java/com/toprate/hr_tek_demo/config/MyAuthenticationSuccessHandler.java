package com.toprate.hr_tek_demo.config;

import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
 * Date: 11:37 04/02/2021
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        Users user = userService.getUserByGmail(authentication.getName()).get();
        session.setAttribute("userInfo", user);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}
