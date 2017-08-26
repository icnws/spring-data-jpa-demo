package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/25.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String fullEntity;
    private String groupName;
    private String cronTime;
    private Integer status;
    private Date createAt;
    private Date updateAt;
}
