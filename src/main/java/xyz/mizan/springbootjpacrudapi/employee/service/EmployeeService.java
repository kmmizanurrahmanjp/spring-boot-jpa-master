package xyz.mizan.springbootjpacrudapi.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.mizan.springbootjpacrudapi.employee.entity.Employee;
import xyz.mizan.springbootjpacrudapi.employee.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepository;
	
	public List<Employee> selectEmployee(){
		return employeeRepository.findAll();
	}
	
	public Employee insertEmployee(Employee e){
		return employeeRepository.save(e);
	}
	
	public Employee updateEmployee(int id,Employee e){
		Optional<Employee> empChack = employeeRepository.findById(id);
		if(!empChack.isPresent()) {
			
		}
		e.setId(id);
		return employeeRepository.save(e);
	}
	
	public void deleteEmployee(int id){
		employeeRepository.deleteById(id);
	}
}
