package com.demo.employee.service;

import java.util.Date;
import java.util.List;

import com.demo.employee.model.Employee;


public interface EmployeeService {

	public List<Employee> getAllEmployee();

	public Employee getEmployee(Long id);

	public Employee saveEmployee(Employee employee);

	public void deleteEmployee(Long id);

	public List<Employee> getEmployeesByDate(Date start, Date end);

}
