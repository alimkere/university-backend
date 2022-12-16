package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.models.Admission;

@RepositoryRestResource
public interface AdmissionRepository extends JpaRepository<Admission, Long>{

}

