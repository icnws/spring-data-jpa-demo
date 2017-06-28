package com.example.demo.repositories;

import com.example.demo.dto.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * 根据lastName查询结果
     * @param lastName
     * @return
     */
    List<Customer> findByLastName(String lastName);

    /**
     * 模糊匹配
     * @param bauer
     * @return
     */
    List<Customer> findByFirstNameContaining(String bauer);

    /**
     * 模糊匹配
     * @param bauer
     * @return
     */
    Customer findByFirstName(String bauer);

    List<Customer> findByLastNameOrderByIdDesc(String bau);


    List<Customer> findByLastNameContainingOrderByIdDesc(String bau);

    List<Customer> findByIdBetween(Long start, Long end);

    @Query("select c from Customer c where c.firstName=?1 order by c.id desc")
    List<Customer> findByFirstName1(@Param("firstName") String firstName);

    @Query("select c from Customer c where c.lastName=?1  order by c.id desc")
    List<Customer> findByName(String lastName);

    @Query(nativeQuery = true, value = "select * from Customer c where c.first_name= ?1 or c.last_name= ?2 order by id desc")
    List<Customer> findByName2(String firstName, String lastName);

    @Query("select c from Customer c where c.lastName=:lastName  order by c.id desc")
    List<Customer> findByName3(@Param("lastName") String lastName);

    @Query("select c from Customer c where c.lastName=:lastName  or c.firstName=:lastName order by c.id desc")
    List<Customer> findByName4(@Param("lastName") String lastName);


    @Query("select c from Customer c where c.lastName=:lastName  or c.firstName=:lastName")
    List<Customer> findByName5(@Param("lastName") String lastName, Sort sort);


}