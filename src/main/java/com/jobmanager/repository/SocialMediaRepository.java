package com.jobmanager.repository;

import com.jobmanager.entitiy.Account;
import com.jobmanager.entitiy.SocialMedia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends CrudRepository<SocialMedia, Long> {
}
