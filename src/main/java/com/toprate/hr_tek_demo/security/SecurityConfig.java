package com.toprate.hr_tek_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userServiceimp")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired AuthenticationManager authenticationManager;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .parentAuthenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        System.out.println(userDetailsService);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

//        // Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
//        httpSecurity.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
//
//        // Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn /user/**
//        httpSecurity.authorizeRequests().antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
//
//        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
//        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Tắt chứng thực token
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeRequests()
                    .antMatchers("/bootstrap/**", "/dist/**", "/plugins/**")
                    .permitAll() // cấp quyền tất cả truy cập vào các đường dẫn treen
                .anyRequest().authenticated()
                    .and()
                .oauth2Login()
//                    .loginPage("/login")
                    .userInfoEndpoint()
                .and()
                .defaultSuccessUrl("/home",true);
    }
}
