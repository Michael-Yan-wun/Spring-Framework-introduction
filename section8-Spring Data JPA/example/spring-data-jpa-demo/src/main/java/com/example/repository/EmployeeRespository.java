package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Employee;


public interface EmployeeRespository extends CrudRepository<Employee, Long> {
	
}
