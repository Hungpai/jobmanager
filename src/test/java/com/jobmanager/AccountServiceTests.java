package com.jobmanager;


import com.jobmanager.entitiy.Account;
import com.jobmanager.entitiy.Job;
import com.jobmanager.entitiy.Status;
import com.jobmanager.service.AccountService;
import com.jobmanager.service.JobService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService service;

    @Autowired
    private JobService jobService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void shouldReturnAccountByUsername() {
        Account acc = service.findByUsername("Hung");

        assertThat(acc.getUsername()).isEqualTo("Hung");
        assertThat(acc.getRole()).isEqualTo("USER");
    }

    @Test
    @Transactional
    void shouldReturnAListOfJobsFromUsername() {
        Account acc = service.findByUsername("Hung");
        List<Job> jobs = acc.getJobs();

        assertThat(jobs.size()).isEqualTo(4);

        Job job = jobs.get(0);
        assertThat(job.getJobId()).isEqualTo(10);
        assertThat(job.getUser().getId()).isEqualTo(99);
        assertThat(job.getCompany()).isEqualTo("VHV");
        assertThat(job.getTitle()).isEqualTo("Java-Entwickler");
        assertThat(job.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @Test
    void shouldReturnNullWithUnknownUsername() {
        Account acc = service.findByUsername("Toai");
        assertThat(acc).isEqualTo(null);
    }

    @Test
    void shouldCreateANewAccount() {
        Account acc = new Account(null, "Christian", encoder.encode("xyz"), "USER");
        service.createAccount(acc);
        Account getAcc = service.findByUsername("Christian");

        assertThat(getAcc.getUsername()).isEqualTo("Christian");
//        assertThat(getAcc.getPassword()).isEqualTo(encoder.encode("xyz"));
        assertThat(getAcc.getRole()).isEqualTo("USER");
    }

    @Test
    void shouldAddJobsToAccount() {
        Account acc = service.findByUsername("Christian");
        Job job1 = new Job(null, "Mercedes", "Lager", Status.ACCEPTED);
        Job job2 = new Job(null, "Telekom", "Generative AI", Status.ACCEPTED);
        job1.setUser(acc);
        job2.setUser(acc);
        jobService.save(job1);
        jobService.save(job2);

        // Access via Job Repository
        List<Job> jobs = jobService.findAllByUserId(acc.getId());
        assertThat(jobs.size()).isEqualTo(2);
    }

    @Test
    void shouldDeleteAccountById() {
        Account acc = service.findByUsername("Christian");
        long id = acc.getId();
        service.deleteAccount(id);
        Account getAcc = service.findByUsername("Christian");
        assertThat(getAcc).isEqualTo(null);
    }

    @Test
    void shouldNotReturnJobs() {
        List<Job> jobs = jobService.findAllByUserId((long) 1);
        assertThat(jobs.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnJobs() {
        List<Job> jobs = jobService.findAllByUserId((long) 99);
        assertThat(jobs.size()).isEqualTo(4);
    }

    @Test
    @DirtiesContext
    void shouldUpdateAccount() {
        String newPassword = encoder.encode("abc123");
        Account acc = service.findByUsername("Hung");
        Account updatedAcc = new Account(acc.getId(), acc.getUsername(), newPassword, acc.getRole());
        service.updateAccount(updatedAcc);

        Account getAcc = service.findByUsername("Hung");
        String password = getAcc.getPassword();
        assertThat(password).isEqualTo(newPassword);
    }
}
