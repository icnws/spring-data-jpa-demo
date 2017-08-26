package com.example.demo.jobs;

import com.example.demo.dto.JobConfig;
import com.example.demo.service.JobConfigService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class MyJob implements Job {
    @Autowired
    private JobConfigService jobConfigService;

    public void execute(JobExecutionContext context) {
        System.out.println();
        System.out.println();
        System.out.println(context.getJobDetail().getDescription());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(this.toString() + ":" + f.format(new Date()) + "正在执行Job   executing...");
        List<JobConfig> configs = jobConfigService.findAllByStatus(1);
        for (JobConfig config : configs) {
            System.out.println(config.toString());
        }
    }
}