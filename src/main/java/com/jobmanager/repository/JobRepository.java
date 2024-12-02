package com.jobmanager.repository;

import com.jobmanager.entitiy.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    List<Job> findAllByUserId(Long user_id);
}
