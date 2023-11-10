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

import com.assginment.employmangment.repository.DepartmentRepository;
import com.assginment.employmangment.entity.DepartmentEntity;
import com.assginment.employmangment.entity.EmployeeEntity;
import com.assginment.employmangment.exception.EmpoyeeNotFoundException;
@RestController
public class DepartmentController {
	private DepartmentRepository departmentRepository;

	public DepartmentController(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	// Get endpoint
	
	@GetMapping("/dep")
	public List<DepartmentEntity> retriveAllRecipes(){
		return departmentRepository.findAll();
	}
	
	
	  
	
	//post endpoint

     @PostMapping("/dep")
	public ResponseEntity<DepartmentEntity> addRecipes( @RequestBody DepartmentEntity recipeEntity ) {
		 departmentRepository.save(recipeEntity);
		// if u need add path location i.e /department/4 
	    URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipeEntity.getDepartmentId()).toUri();
		// below code is use to set 201 response for created 
		return ResponseEntity.created(location).build();
	}
     
     //delete endpoint 
     
     @DeleteMapping("/dep/{id}")
    public void deleteRecipe(@PathVariable String  depId) {
        departmentRepository.deleteById(depId);
    }
     
     @PutMapping("/dep/{id}")
     public DepartmentEntity updateDepartment(@PathVariable String id, @RequestBody DepartmentEntity updatedDepartment) {
    	 DepartmentEntity entity = departmentRepository.findById(id).orElseThrow();
    	 entity.setDepartmentId(updatedDepartment.getDepartmentId());
    	 entity.setDepartmentName(updatedDepartment.getDepartmentName());
    	 entity.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
         return departmentRepository.save(entity);
     }


}
