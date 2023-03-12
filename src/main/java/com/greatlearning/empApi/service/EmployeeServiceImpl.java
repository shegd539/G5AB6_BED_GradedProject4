package com.greatlearning.empApi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.greatlearning.empApi.entity.Employee;
import com.greatlearning.empApi.entity.Role;
import com.greatlearning.empApi.entity.User;
import com.greatlearning.empApi.repository.EmployeeRepository;
import com.greatlearning.empApi.repository.RoleRepository;
import com.greatlearning.empApi.repository.UserRepository;

@Component
public class EmployeeServiceImpl {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder cryptPasswordEncoder;
	
	public Role saveRole(Role role) {

		Role theRole = roleRepository.findByName(role.getName());
		if (theRole == null) {
			return roleRepository.save(role);
		} else {
			return theRole;
		}

	}
	
	
	
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}
	
	
	public User saveUser(User user) {
		user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
		// Handle Existing Roles
		Set<Role> theRoles = new HashSet<>();
		for (Role theRole : user.getRoles()) {
			Role tempRole = roleRepository.findByName(theRole.getName());
			if (tempRole != null) {
				theRoles.add(tempRole);
			} else {
				theRoles.add(theRole);
			}
		}
		user.setRoles(theRoles);

		return userRepository.save(user);

	}
	
	
	public java.util.List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();

		} else {
			throw new RuntimeException("Employee id - " + theId + " Not Found");
		}
		return theEmployee;
	}

	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}
	
	public java.util.List<Employee> searchByFirstName(String firstName) {
		List<Employee> employeeList=employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
		if(employeeList.size()==0) {
			throw new RuntimeException("Employee firstName not found - " + firstName);
		}
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}
	
	
	
	public java.util.List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	
	public List<Employee> sortByFirstNameDesc() {
		return employeeRepository.findAllByOrderByFirstNameDesc();
	}
}
