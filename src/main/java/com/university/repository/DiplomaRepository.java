package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.models.Diploma;

@RepositoryRestResource
public interface DiplomaRepository extends JpaRepository<Diploma, Long>{

}
