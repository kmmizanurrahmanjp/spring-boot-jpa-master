package xyz.mizan.springbootjpacrudapi.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.mizan.springbootjpacrudapi.employee.entity.Employee;
import xyz.mizan.springbootjpacrudapi.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value="/store",method=RequestMethod.GET)
	public List<Employee> selectEmployee(){
		return employeeService.selectEmployee();
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public List<Employee> insertEmployee(@RequestBody Employee e){
		employeeService.insertEmployee(e);
		return employeeService.selectEmployee();
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public List<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee e){
		employeeService.updateEmployee(id,e);
		return employeeService.selectEmployee();
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public List<Employee> deleteEmployee(@PathVariable int id){
		employeeService.deleteEmployee(id);
		return employeeService.selectEmployee();
	}
}
