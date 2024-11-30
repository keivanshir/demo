package com.example.demo2.service.implementation;

import com.example.demo2.dto.JobDto;
import com.example.demo2.entity.Job;
import com.example.demo2.exception.NotFoundException;
import com.example.demo2.mapper.EntityMapper;
import com.example.demo2.repository.JobRepository;
import com.example.demo2.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private EntityMapper mapper;

    @Override
    public List<JobDto> getAllJobs(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.findAll(pageable)
                .stream()
                .map(job -> mapper.mapToJobDto(job))
                .toList();
    }

    @Override
    public JobDto saveJob(JobDto jobDto) {

        Job job = new Job();
        job.setJobName(jobDto.getJobName());
        job.setDescription(jobDto.getDescription());

        if (jobDto.getAddress() != null){
            job.setAddress(jobDto.getAddress()
                    .stream()
                    .map(addressDto -> mapper.mapToAddress(addressDto))
                    .collect(Collectors.toList()));
        }

        return mapper.mapToJobDto(jobRepository.save(job));
    }

    @Override
    public JobDto updateJob(Long id, JobDto jobDto) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("job not found!"));

        job.setJobName(jobDto.getJobName());
        job.setDescription(jobDto.getDescription());

        if (jobDto.getAddress() != null){
            job.setAddress(jobDto.getAddress()
                    .stream()
                    .map(addressDto -> mapper.mapToAddress(addressDto))
                    .collect(Collectors.toList()));
        }

        return mapper.mapToJobDto(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("job not found!"));
        jobRepository.delete(job);
    }
}
