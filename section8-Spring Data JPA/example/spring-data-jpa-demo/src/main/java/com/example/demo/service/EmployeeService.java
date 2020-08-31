package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CommonEntityDao;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDao;


@Service
public class EmployeeService extends SoftDeleteEntityService<Employee, Long>{

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Autowired
    private EmployeeDao employeeDao;
	
	@Override
    protected CommonEntityDao<Employee, Long> getCommonEntityDao() {
        return employeeDao;
    }
	
}
