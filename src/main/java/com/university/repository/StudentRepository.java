package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.university.models.Student;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long>{

}
