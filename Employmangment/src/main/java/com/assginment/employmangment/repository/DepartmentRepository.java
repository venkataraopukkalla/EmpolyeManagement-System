package com.assginment.employmangment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assginment.employmangment.entity.DepartmentEntity;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {

}
