package com.example.demo2.repository;

import com.example.demo2.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, Long> , JpaSpecificationExecutor {
}
