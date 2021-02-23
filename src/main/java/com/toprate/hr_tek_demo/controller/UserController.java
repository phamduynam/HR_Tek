package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.RoleService;
import com.toprate.hr_tek_demo.secvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    // Hiển thị danh sách người dùng của hệ thống
    @GetMapping("/index")
    public String showUserList(Model model, @ModelAttribute("searchUserDto") SearchUserDto searchUserDto) {
        return findPaginated(1, "userId", "asc", model, searchUserDto);
    }

    // Hiển thị view thêm mới
    @GetMapping("/add")
    public String showNewUserPage(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRole());
        return "user/add";
    }

    @PostMapping("/save")
    public String saveUser(Users user, RedirectAttributes redirAttrs) {
        Users users = userService.getUserByGmail(user.getGmail());
        if(users != null){
            redirAttrs.addFlashAttribute("error", "Gmail đã được sử dụng cho tài khoản khác !");
            return "redirect:user/add";
        }
        userService.saveUser(user);
        return "redirect:user/index";
    }


    // Hiển thị view edit
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Users user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        model.addAttribute("user", user);
        return "/user/edit";
    }

    @PostMapping("/update")
    public String saveUser(Users user) {
        userService.saveUser(user);
        return "redirect:/user/index";
    }

    // Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        Users user = userService.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteUser(user);

        return "redirect:/user/index";
    }

    // Page able
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model, @ModelAttribute("searchUserDto") SearchUserDto searchUserDto ) {
        int pageSize = 5;

        Page<Users> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Users> users = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.getAllRole());
        return "/user/index";
    }

    // Search
    @PostMapping("/search")
    public String viewSearchPage(Model model, @ModelAttribute("searchUserDto") SearchUserDto searchUserDto) {
        List<Users> users = userService.searchUserByKeyword(searchUserDto);
        model.addAttribute("users", users);
        model.addAttribute("roles", roleService.getAllRole());
        return "/user/index";
    }
}
