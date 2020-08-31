package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CommonEntityDao;


@Repository
public interface EmployeeDao extends CommonEntityDao<Employee, Long> {

    @Query("select r from Employee r where r.deleted = false and r.name = :name")
    public Optional<Employee> findByName(@Param("name") String name);
    
    @Query("select r from Employee r where r.deleted = false")
    public List<Employee> findAll();

}
