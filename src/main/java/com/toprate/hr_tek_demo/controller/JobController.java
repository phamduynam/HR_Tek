package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;
import java.util.List;

@Controller
public class JobController {

    @Autowired
    private JobDtoServiceImpl jobDtoService;

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private LocationServiceImpl locationService;

    @Autowired
    private PartnerServiceImpl partnerService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PositionServiceImpl positionService;

    @Autowired
    private JobPositionServiceImpl jobPositionService;

    @Autowired
    private JobWorkSkillServiceImpl jobWorkSkillService;

    @Autowired
    private SkillServiceImpl skillService;

    // danh sach job dang tuyen
    @GetMapping("/list-job")
    public String showJobList(Model model) {
        model.addAttribute("jobs", jobService.findAllJob());
        return "job/list-job";
    }

    // thong tin chi tiet 1 job
    @GetMapping("/job-detail/{id}")
    public String showJobDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("job", jobDtoService.getJobDetailById(id));
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
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "job/add-job";
    }
//    @RequestMapping(value = "/save-job", method = RequestMethod.POST)
//    public String saveJob(@ModelAttribute("job") JobRequirements job) {
//
//        jobService.saveJob(job);
//
//        String jobId = job.getJobRecruitmentId();
//        List<Integer> positionsId = job.getPositionId();
//        List<Integer> skillsId = job.getSkillId();
//
//        for(int positionId : positionsId) {
//            jobPositionService.save(jobId, positionId);
//        }
//        for(int skillId : skillsId) {
//            jobWorkSkillService.save(jobId, skillId);
//        }
//        return "redirect:/list-job";
//    }

    // chinh sua 1 job
    @GetMapping("edit-job/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        JobRequirements job = jobDtoService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Id:" + id));
        model.addAttribute("job", job);
        model.addAttribute("locations", locationService.findAllLocation());
        model.addAttribute("partners", partnerService.findAllPartner());
        model.addAttribute("users",userServiceImpl.getAllUser());
        model.addAttribute("positions", positionService.getAllPosition());
        model.addAttribute("skills",skillService.getAllSkill());
        return "job/edit-job";
    }

//    @PostMapping("update-job/{id}")
//    public String updateUser(@PathVariable("id") String id, JobRequirements job, BindingResult result,
//                             Model model) {
//        if (result.hasErrors()) {
//            job.setJobRecruitmentId(id);
//            return "job/edit-job";
//        }
//        job.setJobRecruitmentId(id);
//        jobService.saveJob(job);
//
//        String jobId = job.getJobRecruitmentId();
//        List<Integer> positionsId = job.getPositionId();
//        List<Integer> skillsId = job.getSkillId();
//
//        for(int positionId : positionsId) {
//            jobPositionService.save(jobId, positionId);
//        }
//        for(int skillId : skillsId) {
//            jobWorkSkillService.save(jobId, skillId);
//        }
//        return "redirect:/index";
//    }

    // xoa job
    @GetMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable("id") String id, Model model) {
        JobRequirements job = jobDtoService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        jobDtoService.deleteJob(job);
        return "redirect:/list-job";
    }

    // hien thi trang chu
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("jobs", jobService.findAllJob());
        return "job/home";
    }

}
