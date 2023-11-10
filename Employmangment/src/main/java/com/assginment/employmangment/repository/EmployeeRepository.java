package com.assginment.employmangment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assginment.employmangment.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
