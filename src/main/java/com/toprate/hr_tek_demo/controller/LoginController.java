package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.User;
import com.toprate.hr_tek_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    // MẶc đinh khi login xong sẽ nhảy vào đây
    @GetMapping("/")
    public String getStart(){
        return "/login";
    }


    @GetMapping("/home")
    public ModelAndView getHome(OAuth2AuthenticationToken authentication,ModelAndView mav) {
    // Xem token nhận được là gì ?// đặt debbug vào để xem nhé
    //        OAuth2AuthorizedClient client = authorizedClientService
    //                .loadAuthorizedClient(
    //                        authentication.getAuthorizedClientRegistrationId(),
    //                        authentication.getName());
        // Lấy email ra từ token google gửi về
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        OidcUserAuthority authority = (OidcUserAuthority) authorities.get(0);
        String gmail = authority.getAttributes().get("email").toString();

        User user = userRepository.findByGmail(gmail);
        if(user != null){
            // Ở đây có thể check quyền sau đó add vào mav các view phù hợp
            if(user.getRole_name().equals("ADMIN")){
                mav.setViewName("admin");
                mav.addObject("user",user);
            }else if(user.getRole_name().equals("MANAGER")){

            }else{
                mav.setViewName("index");
                mav.addObject("user",user);
            }
        }else{
            // Không có user nào giống thì trả về lỗi
            mav.addObject("404");
        }
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
