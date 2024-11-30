package com.example.demo2.service;

import com.example.demo2.dto.JobDto;

import java.util.List;

public interface JobService {

    List<JobDto> getAllJobs(Integer page, Integer size);

    JobDto saveJob(JobDto jobDto);

    JobDto updateJob(Long id, JobDto jobDto);

    void deleteJob(Long id);
}
