package com.example.demo2.controller;

import com.example.demo2.dto.JobDto;
import com.example.demo2.dto.Response;
import com.example.demo2.service.implementation.JobServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobController {

    private JobServiceImpl jobService;

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Response> getAllJobs(@PathVariable("page") Integer page,
                                               @PathVariable("size") Integer size){
        Response response = Response.builder()
                .jobDtoList(jobService.getAllJobs(page, size))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveJob(@RequestBody JobDto jobDto){
        Response response = Response.builder()
                .jobDto(jobService.saveJob(jobDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateJob(@PathVariable Long id, @RequestBody JobDto jobDto){
        Response response = Response.builder()
                .jobDto(jobService.updateJob(id, jobDto))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        Response response = Response.builder()
                .message("job deleted successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
