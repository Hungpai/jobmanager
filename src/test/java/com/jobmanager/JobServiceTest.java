package com.jobmanager;

import com.jobmanager.entitiy.Job;
import com.jobmanager.entitiy.Status;
import com.jobmanager.service.AccountService;
import com.jobmanager.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JobServiceTest {
    @Autowired
    private AccountService service;

    @Autowired
    private JobService jobService;

    @Test
    @DirtiesContext
    void shouldDeleteJobFromUserByJobId() {
        Long jobId = (long) 14;
        jobService.delete(jobId);

        // From Job Service
        List<Job> job = jobService.findAllByUserId((long) 99);
        assertThat(job.size()).isEqualTo(3);
    }

    @Test
    @DirtiesContext
    void shouldEditJob() {
        Long jobId = (long) 12;
        Job job = jobService.findById(jobId);

        // Set Status to REJECTED
        Job updateJob = new Job(job.getJobId(), job.getCompany(), job.getTitle(), Status.REJECTED);
        jobService.update(updateJob);

        Job getJob = jobService.findById(jobId);
        assertThat(getJob.getStatus()).isEqualTo(Status.REJECTED);
    }
}

