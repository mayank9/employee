package com.demo.employee.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee.dao.EmployeeRepository;
import com.demo.employee.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;

	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeesByDate(Date start, Date end) {
		return employeeRepository.findByJoiningDateBetween(start, end);
	}

}
