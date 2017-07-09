package com.example.demo.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2017/7/9 0009.
 */
public interface CustomerProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    String getFirstName();

    String getLastName();
}
