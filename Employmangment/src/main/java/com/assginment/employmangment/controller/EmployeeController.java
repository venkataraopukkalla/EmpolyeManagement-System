package com.assginment.employmangment.controller;

import java.net.URI;
import java.security.Timestamp;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assginment.employmangment.entity.EmployeeEntity;
import com.assginment.employmangment.exception.EmpoyeeNotFoundException;
import com.assginment.employmangment.repository.EmployeeRepository;


@RestController
public class EmployeeController {
	
	private EmployeeRepository employeeRepository;
     // autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	// get mapping
	
	@GetMapping("/employee-details")
	public List<EmployeeEntity> retriveAllRecipes(){
		return employeeRepository.findAll();
	}
	
	// GET user/{path variable}
		
		@GetMapping("/employee-details/{id}")
		public EmployeeEntity getUserById(@PathVariable Long id) {
			 EmployeeEntity byId = employeeRepository.getById(id);
			if(byId==null) {
				throw new EmpoyeeNotFoundException("id :"+id);
			}
			return byId;
		}
	
	// post 
	
	@PostMapping("/employee-details")
	public ResponseEntity<EmployeeEntity> addRecipes( @RequestBody EmployeeEntity recipeEntity ) {
		 employeeRepository.save(recipeEntity);
		// if u need add path location i.e /employee/4 
	    URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipeEntity.getId()).toUri();
		// below code is use to set 201 response for created 
		return ResponseEntity.created(location).build();
	}
	
	// delete Endpoint
	@DeleteMapping("/employee/{id}")
	 public void deleteRecipe(@PathVariable Long id) {
		 employeeRepository.deleteById(id);
	 }
	
	
	@PutMapping("empolyee/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity updatedEmployee) {
		
		EmployeeEntity existingEmployee = employeeRepository.findById(id).orElseThrow();
                
         existingEmployee.setDepartmentId(updatedEmployee.getDepartmentId());
         existingEmployee.setFirstName(updatedEmployee.getFirstName());
         existingEmployee.setLastName(updatedEmployee.getLastName());
         existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        return employeeRepository.save(existingEmployee);
        
    }
		     
	  

}
