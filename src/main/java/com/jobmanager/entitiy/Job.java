package com.jobmanager.entitiy;

import jakarta.persistence.*;

/**
 * This entity class represents a job application, storing name of company
 * and the title of the job. This class will be table in the database
 * created by JPA. A job has a reference to user to create a bidirectional
 * one-to-many relationship. Additionally, it has a reference to an
 * optional SocialMedia object to store social media related information.
 */
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "img_url", columnDefinition = "TEXT")
    private String imgUrl;

    @Column(name = "job_url", columnDefinition = "TEXT")
    private String jobUrl;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private SocialMedia media;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    public Job(Long id, String company, String title, Status status) {
        this.jobId = id;
        this.company = company;
        this.title = title;
        this.status = status;
    }

    public Job() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long id) {
        this.jobId = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public SocialMedia getMedia() {
        return media;
    }

    public void setMedia(SocialMedia media) {
        this.media = media;
    }
}
