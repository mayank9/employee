package com.demo.employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.employee.dao.EmployeeRepository;
import com.demo.employee.model.Employee;
import com.demo.employee.service.EmployeeService;
import com.demo.employee.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	private List<Employee> list = new ArrayList<>();

	private EmployeeService employeeService;

	@Before
	public void setUp() {

		employeeService = new EmployeeServiceImpl(employeeRepository);
		list.add(new Employee());
		list.add(new Employee());

	}

	@Test
	public void testSave() {
		Employee emp = new Employee();
		emp.setId(2L);
		emp.setEmpName("Kat");
		when(employeeRepository.save(emp)).thenReturn(emp);
		employeeService.saveEmployee(emp);
		verify(employeeRepository).save(emp);

	}

	@Test
	public void testGetAll() {
		when(employeeRepository.findAll()).thenReturn(list);
		assertEquals(2, employeeService.getAllEmployee().size());
	}

	@Test
	public void testGet() {
		Employee emp = new Employee();
		emp.setId(2L);
		emp.setEmpName("Kat");

		when(employeeRepository.findById(2L)).thenReturn(Optional.of(emp));
		String empName = employeeService.getEmployee(2L).getEmpName();

		verify(employeeRepository, times(2)).findById(2L);

		assertEquals("Kat", empName);

	}

}
