package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.SearchExceptionDto;
import com.toprate.hr_tek_demo.dto.SearchUserDto;
import com.toprate.hr_tek_demo.model.ExceptionLog;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 01:25 18/02/2021
 */
@Controller
@RequestMapping("/exception-log")
public class ExceptionLogController {
    @Autowired
    ExceptionLogService exceptionLogService;

    @GetMapping("/view")
    public String accessDenied(Model model) {
        model.addAttribute("exceptionLogs", exceptionLogService.getAll());
        model.addAttribute("searchExceptionDto",new SearchExceptionDto());
        return "admin/exception_log_manager";
    }
}
