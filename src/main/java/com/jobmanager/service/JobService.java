package com.jobmanager.service;

import com.jobmanager.entitiy.Job;
import com.jobmanager.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Job findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Job> findAllByUserId(Long user_id) {
        return repository.findAllByUserId(user_id);
    }

    public void save(Job job) {
        repository.save(job);
    }

    public void update(Job job) {
        repository.save(job);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
