package com.toprate.hr_tek_demo.controller;

import com.toprate.hr_tek_demo.model.*;
import com.toprate.hr_tek_demo.secvice.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private String id;
    private JobRequirements job;
    private BindingResult result;
    private Model model;

    // danh sach job dang tuyen
    @GetMapping("/list-job")
    public String showJobList(Model model) {
        return findJobPaginated(1, "jobRecruitmentId", "asc", model);
    }

    // thong tin chi tiet 1 job
    @GetMapping("/job-detail/{id}")
    public String showJobDetail(@PathVariable("id") String id, Model model) {
        JobRequirements job = jobService.findJobById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job Id:" + id));
        model.addAttribute("jobDetail", job);
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

    @RequestMapping(value = "/save-job", method = RequestMethod.POST)
    public String saveJob(@ModelAttribute("job") JobRequirements job) {

        jobService.saveJob(job);

        List<JobPosition> lst = job.getJobPositionList();

        for (JobPosition jobPosition : lst) {
            List<Integer> id = job.getPositionId();
        }

        String jobId = job.getJobRecruitmentId();
        List<Integer> positionsId = job.getPositionId();
        List<Integer> skillsId = job.getSkillId();

        for(int positionId : positionsId) {
            jobPositionService.save(jobId, positionId);
        }
        for(int skillId : skillsId) {
            jobWorkSkillService.save(jobId, skillId);
        }
        return "redirect:/list-job";
    }

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

    @PostMapping("update-job/{id}")
    public String updateUser(@PathVariable("id") String id, JobRequirements job, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            job.setJobRecruitmentId(id);
            return "job/edit-job";
        }

        // lay ra danh danh job_position tu db theo jobId
        List<JobPosition> list = jobPositionService.findAllByJobId(id);
        for(JobPosition jobPosition : list) {
            jobPositionService.delete(jobPosition);
        }

        // lay ra danh sach job_work_skill tu db theo jobId
        List<JobWorkSkill> listJobWorkSkill = jobWorkSkillService.findAllByJobId(id);
        for(JobWorkSkill jobWorkSkill : listJobWorkSkill) {
            jobWorkSkillService.delete(jobWorkSkill);
        }

        // cap nhat job
        job.setJobRecruitmentId(id);
        jobService.saveJob(job);

        // luu cac position moi va skill moi vao db
        List<JobPosition> lstPosition = job.getJobPositionList();
        List<JobWorkSkill> lstWorkSkill = job.getJobWorkSkills();

        for (JobPosition jobPosition : lstPosition) {
            int jobWorkPositionId = jobPosition.getJobPositionId();
            String jobId = job.getJobRecruitmentId();
            Position position = jobPosition.getPosition();
            Integer positionId = position.getPositionId();
            jobPositionService.save(jobId, positionId);
        }

        for (JobWorkSkill jobWorkSkill : lstWorkSkill) {
            String jobId = job.getJobRecruitmentId();
            Skill skill = jobWorkSkill.getSkill();
            Integer skillId = skill.getSkillId();
            jobWorkSkillService.save(jobId, skillId);
        }

        return "redirect:/list-job";
    }

    @PostMapping("update-job/{id}")
    public String updateUser(@PathVariable("id") String id, JobRequirements job, Model model) {

        if (result.hasErrors()) {
            job.setJobRecruitmentId(id);
            return "job/edit-job";
        }
        job.setJobRecruitmentId(id);
        jobService.saveJob(job);

        String jobId = job.getJobRecruitmentId();
        List<Integer> positionsId = job.getPositionId();
        List<Integer> skillsId = job.getSkillId();

        for(int positionId : positionsId) {
            jobPositionService.save(jobId, positionId);
        }
        for(int skillId : skillsId) {
            jobWorkSkillService.save(jobId, skillId);
        }
        return "redirect:/index";
    }


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

    // phan trang
    @GetMapping("/JobPage/{pageNo}")
    public String findJobPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
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
        return "job/list-job";
    }

}
