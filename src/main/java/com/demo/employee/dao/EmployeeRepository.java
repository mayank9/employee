package com.demo.employee.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.demo.employee.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public List<Employee> findAll();

	public Optional<Employee> findById(Long id);
	
	public Optional<Employee> findByEmpName(String name);
	
	public List<Employee> findByJoiningDateBetween(Date start, Date end);

	
	


}
