package com.toprate.hr_tek_demo.secvice.impl;

import com.toprate.hr_tek_demo.model.Role;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 13:58 04/02/2021
 */
@Service
public class DefaultOAuth2UserServiceImpl extends DefaultOAuth2UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        try {
            return processOAuth2User(oAuth2UserRequest);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest) {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String emailFromGoogle = (String) attributes.get("email");
        Users user = userRepository.findByGmail(emailFromGoogle).orElse(null);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return new DefaultOAuth2User(grantedAuthorities, attributes, "sub");
    }
}