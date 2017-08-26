package com.example.demo.config;

import com.example.demo.dto.JobConfig;
import com.example.demo.service.JobConfigService;
import com.example.demo.util.SchedulerUtil;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnExpression("'${quartz.enabled}'=='true'")
public class SchedulerConfig {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobConfigService jobConfigService;

    @Bean
    public StdSchedulerFactory stdSchedulerFactory() {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        //获取JobConfig集合
        List<JobConfig> configs = jobConfigService.findAllByStatus(1);
        logger.debug("Setting the Scheduler up");
        for (JobConfig config : configs) {
            try {
                Boolean flag = SchedulerUtil.createScheduler(config, applicationContext);
                System.out.println("执行结果：" + (flag == true ? "成功" : "失败"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stdSchedulerFactory;
    }
}
