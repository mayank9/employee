package com.demo.employee.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.employee.model.Employee;
import com.demo.employee.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@MockBean
	EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;

	private List<Employee> employees;

	private String resultEmployees;
	private String resultEmployee;

	private Employee emp;

	@Before
	public void setUp() throws JsonProcessingException {
		emp = new Employee();
		emp.setId(1L);
		emp.setEmpName("Mak");

		employees = new ArrayList<>();
		employees.add(emp);

		ObjectMapper mapper = new ObjectMapper();
		resultEmployees = mapper.writeValueAsString(employees);
		resultEmployee = mapper.writeValueAsString(emp);
		System.out.println(resultEmployees);

	}

	@Test
	public void testGetAllEmployee() throws Exception {
		when(employeeService.getAllEmployee()).thenReturn(employees);

		mockMvc.perform(get("/employee/getAll").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().json(resultEmployees));

		verify(employeeService).getAllEmployee();

	}

	@Test
	public void testGetEmployee() throws Exception {
		when(employeeService.getEmployee(1L)).thenReturn(emp);

		mockMvc.perform(get("/employee/get/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().json(resultEmployee));
		verify(employeeService).getEmployee(1L);
	}
}
