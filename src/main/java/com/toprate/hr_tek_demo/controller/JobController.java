package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.dto.JobDto;
import com.toprate.hr_tek_demo.dto.SearchJobDto;
import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.JobService;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private LocationServiceImpl locationService;

    @Autowired
    private PartnerServiceImpl partnerService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private SkillServiceImpl skillService;

    @Autowired
    private ContactServiceImpl contactService;

    // hien thi danh sach job dang tuyen
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

        // tim tat cac cac cv ung tuyen vao job
        List<Contact> contactList = contactService.findAllContactForJob(id);

        // loc trung ket qua
        Set<String> idSet = new HashSet<>();
        List<Contact> contacts = contactList.stream()
                .filter(e -> idSet.add(e.getCandidateId()))
                .collect(Collectors.toList());

        model.addAttribute("contacts", contacts);
        model.addAttribute("jobDetail", jobDetail);
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills", skillService.getAllSkill());
        return "job/job-detail";
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
        model.addAttribute("users",userServiceImpl.getAllUser());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "job/add-job";
    }

    @RequestMapping(value = "/save-job", method = RequestMethod.POST)
    public String saveJob(@Valid @ModelAttribute("newJob") JobDto newJob, Errors errors) {

        if (null != errors && errors.getErrorCount() > 0) {
            return "job/add-job";
        }

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
        model.addAttribute("users",userServiceImpl.getAllUser());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "job/edit-job";
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

    // hien thi trang chu man hinh chinh
    @GetMapping("/home")
    public String showHomePage(Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto) {
        return findJobShowHomePage(1, "jobRecruitmentId", "asc", model, searchJobDto);
    }

    // phan trang hien thi danh sach job dang tuyen
    @GetMapping("/JobPage/{pageNo}")
    public String findJobPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                   Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto) {
        // So phan tu hien thi tren 1 trang
        int pageSize = 5;

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

    // phan trang hien thi trang chu
    @GetMapping("/Page/{pageNo}")
    public String findJobShowHomePage(@PathVariable (value = "pageNo") int pageNo,
                                   @RequestParam("sortField") String sortField,
                                   @RequestParam("sortDir") String sortDir,
                                   Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto) {
        // So phan tu hien thi tren 1 trang
        int pageSize = 5;

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
        return "job/home";
    }

    // search full text
    // tim kiem theo toan bo cac tieu chi
    @RequestMapping("/search-job")
    public String viewHomePage(Model model, @ModelAttribute("searchJobDto") SearchJobDto searchJobDto ) {
        List<JobRequirements> jobRequirementsList = jobService.searchJobByKeyword(searchJobDto);

        // loc trung ket qua
        Set<String> idSet = new HashSet<>();
        List<JobRequirements> jobs = jobRequirementsList.stream()
                .filter(e -> idSet.add(e.getJobRecruitmentId()))
                .collect(Collectors.toList());

        model.addAttribute("jobs", jobs);
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills", skillService.getAllSkill());

        return "job/list-job";
    }
}
