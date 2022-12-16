package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.university.models.Registration;

@RepositoryRestResource
public interface RegistrationRepository extends JpaRepository<Registration, Long>{

}
