package com.toprate.hr_tek_demo.config;

import com.toprate.hr_tek_demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 10:48 04/02/2021
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationSuccessHandler myOauth2AuthenticationSuccessHandler() {
        return new Oauth2AuthenticationSuccessHandler();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/*").hasAnyRole(Constants.ROLE.ADMIN, Constants.ROLE.MANAGER, Constants.ROLE.HR)
                .and()
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler())
                .loginPage("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(myOauth2AuthenticationSuccessHandler())
                .userInfoEndpoint()
                .userService(defaultOAuth2UserService);
    }
}