package com.demo.employee.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.model.Employee;
import com.demo.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		ResponseEntity.ok().build();
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployee(), HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity(HttpStatus.OK);

	}

	@GetMapping("/getAllByDate")
	public ResponseEntity<List<Employee>> getEmployeesByDate(
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {

		return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByDate(start, end), HttpStatus.OK);

	}

}
