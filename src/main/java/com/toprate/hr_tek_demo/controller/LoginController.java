package com.toprate.hr_tek_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    // MẶc đinh khi login xong sẽ nhảy vào đây
    @GetMapping("/")
    public String getStart(){
        return "/login";
    }


    @GetMapping("/home-page")
    public ModelAndView getHome(OAuth2AuthenticationToken authentication,ModelAndView mav) {
    // Xem token nhận được là gì ?// đặt debbug vào để xem nhé
//            OAuth2AuthorizedClient client = authorizedClientService
//                    .loadAuthorizedClient(
//                            authentication.getAuthorizedClientRegistrationId(),
//                            authentication.getName());
//        // Lấy email ra từ token google gửi về
//        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
//        OidcUserAuthority authority = (OidcUserAuthority) authorities.get(0);
//        String gmail = authority.getAttributes().get("email").toString();
//
//        Users user = userService.getUserByGmail(gmail);

//        if(user != null) {
//
//            mav.setViewName("admin");
//            mav.addObject("user", user);
//        }
        mav.setViewName("admin");
        return mav;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

}
