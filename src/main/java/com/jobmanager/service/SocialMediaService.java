package com.jobmanager.service;

import com.jobmanager.entitiy.SocialMedia;
import com.jobmanager.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService {

    @Autowired
    private SocialMediaRepository repository;

    public void update(SocialMedia media) {
        repository.save(media);
    }
}
