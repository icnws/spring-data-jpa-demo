package com.example.demo.util;

import com.example.demo.config.AutoWiringSpringBeanJobFactory;
import com.example.demo.dto.JobConfig;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;

public class SchedulerUtil {

    private static StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private static CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();

    private static JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();

    private static AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();

    private static SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

    static {
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("quartz.properties"));
    }

    public static boolean createScheduler(JobConfig config, ApplicationContext context) {
        try {
            //创建新的定时任务
            return create(config, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param oldConfig
     * @param config
     * @param context
     * @return
     */
    public static Boolean modifyScheduler(JobConfig oldConfig, JobConfig config, ApplicationContext context) {
        if (oldConfig == null || config == null || context == null) {
            return false;
        }
        try {
            String oldJobClassStr = oldConfig.getFullEntity();
            String oldName = oldJobClassStr + oldConfig.getId();
            String oldGroupName = oldConfig.getGroupName();
            //1、清除旧的定时任务
            delete(oldName, oldGroupName);
            //2、创建新的定时任务
            return create(config, context);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    private static Boolean delete(String oldName, String oldGroupName) throws SchedulerException {
        TriggerKey key = new TriggerKey(oldName, oldGroupName);
        Scheduler oldScheduler = schedulerFactory.getScheduler();
        //根据TriggerKey获取trigger是否存在，如果存在则根据key进行删除操作
        Trigger keyTrigger = oldScheduler.getTrigger(key);
        if (keyTrigger != null) {
            oldScheduler.unscheduleJob(key);
        }
        return true;
    }

    private static Boolean create(JobConfig config, ApplicationContext context) {
        try {
            //创建新的定时任务
            String jobClassStr = config.getFullEntity();
            Class clazz = Class.forName(jobClassStr);
            String name = jobClassStr + config.getId();
            String groupName = config.getGroupName();
            String description = config.toString();
            String time = config.getCronTime();

            JobDetail jobDetail = createJobDetail(clazz, name, groupName, description);
            if (jobDetail == null) {
                return false;
            }
            Trigger trigger = createCronTrigger(jobDetail, time, name, groupName, description);
            if (trigger == null) {
                return false;
            }

            jobFactory.setApplicationContext(context);

            schedulerFactoryBean.setJobFactory(jobFactory);
            schedulerFactoryBean.setJobDetails(jobDetail);
            schedulerFactoryBean.setTriggers(trigger);
            schedulerFactoryBean.afterPropertiesSet();
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据指定的参数，创建JobDetail
     *
     * @param clazz
     * @param name
     * @param groupName
     * @param description
     * @return
     */
    public static JobDetail createJobDetail(Class clazz, String name, String groupName, String description) {
        jobDetailFactory.setJobClass(clazz);
        jobDetailFactory.setName(name);
        jobDetailFactory.setGroup(groupName);
        jobDetailFactory.setDescription(description);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory.getObject();
    }

    /**
     * 根据参数，创建对应的CronTrigger对象
     *
     * @param job
     * @param time
     * @param name
     * @param groupName
     * @param description
     * @return
     */
    public static CronTrigger createCronTrigger(JobDetail job, String time, String name, String groupName, String description) {
        factoryBean.setName(name);
        factoryBean.setJobDetail(job);
        factoryBean.setCronExpression(time);
        factoryBean.setDescription(description);
        factoryBean.setGroup(groupName);
        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return factoryBean.getObject();
    }


}
