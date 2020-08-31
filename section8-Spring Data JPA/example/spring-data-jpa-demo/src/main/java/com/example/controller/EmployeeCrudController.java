package com.example.controller;

import com.example.model.Employee;
import com.example.model.EmployeeDto;
import com.example.repository.EmployeeRespository;
import com.example.service.EmployeeService;


import sun.util.logging.resources.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping(value="/employees")
public class EmployeeCrudController  extends BaseController{
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRespository employeeDao;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private List<Employee> employees;

	@GetMapping(produces = "application/json")
	public List<Employee> firstPage() {
		employees = employeeService.findAll();
		return employees;
	}

	//@DeleteMapping(path = { "/{id}" })
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Employee delete(@PathVariable("id") String id) {
		Employee deletedEmp = null;
		for (Employee emp : employees) {
			//logger.info("id="+id+";"+"empid="+emp.getEmpId());
			if (emp.getEmpId().equals(id)) {
				employees.remove(emp);
				employeeService.delete(emp);
				deletedEmp = emp;
				break;
			}
		}
		return deletedEmp;
	}

	public Employee empsave(EmployeeDto employee) {
		Employee emp = new Employee();
		emp.setEmpId(employee.getEmpId());
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		return employeeDao.save(emp);
	}
	
	@PostMapping(value = "/add")
	public Employee create(@RequestBody EmployeeDto employee) {
		Employee emp = new Employee();
		emp.setEmpId(employee.getEmpId());
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		employees.add(emp);
		return employeeDao.save(emp);
	}

	

}