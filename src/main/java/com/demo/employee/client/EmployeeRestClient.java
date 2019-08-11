package com.demo.employee.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.employee.model.Employee;

public class EmployeeRestClient {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public List<Employee> getAllEmployee() throws RestClientException, URISyntaxException{
		return restTemplate.getForObject(new URI("http://localhost:8080/employee/getAll"),ArrayList.class);
	}
	
	public Employee getEmployee() throws RestClientException, URISyntaxException{
		return restTemplate.getForObject(new URI("http://localhost:8080/employee/get/1"),Employee.class);
	}
	
	public void saveEmployee() throws RestClientException, URISyntaxException{
		Employee emp = new Employee();
		emp.setEmpName("Dhanji");
		emp.setDesignation("Dev");
		restTemplate.postForEntity(new URI("http://localhost:8080/employee/save"), emp, Employee.class);
		 
	}
	
	public void getBody() throws RestClientException, URISyntaxException{
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/employee/getAll", Object[].class);
		Object[] objects = responseEntity.getBody();
		for (Object object : objects) {
			System.out.println(object);
			
		}
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();
		 
	}
	
	
	
	
		public static void main(String ...strings) throws RestClientException, URISyntaxException {
		EmployeeRestClient employeeRestClient = new EmployeeRestClient();
		//employeeRestClient.saveEmployee();

		//List<Employee> emp = employeeRestClient.getAllEmployee();
	
		
		
		//System.out.println(employeeRestClient.getEmployee().toString());
		employeeRestClient.getBody();

	}

}
