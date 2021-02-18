package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.JobDto;
import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.model.Contact;
import com.toprate.hr_tek_demo.model.JobRequirements;
import com.toprate.hr_tek_demo.model.JobWorkSkill;
import com.toprate.hr_tek_demo.secvice.*;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    LocationService locationService;

    @Autowired
    PartnerService partnerService;

    @Autowired
    UserService userService;

    @Autowired
    PositionService positionService;

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    JobWorkSkillService jobWorkSkillService;

    @Autowired
    SkillService skillService;

    @Autowired
    ContactService contactService;

    // danh sach job dang tuyen
    @GetMapping("/list-job")
    public String showJobList(Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto) {
        return findJobPaginated(1, "jobRecruitmentId", "asc", model, searchJobDto);
    }

    // thong tin chi tiet 1 job
    @GetMapping("/job-detail/{id}")
    public String showJobDetail(@PathVariable("id") String id, Model model) {
        JobRequirements jobRequirement = jobService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Id:" + id));
        JobDto jobDetail = jobRequirement.convertToJobDto();

        List<Contact> contacts = contactService.findAllContactForJob(id);

        model.addAttribute("contacts", contacts);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "detail";
    }

    // Them moi 1 Job
    @RequestMapping("/add-job")
    public String showNewJobPage(Model model) {
        JobDto newJob = new JobDto();

        // them moi cac skill
        ArrayList<JobWorkSkill> jobWorkSkills = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            jobWorkSkills.add(new JobWorkSkill());
        }
        newJob.setJobWorkSkills(jobWorkSkills);

        model.addAttribute("newJob", newJob);
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "add";
    }

    @RequestMapping(value = "/save-job", method = RequestMethod.POST)
    public String saveJob(@ModelAttribute("newJob") JobDto newJob) {
        JobRequirements jobRequirement = newJob.convertToModel();
        jobService.saveJob(jobRequirement);
        return "redirect:/list-job";
    }

    // chinh sua 1 job
    @GetMapping("edit-job/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        JobRequirements jobEdit = jobService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Id:" + id));
        JobDto job = jobEdit.convertToJobDto();
        model.addAttribute("job", job);
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "edit";
    }

    @PostMapping("update-job")
    public String updateJob(@ModelAttribute("job") JobDto job) {

        JobRequirements jobRequirement = job.convertToModel();
        jobService.updateJob(jobRequirement);
        return "redirect:/list-job";
    }



    // xoa job
    @GetMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable("id") String id, Model model) {
        JobRequirements job = jobService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        jobService.deleteJob(job);
        return "redirect:/list-job";
    }

    // hien thi trang chu
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("jobs", jobService.findAllJob());
        return "job/home";
    }

    // phan trang
    @GetMapping("/JobPage/{pageNo}")
    public String findJobPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto) {
        int pageSize = 10;

        Page<JobRequirements> page = jobService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<JobRequirements> jobs = page.getContent();

        model.addAttribute("currentJobPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("jobs", jobs);
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("skills", skillService.getAllSkill());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("locations", locationService.findAllLocation());
        return "job/list-job";
    }

    // search full text
    // tim kiem
    @RequestMapping("/search-job")
    public String viewHomePage(Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto ) {
        List<JobRequirements> jobs = jobService.searchJobByKeyword(searchJobDto);
        model.addAttribute("jobs", jobs);
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("positions", positionService.getAllPosition());

        return "job/search-job";
    }

}
