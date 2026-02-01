package com.fazil.my_first_app.job.impl;

import com.fazil.my_first_app.job.Job;
import com.fazil.my_first_app.job.JobRepository;
import com.fazil.my_first_app.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService {

//private final List<Job> jobs=new ArrayList<>();
//private Long id=1L;
     JobRepository jobRepository;

    public JobServiceImp(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(Job job) {
        this.jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id){
        return this.jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job updateJob(Job job, Long id){
      Optional<Job> optionalJob=this.jobRepository.findById(id);

       if(optionalJob.isPresent()){
           Job toUpdateJob=optionalJob.get();
           toUpdateJob.setTitle(job.getTitle());
           toUpdateJob.setDescription(job.getDescription());
           toUpdateJob.setMinSalary(job.getMinSalary());
           toUpdateJob.setMaxSalary(job.getMaxSalary());
           toUpdateJob.setLocation(job.getLocation());
           return this.jobRepository.save(toUpdateJob);
       }
       return null;
    }

    @Override
    public boolean deleteJob(Long id){
        try {
            this.jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
