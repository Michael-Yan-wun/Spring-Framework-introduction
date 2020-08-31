package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Employee;


public interface EmployeeRespository extends CrudRepository<Employee, Long> {
	
}
