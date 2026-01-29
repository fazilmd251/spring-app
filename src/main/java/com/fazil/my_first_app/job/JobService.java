package com.fazil.my_first_app.job;

import java.util.List;

public interface JobService {
    List<Job> getJobs();
    void addJob(Job job);
    Job getJobById(Long id);
    Job updateJob(Job job,Long id);
    void deleteJob(Long id);

}
