package com.greatlearning.empApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.greatlearning.empApi.entity.Employee;
import com.greatlearning.empApi.entity.Role;
//import com.greatlearning.empApi.entity.User;

@Service
public interface EmployeeService {
//	public List<Employee> findAll();
//
//	public Employee findById(int theId);
//
//	public void save(Employee theEmployee);
//
//	public void deleteById(int theId);
//
//	public List<Employee> searchByFirstName(String firstName);
//
//	public List<Employee> sortByFirstNameAsc();
//
//	public List<Employee> sortByFirstNameDesc();
//
//	public User saveUser(User user);

	public Role saveRole(Role role);
}
