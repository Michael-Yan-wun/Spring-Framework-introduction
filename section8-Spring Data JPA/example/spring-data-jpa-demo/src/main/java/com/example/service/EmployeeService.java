package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CommonEntityDao;
import com.example.model.Employee;
import com.example.model.EmployeeDao;


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
