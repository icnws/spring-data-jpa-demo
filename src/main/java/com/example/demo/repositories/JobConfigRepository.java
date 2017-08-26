package com.example.demo.repositories;

import com.example.demo.dto.JobConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
public interface JobConfigRepository extends JpaRepository<JobConfig, Integer> {
    List<JobConfig> findAllByStatus(int status);
}
