package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.impl.RoleServiceImpl;
import com.toprate.hr_tek_demo.secvice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleService;

    // Hiển thị danh sách người dùng của hệ thống
    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUser());
        model.addAttribute("roles", roleService.getAllRole());
        return "user/index";
    }

    // Them moi 1 nguoi dung cua he thong
    @RequestMapping("/adduser")
    public String showNewUserPage(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRole());

        return "user/add-user";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("user") Users user) {

        userServiceImpl.saveUser(user);
        return "redirect:/index";
    }

    // chinh sua nguoi dung
    @GetMapping("edit-user/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Users user = userServiceImpl.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") String id, @Valid Users user, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            user.setUserId(id);
            return "user/update-user";
        }

        userServiceImpl.saveUser(user);
        model.addAttribute("users", userServiceImpl.getAllUser());
        return "redirect:/index";
    }

    // xoa nguoi dung khoi he thong
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        Users user = userServiceImpl.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userServiceImpl.deleteUser(user);
        return "redirect:/index";
    }


}
