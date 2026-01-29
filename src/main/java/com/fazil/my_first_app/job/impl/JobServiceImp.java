package com.fazil.my_first_app.job.impl;

import com.fazil.my_first_app.job.Job;
import com.fazil.my_first_app.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImp implements JobService {

private final List<Job> jobs=new ArrayList<>();
private Long id=1L;

    @Override
    public List<Job> getJobs() {
        return jobs;
    }

    @Override
    public void addJob(Job job) {
        job.setId(id++);
        this.jobs.add(job);
    }

    @Override
    public Job getJobById(Long id){
        return this.jobs.stream().filter(j->j.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Job updateJob(Job job, Long id){
       Job toUpdateJob=this.jobs.stream().filter(j->j.getId().equals(id)).findFirst().orElse(null);

       if(toUpdateJob!=null){
           toUpdateJob.setTitle(job.getTitle());
           toUpdateJob.setDescription(job.getDescription());
           toUpdateJob.setMinSalary(job.getMinSalary());
           toUpdateJob.setMaxSalary(job.getMaxSalary());
           toUpdateJob.setLocation(job.getLocation());
           return toUpdateJob;
       }
       return null;
    }

    @Override
    public void deleteJob(Long id){
        this.jobs.removeIf(j->j.getId().equals(id));
    }
}
