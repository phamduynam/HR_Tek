package com.toprate.hr_tek_demo.config;

import com.toprate.hr_tek_demo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 10:48 04/02/2021
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationSuccessHandler myOauth2AuthenticationSuccessHandler() {
        return new Oauth2AuthenticationSuccessHandler();
    }

    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/*").hasAnyRole(Constants.ROLE.ADMIN, Constants.ROLE.MANAGER, Constants.ROLE.HR)
                .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .successHandler(myOauth2AuthenticationSuccessHandler())
                    .userInfoEndpoint()
                    .userService(defaultOAuth2UserService)
                .and()
                    .failureUrl("/login?error")
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                .httpBasic();
    }
}