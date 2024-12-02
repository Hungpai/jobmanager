package com.jobmanager.controller;


import com.jobmanager.entitiy.Account;
import com.jobmanager.entitiy.Job;
import com.jobmanager.entitiy.SocialMedia;
import com.jobmanager.entitiy.Status;
import com.jobmanager.service.AccountService;
import com.jobmanager.service.JobService;
import com.jobmanager.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class JobController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JobService jobService;

    @Autowired
    private SocialMediaService mediaService;

    @GetMapping
    public String getJobPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        List<Job> jobs = acc.getJobs();
        model.addAttribute("account", acc);
        model.addAttribute("jobs", jobs);
        return "job_page";
    }

    @GetMapping("/create")
    public String getCreateJobPage(Model model) {
        model.addAttribute("newJob", new Job());
        return "create_job_page";
    }

    @PostMapping("/create")
    public String createJob(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Job job) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        job.setUser(acc);
        jobService.save(job);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String getEditJobPage(Model model, @PathVariable Long id) {
        Job job = jobService.findById(id);

        List<Status> options = new ArrayList<>();
        options.add(Status.ACCEPTED);
        options.add(Status.PENDING);
        options.add(Status.REJECTED);

        SocialMedia media = job.getMedia();
        if (media == null) {
            media = new SocialMedia();
        }

        model.addAttribute("jobToEdit", job);
        model.addAttribute("mediaToEdit", media);
        model.addAttribute("options", options);
        return "edit_job_page";
    }

    @PostMapping("/edit")
    public String editJob(@ModelAttribute Job job, @ModelAttribute SocialMedia media) {
        mediaService.update(media);
        job.setMedia(media);
        jobService.update(job);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return "redirect:/home";
    }
}