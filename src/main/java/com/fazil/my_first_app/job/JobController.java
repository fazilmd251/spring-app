package com.fazil.my_first_app.job;

import com.fazil.my_first_app.job.impl.JobServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping("/test")
    public String test(){
        return "app works";
    }

    @GetMapping
    public ResponseEntity<List<Job>> getJobs(){
      return new ResponseEntity<>(jobService.getJobs(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.addJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job= jobService.getJobById(id);
        if(job!=null)
             return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJobs(@PathVariable Long id,@RequestBody Job job){
        Job updatedJob=jobService.updateJob(job,id);
        if(updatedJob!=null)
            return ResponseEntity.ok(updatedJob);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
