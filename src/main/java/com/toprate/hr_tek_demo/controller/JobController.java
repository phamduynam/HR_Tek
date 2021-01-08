package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private LocationServiceImpl locationService;

    @Autowired
    private PartnerServiceImpl partnerService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    // danh sach job dang tuyen
    @GetMapping("/list-job")
    public String showJobList(Model model) {
        model.addAttribute("jobs", jobService.findAllJob());
        return "job/list-job";
    }

    // thong tin chi tiet 1 job
    @GetMapping("/job-detail/{id}")
    public String showJobDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("job", jobService.getJobDetailById(id));
        return "job/job-detail";
    }

    // Them moi 1 Job
    @RequestMapping("/add-job")
    public String showNewJobPage(Model model) {
        JobRequirements job = new JobRequirements();
        model.addAttribute("job", job);
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("users",userServiceImpl.getAllUser());

        return "job/add-job";
    }
    @RequestMapping(value = "/save-job", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("job") JobRequirements job) {

        jobService.saveJob(job);
        return "redirect:/list-job";
    }

    // xoa job
    @GetMapping("/delete-job/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {
        JobRequirements job = jobService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        jobService.deleteJob(job);
        return "redirect:/list-job";
    }

}
