package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Long cId;
    private BigDecimal total;

    //实体映射重复列必须设置：insertable = false,updatable = false
    @OneToOne
    @JoinColumn(name = "cId", insertable = false, updatable = false)
    private Customer customer;

    @Override
    public String toString() {
        return "MyOrder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", cId=" + cId +
                ", total=" + total +
                ", customer=" + customer +
                '}';
    }
}
