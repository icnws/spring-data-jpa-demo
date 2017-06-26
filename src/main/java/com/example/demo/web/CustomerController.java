package com.example.demo.web;

import com.example.demo.dto.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    /**
     * 初始化数据
     */
    @RequestMapping("/index")
    public void index() {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
        repository.save(new Customer("Bauer", "Dessler"));
    }

    /**
     * 查询所有
     */
    @RequestMapping("/findAll")
    public void findAll(){
        List<Customer> result = repository.findAll();
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 查询ID为1的数据
     */
    @RequestMapping("/findOne")
    public void findOne(){
        Customer result = repository.findOne(1L);
        if(result!=null){
            System.out.println(result.toString());
        }
        System.out.println("-------------------------------------------");
    }
    /**
     * 查询ID为1的数据
     */
    @RequestMapping("/delete")
    public void delete(){

        System.out.println("删除前数据：");
        List<Customer> customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }

        System.out.println("删除ID=3数据：");
        repository.delete(3l);

        System.out.println("删除后数据：");
        customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }


}
