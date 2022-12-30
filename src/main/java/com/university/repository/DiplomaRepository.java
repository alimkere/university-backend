package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.university.models.Diploma;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface DiplomaRepository extends JpaRepository<Diploma, Long>{
	
	

}
