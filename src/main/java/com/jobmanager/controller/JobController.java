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

/**
 * This controller class handles all the logic related to creating, reading, updating
 * and deleting a Job.
 */
@Controller
@RequestMapping("/home")
public class JobController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JobService jobService;

    @Autowired
    private SocialMediaService mediaService;


    /**
     * Return the template to display all jobs that were created from the user.
     *
     * @param model       Send data to display.
     * @param userDetails Get current user.
     * @return String indicating the template.
     */
    @GetMapping
    public String getJobPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        List<Job> jobs = acc.getJobs();
        model.addAttribute("account", acc);
        model.addAttribute("jobs", jobs);
        return "job_page";
    }

    /**
     * Returns the page to create a new job. An empty job is send to the
     * client to fill it with data.
     *
     * @param model Send data to the client.
     * @return String indicating the template.
     */
    @GetMapping("/create")
    public String getCreateJobPage(Model model) {
        model.addAttribute("newJob", new Job());
        return "create_job_page";
    }

    /**
     * Takes the data from the submitted from and saves in the database
     *
     * @param userDetails To get username
     * @param job         Serialized object from submitting
     * @return Redirect to the job page.
     */
    @PostMapping("/create")
    public String createJob(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Job job) {
        String username = userDetails.getUsername();
        Account acc = accountService.findByUsername(username);
        job.setUser(acc);
        jobService.save(job);
        return "redirect:/home";
    }

    /**
     * Edits a job based on id.
     *
     * @param model Send data to the model.
     * @param id    identifier of the job entry (primiary key)
     * @return String indicating the tempalte
     */
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

    /**
     * Edits a job by declaring social media information.
     *
     * @param job   Job to edit.
     * @param media SocialMedia object to update.
     * @return String indicating the template to return.
     */
    @PostMapping("/edit")
    public String editJob(@ModelAttribute Job job, @ModelAttribute SocialMedia media) {
        mediaService.update(media);
        job.setMedia(media);
        jobService.update(job);
        return "redirect:/home";
    }

    /**
     * Deletes a job from the database.
     *
     * @param id Identifier of the job to delete.
     * @return String indicating the template to return.
     */
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return "redirect:/home";
    }
}