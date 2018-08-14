package xyz.mizan.springbootjpacrudapi.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.mizan.springbootjpacrudapi.employee.entity.Employee;
import xyz.mizan.springbootjpacrudapi.employee.service.EmployeeServiceImp;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImp employeeServiceImp;

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public Map<String, Object> insertEmployee(@RequestBody Employee e){
		Map<String, Object> response = new HashMap<String, Object>();
		Employee employee = employeeServiceImp.insertEmployee(e);
		if(employee.equals(null)) {
			response.put("success", false);
			response.put("massage", "Employee save failed");
		}else {
			response.put("success", true);
			response.put("data", employee);
			response.put("massage", "Employee save Success");
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Map<String, Object> updateEmployee(@PathVariable int id,@RequestBody Employee e){
		Map<String, Object> response = new HashMap<String, Object>();
		Employee employee = employeeServiceImp.updateEmployee(id,e);
		if(null == employee) {
			response.put("success", false);
			response.put("massage", "Employee update failed");
		}else {
			response.put("success", true);
			response.put("data", employee);
			response.put("massage", "Employee update Success");
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Map<String, Object> deleteEmployee(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		if(employeeServiceImp.deleteEmployee(id) == true) {
			response.put("success", true);
			response.put("massage", "Employee delete success");
		}else {
			response.put("success", false);
			response.put("massage", "Employee delete failed");
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> selectEmployee(){
		Map<String, Object> response = new HashMap<String, Object>();
		List<Employee> employees = employeeServiceImp.selectEmployee();
		if(employees.isEmpty()) {
			response.put("success", false);
			response.put("data", employees);
			response.put("massage", "Employee selected failed");
		}else {
			response.put("success", true);
			response.put("data", employees);
			response.put("massage", "Employee selected Success");
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Map<String, Object> selectEmployeeById(@PathVariable int id){
		Map<String, Object> response = new HashMap<String, Object>();
		Employee employee = employeeServiceImp.selectEmployeeById(id);
		if(null == employee) {
			response.put("success", false);
			response.put("data", employee);
			response.put("massage", "Employee selected failed");
		}else {
			response.put("success", true);
			response.put("data", employee);
			response.put("massage", "Employee selected Success");
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value = "search", method = RequestMethod.GET)
    public Map<String, Object> selectPatientByCriteria(
    		@RequestHeader("employeeId") String id, 
    		@RequestHeader("employeeName") String name, 
    		@RequestHeader("employeeAddress") String address, 
    		@RequestHeader("employeePhone") String phone) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		Employee employee = new Employee();
		if(id != ""){
			employee.setId(Integer.valueOf(id));
		}else{
			employee.setId(0);
		}
		employee.setName(name);
		employee.setAddress(address);
		employee.setPhone(phone);
		List<Employee> query = employeeServiceImp.selectPatientByCriteria(employee);
		if(query.isEmpty()) {
			response.put("success", false);
			response.put("data", query);
			response.put("massage", "Employee not found");
		}else {
			response.put("success", true);
			response.put("data", query);
			response.put("massage", "Employee found");
		}
        return response;
    }
	
	
}
