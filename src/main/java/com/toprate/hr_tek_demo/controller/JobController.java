package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.Users;
import com.toprate.hr_tek_demo.secvice.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JobController {


    private JobServiceImpl jobService;

    public JobServiceImpl getJobService() {
        return jobService;
    }
    @Autowired
    public void setJobService(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

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
