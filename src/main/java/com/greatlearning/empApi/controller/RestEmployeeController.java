package com.greatlearning.empApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.empApi.entity.Employee;
import com.greatlearning.empApi.entity.Role;
import com.greatlearning.empApi.entity.User;
import com.greatlearning.empApi.service.EmployeeService;
import com.greatlearning.empApi.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/api")
public class RestEmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	/**1. application should be able to add roles in the database dynamically in the db.
	 *  */
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}
	
	
	/* 2. application should be able to add Users in the db which can be used for authentication purposes
	 *  */
	// add mapping for POST /employees - add new employee

	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}
	
	
	
	
		@PostMapping("/employees")
		public Employee addEmployee(@RequestBody Employee theEmployee) {

			// also just in case they pass an id in JSON ... set id to 0
			// this is to force a save of new item ... instead of update

			theEmployee.setId(0);

			employeeService.save(theEmployee);

			return theEmployee;
		}
		
		
		// return list of employees
		@GetMapping("/employees")
		public List<Employee> findAll() {
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
			//System.out.println(currentPrincipalName);
			return employeeService.findAll();
		}
		
		
		// add mapping for GET /employees/{employeeId}

		@GetMapping("/employees/{employeeId}")
		public Employee getEmployee(@PathVariable int employeeId) {

			Employee theEmployee = employeeService.findById(employeeId);

			if (theEmployee == null) {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}

			return theEmployee;
		}

		
		
		// add mapping for PUT /employees - update existing employee

		@PutMapping("/employees")
		public Employee updateEmployee(@RequestBody Employee theEmployee) {

			employeeService.save(theEmployee);

			return theEmployee;
		}
		
		@DeleteMapping("/employees/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {

			Employee theEmployee = employeeService.findById(employeeId);

			// throw exception if null

			if (theEmployee == null) {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}

			employeeService.deleteById(employeeId);

			return "Deleted employee id - " + employeeId;
		}
		
		@GetMapping("/employees/search/{firstName}")
		public List<Employee> searchByFirstName(@PathVariable String firstName) {
			return employeeService.searchByFirstName(firstName);
		}
		
		
		@GetMapping("/employees/sort")
		public List<Employee> sortByFirstName(@RequestParam("order") String order) {
			if (order.equalsIgnoreCase("asc")) {
				return employeeService.sortByFirstNameAsc();
			} else if (order.equalsIgnoreCase("desc")) {
				return employeeService.sortByFirstNameDesc();
			}
			return null;
		}

}
