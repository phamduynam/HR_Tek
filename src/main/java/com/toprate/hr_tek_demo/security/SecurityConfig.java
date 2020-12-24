package com.toprate.hr_tek_demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        // Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
        httpSecurity.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')");

        // Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn /user/**
        httpSecurity.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN') or hasRole('USER')");

        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
        httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        httpSecurity
                .authorizeRequests()
                    .antMatchers("/bootstrap/**", "/dist/**", "/plugins/**")
                    .permitAll() // cấp quyền tất cả truy cập vào các đường dẫn treen
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home");

    }

}
