package com.example.demo.service;

import com.example.demo.dto.JobConfig;
import com.example.demo.repositories.JobConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */
@Service
public class JobConfigService {
    @Autowired
    private JobConfigRepository jobConfigRepository;

    public List<JobConfig> findAllByStatus(Integer status) {
        return jobConfigRepository.findAllByStatus(status);
    }
}
